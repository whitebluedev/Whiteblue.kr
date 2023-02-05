import React from 'react'; import { FunctionComponent } from 'react'
import styled from '@emotion/styled'
import { motion } from 'framer-motion'
// React Library
// Custom Library
// MUI Library
import { Typography } from '@mui/material'

const Body = styled(motion.div)`
  width: 100%;
  padding: 4% 0 4% 25%;
  text-align: left;

  & .divider {
    text-align: center;
  }
`

const Divider = styled(motion.div)`
  width: 67%;
  height: 4%;
  margin-top: 1%;
  background-color: #BDBDBD;  
`

type Title = {
    name: string;
};

const Header: FunctionComponent<Title> = ({ name }) => (
    <Body>
        <Typography
            variant='h4'
            sx={{
                fontFamily: 'ChosunBg',
                fontWeight: 100
            }}
        >
            {name}
        </Typography>
        <Divider className='divider' />
    </Body>
);

export default Header