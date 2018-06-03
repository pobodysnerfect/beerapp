import React, {Component} from 'react';
import Box from 'grommet/components/Box';
import Menu from 'grommet/components/Menu';
import Anchor from 'grommet/components/Anchor';
import Footer from 'grommet/components/Footer';
import Paragraph from 'grommet/components/Paragraph';

class CustomFooter extends Component {

    render() {
        return (
            <Footer justify='center' size='large' colorIndex='light-2'>
                <Box direction='row'
                     align='center'
                     pad={{"between": "medium"}}>
                    <Paragraph margin='none'>
                        © 2018 Beery McBeer
                    </Paragraph>
                    <Menu direction='row'
                          size='small'
                          dropAlign={{"right": "right"}}>
                        <Anchor href='#'>
                            Support
                        </Anchor>
                        <Anchor href='#'>
                            Contact
                        </Anchor>
                        <Anchor href='#'>
                            About
                        </Anchor>
                    </Menu>
                </Box>
            </Footer>
        );
    }
}

export default CustomFooter;
