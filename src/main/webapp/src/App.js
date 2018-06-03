import React, {Component} from 'react';
import './App.css';
import App from 'grommet/components/App';
import Box from 'grommet/components/Box';
import Home from './components/Home';
import Breweries from './components/Breweries';
import Brewery from './components/Brewery';
import Beer from './components/Beer';
import Beers from './components/Beers';
import Footer from './components/Footer';
import Header from './components/Header';
import NoMatch from './components/NoMatch';
import {
    BrowserRouter as Router,
    Route,
    Switch
} from 'react-router-dom'

class AppClass extends Component {
    render() {
        return (
            <Router>
                <App centered={false}>
                    <Box>
                        <Header/>
                        <Switch>
                            <Route path="/beers/:id" component={Beer}/>
                            <Route path="/beers" component={Beers}/>
                            <Route path="/breweries/:id" component={Brewery}/>
                            <Route path="/breweries" component={Breweries}/>
                            <Route exact path="/" component={Home}/>
                            <Route component={NoMatch} />
                        </Switch>
                        <Footer/>
                    </Box>
                </App>
            </Router>
        );
    }
}

export default AppClass;
