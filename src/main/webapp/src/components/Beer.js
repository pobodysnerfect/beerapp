import React, {Component} from 'react';
import Box from 'grommet/components/Box';
import Card from 'grommet/components/Card';
import Sidebar from 'grommet/components/Sidebar';
import Split from 'grommet/components/Split';
import Section from 'grommet/components/Section';
import List from 'grommet/components/List';
import ListItem from 'grommet/components/ListItem';
import beerStock from './beer-stock.jpg';

class Beer extends Component {

    constructor(props) {
        super(props);

        this.state = {
            beer: []
        };
    }

    componentDidMount() {
        const {id} = this.props.match.params;
        fetch(`/api/beers/${id}`)
            .then(response => response.json())
            .then(data => this.setState({beer: data}))
            .catch(ext => console.log(ext));
    }

    render() {
        const {beer} = this.state;
        return (
            <Split flex='right' priority='right'>
                <Sidebar full={false}>
                    <Box align='center' pad='medium'>
                        <Card thumbnail={beerStock}
                              label={beer.style}
                              heading={beer.name}/>
                    </Box>
                </Sidebar>
                <Box>
                    <Section>
                        <List>
                            <ListItem>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ullamcorper scelerisque risus vel tempor. Mauris enim ante, vestibulum vel justo in, tristique pellentesque nulla. Nam augue velit, egestas id tristique at, porttitor sit amet magna. Integer id ante est. Aenean congue efficitur enim, eget finibus sapien vehicula eget. Morbi eu purus a tellus suscipit tincidunt. Ut faucibus ex a arcu condimentum ullamcorper. Nam tincidunt varius quam, efficitur tristique ante.</p>
                            </ListItem>

                            <ListItem>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ullamcorper scelerisque risus vel tempor. Mauris enim ante, vestibulum vel justo in, tristique pellentesque nulla. Nam augue velit, egestas id tristique at, porttitor sit amet magna. Integer id ante est. Aenean congue efficitur enim, eget finibus sapien vehicula eget. Morbi eu purus a tellus suscipit tincidunt. Ut faucibus ex a arcu condimentum ullamcorper. Nam tincidunt varius quam, efficitur tristique ante.</p>
                            </ListItem>

                            <ListItem>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ullamcorper scelerisque risus vel tempor. Mauris enim ante, vestibulum vel justo in, tristique pellentesque nulla. Nam augue velit, egestas id tristique at, porttitor sit amet magna. Integer id ante est. Aenean congue efficitur enim, eget finibus sapien vehicula eget. Morbi eu purus a tellus suscipit tincidunt. Ut faucibus ex a arcu condimentum ullamcorper. Nam tincidunt varius quam, efficitur tristique ante.</p>
                            </ListItem>
                        </List>
                    </Section>
                </Box>
            </Split>
        );
    }
}

export default Beer;
