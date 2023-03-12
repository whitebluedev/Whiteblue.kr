// React Library
import { ReactElement } from 'react';
// Custom Library
import styled from '@emotion/styled'
import { motion } from 'framer-motion'
import AccountMenu from './AccountMenu';
// MUI Library
import {
  AppBar,
  CssBaseline,
  Slide,
  Toolbar,
  Typography,
  useScrollTrigger,
} from '@mui/material';
import Router from 'next/router';


interface Props {
  window?: () => Window;
  children: ReactElement;
}

function HideOnScroll(props: Props) {
  const { children, window } = props;
  const trigger = useScrollTrigger({
    target: window ? window() : undefined,
  });

  return (
    <Slide appear={false} direction="down" in={!trigger}>
      {children}
    </Slide>
  );
}

export default function HideNav(props: Props) {
  return (
    <>
      <CssBaseline />
      <HideOnScroll {...props}>
        <AppBar
          sx={{
            p: 0.5,
            bgcolor: '#fff',
            boxShadow: 0,
            borderBottom: '1px solid #F6F6F6',
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
                ml: '10%',
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
                mr: '10%',
                '@media (max-width: 600px)': {
                  mr: '0'
                }
              }}>
              <AccountMenu />
            </Typography>
          </Toolbar>        
        </AppBar>
      </HideOnScroll>
      <Toolbar />
    </>
  );
}