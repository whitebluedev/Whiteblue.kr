import * as React from 'react'
import styled from '@emotion/styled'
import { motion } from 'framer-motion'
// React Library
import { FunctionComponent } from 'react'
// Custom Library
import Router from 'next/router'
import Image from 'next/image'
import AccountMenu from './AccountMenu'
// MUI Library
import {
  AppBar,
  CssBaseline,
  Toolbar,
  Typography,
} from '@mui/material'


const Body = styled(motion.div)`
  width: 100%;
  position: fixed;
`

const Nav: FunctionComponent = () => {
  return (
    <>
      <CssBaseline />
      <Body>
        <AppBar
          sx={{
            p: 0.5,
            bgcolor: '#fff',
            boxShadow: 0,
            '@media (max-width: 600px)': {
              p: 0,
            }
          }}
        >
          <Toolbar>
            {/* Main Logo */}
            <Typography
              sx={{
                color: '#000',
                ml: '20%',
                fontSize: '1.5rem',
                fontFamily: 'GmarketSans',
                fontWeight: 100,
                '&:hover': {
                  cursor: 'pointer'
                },
                '@media (max-width: 600px)': {
                  fontSize: '1.2rem',
                  ml: '0'
                }
              }}
              onClick={() => {
                Router.push('/')
              }}
            >
              WHITEBLUE
            </Typography>
            <Typography sx={{ flexGrow: 1 }} />
            {/* User Menu */}
            <Typography
              sx={{
                mr: '19%',
                '@media (max-width: 600px)': {
                  mr: '0'
                }
              }}>
              <AccountMenu />
            </Typography>
          </Toolbar>
        </AppBar>
      </Body>
    </>
  )
}

export default Nav
