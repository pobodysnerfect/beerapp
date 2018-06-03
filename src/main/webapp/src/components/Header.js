import React, {Component} from 'react';
import Header from 'grommet/components/Header';
import Box from 'grommet/components/Box';
import Menu from 'grommet/components/Menu';
import Title from 'grommet/components/Title';
import { Link } from 'react-router-dom';
import RobotIcon from 'grommet/components/icons/base/Robot';

class CustomHeader extends Component {
    render() {
        return (
            <Header justify='center' colorIndex="grey-2" fixed={true}>
                <Box size={{width: {max: 'xxlarge'}}} direction="row"
                     responsive={false} justify="start" align="center"
                     pad={{horizontal: 'medium'}} flex="grow">
                <Title>
                    <Link to='/' className='active'>
                        <RobotIcon  colorIndex="light-1"/> BeerTopia
                    </Link>
                </Title>
                <Box flex={true}
                     justify='end'
                     direction='row'
                     responsive={false}>
                    <Menu direction='row'
                          size='large'
                          dropAlign={{"left": "left"}}>
                        <Link to='/beers' className='active'>
                            Beers
                        </Link>
                        <Link to='/breweries'>
                            Breweries
                        </Link>
                    </Menu>
                </Box>
                </Box>
            </Header>
        );
    }
}

export default CustomHeader;
