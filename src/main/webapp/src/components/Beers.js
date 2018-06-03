import React, {Component} from 'react';
import Box from 'grommet/components/Box';
import Tiles from 'grommet/components/Tiles';
import Tile from 'grommet/components/Tile';
import Card from 'grommet/components/Card';
import Sidebar from 'grommet/components/Sidebar';
import Split from 'grommet/components/Split';
import beerStock from './beer-stock.jpg';
import beerIcon from './beer-icon.png';

class Beers extends Component {

    constructor(props) {
        super(props);

        this.state = {
            beers: []
        };

        this.onTileSelect = this.onTileSelect.bind(this);
    }

    componentDidMount() {
        fetch('/api/beers')
            .then(response => response.json())
            .then(data => this.setState({beers: data}))
            .catch(ext => console.log(ext));
    }

    render() {
        const {beers} = this.state;
        return (
            <Split flex='right' fixed={true}>
                <Sidebar fixed={true} size='small'>
                    <Box align='center' pad='medium'>
                        <Card thumbnail={beerIcon}
                              alignContent='center'
                              textSize='small'
                              heading='Beers'/>
                    </Box>
                </Sidebar>
                <Box>
                    <Tiles flush={false}
                           selectable={true}
                           onSelect={this.onTileSelect}>
                        {beers.map((beer) => {
                            return (
                                <Tile key={beer.id} size='medium'>
                                    <Card thumbnail={beerStock}
                                          textSize='xsmall'
                                          label={beer.style}
                                          heading={beer.name}/>
                                </Tile>
                            )
                        })}
                    </Tiles>
                </Box>
            </Split>
        );
    }

    onTileSelect(index) {
        const beer = this.state.beers[index];
        this.props.history.push({pathname: `/beers/${beer.id}`});
    }
}

export default Beers;
