import React, {Component} from 'react';
import Box from 'grommet/components/Box';
import Search from 'grommet/components/Search';
import Hero from 'grommet/components/Hero';
import Image from 'grommet/components/Image';
import get from 'lodash/get';
import debounce from 'lodash/debounce';
import heroImg from './hero.jpg';
import FontAwesomeIcon from '@fortawesome/react-fontawesome'
import faBeer from '@fortawesome/fontawesome-free-solid/faBeer'
import faBuilding from '@fortawesome/fontawesome-free-solid/faBuilding'

class Home extends Component {

    constructor(props) {
        super(props);

        this.state = {
            searchSuggestions: []
        };

        this.onSearchType = debounce(this.onSearchType.bind(this), 300);
        this.onSelect = this.onSelect.bind(this);
    }


    onSearchType(event) {
        const query = event.target.value;
        if (!query || query.trim().length <= 2) {
            this.setState({searchSuggestions: []});
            return;
        }

        fetch('/api/search?q=' + query)
            .then(response => response.json())
            .then(data => {
                const hits = get(data, 'hits.hits', []),
                    suggestions = hits.map((hit) => {
                        const map = get(hit, 'sourceAsMap', {}),
                            name = get(map, 'name'),
                            index = get(hit, 'index'),
                            icon = !!get(map, 'breweryId') ? faBeer : faBuilding,
                            label = [
                                <FontAwesomeIcon icon={icon} key={1}/>, <span key={2}> {name} ({index}) </span>
                            ];

                        return Object.assign({label: `${name} (${index})`}, map, {label});
                    });

                this.setState({searchSuggestions: suggestions})
            })
            .catch(ext => console.log(ext));
    }

    onSelect(selection) {
        console.log(selection);
        this.setState({searchSuggestions: []});

        if (!!get(selection, 'suggestion.breweryId')) {
            this.props.history.push({pathname: `/beers/${selection.suggestion.id}`});
        } else {
            this.props.history.push({pathname: `/breweries/${selection.suggestion.id}`});
        }
    }


    render() {
        return (
            <Hero background={<Image src={heroImg}
                                     fit='cover'
                                     full={true}/>}
                  backgroundColorIndex='dark'>

                <Box direction='row' justify="center">
                    <Box colorIndex='light-1' basis='1/2'>
                        <Search inline={true}
                                fill={true}
                                size='medium'
                                placeHolder='Search beers, breweries...'
                                onDOMChange={this.onSearchType}
                                onSelect={this.onSelect}
                                suggestions={this.state.searchSuggestions}/>
                    </Box>
                </Box>
            </Hero>
        );
    }
}

export default Home;
