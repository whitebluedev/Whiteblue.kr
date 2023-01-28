import * as React from 'react';
// React Library
import {
  useEffect,
  useState,
} from 'react'
import { useDispatch, useSelector } from 'react-redux'
import axios, { AxiosError, AxiosResponse } from 'axios'
// Custom Library
import { Account, setLogin, setAccount } from 'src/redux/reducers/userReducer'
import Router from 'next/router'
import NavFixed from 'src/components/Nav/NavFixed';
import Footer from 'src/components/Footer';
// MUI Library
import {
  Box,
  Paper,
} from '@mui/material';

import styled from '@emotion/styled'
import { motion, useAnimation } from 'framer-motion'


const Body = styled(motion.div)`
  width: 100%;
  display: flex;
  flex-direction: column;
  background-color: #fff;
`

const Profile = () => {
  return (
    <Body>
      <NavFixed />
      {/* Header */}

      {/* Profile BOX */}
      <Box
        sx={{
          display: "flex",
          flexWrap: "wrap",
          justifyContent: "center",

          '& > :not(style)': {
            mt: 3.5,
            width: "66%",
            height: 500,
          },
        }}
      >
        <Paper elevation={3} />
      </Box>
      <Footer />

      {/* LogIn Checking */}
    </Body>
  )
}

export default Profile