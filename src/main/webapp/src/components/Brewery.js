import React, {Component} from 'react';
import Card from 'grommet/components/Card';
import ListItem from 'grommet/components/ListItem';
import List from 'grommet/components/List';
import Sidebar from 'grommet/components/Sidebar';
import Split from 'grommet/components/Split';
import Box from 'grommet/components/Box';
import Section from 'grommet/components/Section';
import Image from 'grommet/components/Image';
import beerStock from './beer-stock.jpg';
import breweryStock from './brewery-stock.jpg';
import { Link } from 'react-router-dom';

class Brewery extends Component {

    constructor(props) {
        super(props);

        this.state = {
            brewery: {}
        };
    }

    componentDidMount() {
        const {id} = this.props.match.params;
        fetch(`/api/breweries/${id}`)
            .then(response => response.json())
            .then(data => this.setState({brewery: data}))
            .catch(ext => console.log(ext));
    }

    render() {
        const {brewery} = this.state;
        if (!brewery || !brewery.beers) {
            return <h3>loading...</h3>;
        }
        return (
            <Split flex='right' priority='right'>
                <Sidebar full={false}>
                    <Box align='center' pad='medium'>
                        <Card thumbnail={breweryStock}
                              textSize='xsmall'
                              label={brewery.address}
                              heading={brewery.name}/>
                    </Box>
                </Sidebar>
                <Box>
                    <Section>
                        <List
                            onSelect={this.onTileSelect}>
                            {brewery.beers.map((beer, index) => {
                                const beerLink = `/beers/${beer.id}`;
                                return (
                                    <ListItem key={index} >
                                        <Box direction='column' pad='small'  >
                                            <Image src={beerStock} size='thumb'/>
                                        </Box>
                                        <Box direction='column' pad='small'>
                                            <Link to={beerLink}>
                                                <Card textSize='xsmall' label={beer.name} description={beer.style}/>

                                            </Link>
                                        </Box>
                                    </ListItem>
                                )
                            })}
                        </List>
                    </Section>
                </Box>
            </Split>
    );
    }

    onTileSelect(index) {
        const beer = this.state.brewery.beers[index];
        this.props.history.push({pathname: `/beers/${beer.id}`});
    }
}

export default Brewery;
