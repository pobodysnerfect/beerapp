import React, {Component} from 'react';
import Box from 'grommet/components/Box';
import Tiles from 'grommet/components/Tiles';
import Tile from 'grommet/components/Tile';
import Card from 'grommet/components/Card';
import Sidebar from 'grommet/components/Sidebar';
import Split from 'grommet/components/Split';
import breweryStock from './brewery-stock.jpg';
import breweryIcon from './brewery-icon.jpg';

class Breweries extends Component {

    constructor(props) {
        super(props);

        this.state = {
            breweries: []
        };
        this.onTileSelect = this.onTileSelect.bind(this);
    }

    componentDidMount() {
        fetch('/api/breweries')
            .then(response => response.json())
            .then(data => this.setState({breweries: data}))
            .catch(ext => console.log(ext));
    }

    render() {
        const {breweries} = this.state;
        return (
            <Split flex='right' priority='right'>
                <Sidebar fixed={true} size='small'>
                    <Box align='center' pad='medium'>
                        <Card thumbnail={breweryIcon}
                              alignContent='center'
                              textSize='small'
                              heading='Breweries'/>
                    </Box>
                </Sidebar>
                <Box>
                    <Tiles flush={false}
                           selectable={true}
                           onSelect={this.onTileSelect}>
                        {breweries.map((brewery) => {
                            return (
                                <Tile key={brewery.id} size='medium'>
                                    <Card thumbnail={breweryStock}
                                          textSize='xsmall'
                                          label={brewery.address}
                                          heading={brewery.name}/>
                                </Tile>
                            )
                        })}
                    </Tiles>
                </Box>
            </Split>
        );
    }

    onTileSelect(index) {
        const brewery = this.state.breweries[index];
        this.props.history.push({pathname: `/breweries/${brewery.id}`});
    }
}

export default Breweries;
