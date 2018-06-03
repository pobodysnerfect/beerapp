import React, {Component} from 'react';
import Box from 'grommet/components/Box';

class NoMatch extends Component {

    render() {
        return (
            <Box>
               <h1>404</h1>
                <h3>Woh there, you aren't where you think you are.</h3>
            </Box>
        );
    }
}

export default NoMatch;
