import * as React from 'react';
import styled from '@emotion/styled'
import { motion } from 'framer-motion'
// React Library
import { shallowEqual, useSelector } from 'react-redux'
import { FunctionComponent } from 'react'
// Custom Library
import Router from 'next/router'
import Image from 'next/image'
import AccountMenu from './AccountMenu';
// MUI Library
import {
  Toolbar,
  Typography,
  useMediaQuery,
} from '@mui/material'


const Body = styled(motion.div)`
  width: 100%;
  position: fixed;
  padding: 0.3% 0;
  backdrop-filter: saturate(100%) blur(50px);

`
const LogoWrapper = styled(motion.div)`
  margin-left: 16%;
  & :hover {
    cursor: pointer;
  }

  @media (max-width: 600px) {
    margin-left: 0;
  }
`
const MenuWrapper = styled(motion.div)`
  margin-right: 16%;

  @media (max-width: 600px) {
    margin-right: 0;
  }
`

const Nav: FunctionComponent = () => {
  const account = useSelector((store: Store) => {
    return store.user.account
  }, shallowEqual)

  const isMobile = useMediaQuery('(max-width:600px)');

  return (
    <Body>
      <Toolbar>
        <LogoWrapper>
          <Image
            src='/image/mainLogo.png'
            width={isMobile ? 90 : 210}
            height={isMobile ? 20 : 45}
            alt='mainLogo'
            onClick={() => {
              Router.push('/')
            }}
          />

        </LogoWrapper>
        <Typography sx={{ flexGrow: 1 }} />
        <MenuWrapper>
          <AccountMenu />
        </MenuWrapper>
      </Toolbar>
    </Body>
  )
}

export default Nav
