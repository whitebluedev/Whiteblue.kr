import * as React from 'react';
import styled from '@emotion/styled'
import { motion } from 'framer-motion'
// React Library
import { FunctionComponent } from 'react'
// Custom Library

// MUI Library
import {
  Divider,
} from '@mui/material'


const Body = styled(motion.div)`
  width: 100%;
  padding: 0.3% 0;
`
const Header: FunctionComponent = () => {
  return (
    <Body>
      <Divider
        textAlign="center"
        sx={{ mx: 20, my: 3 }} 
      >
        example
      </Divider>
    </Body>
  )
}
export default Header
