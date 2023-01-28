import * as React from 'react';
import styled from '@emotion/styled'
import { motion } from 'framer-motion'
// React Library
import {
  useCallback,
  useState,
  FormEvent,
} from 'react'
import { NextPage } from 'next';
import Router from 'next/router'
import Image from 'next/image';
// Redux & axios
import { useDispatch } from 'react-redux'
import { Account, setLogin, setAccount } from 'src/redux/reducers/userReducer'
import axios, { AxiosError, AxiosResponse } from 'axios'
// MUI Library
import {
  Box,
  Button, IconButton,
  useMediaQuery,
  Divider,
  FormControl,
  Input, InputLabel, InputAdornment,
  Typography,
} from '@mui/material';
import {
  Visibility, VisibilityOff,
} from '@mui/icons-material';
// Custom Library
import Footer from 'src/components/Footer';


const Body = styled(motion.div)`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
`

const Container = styled(motion.div)`
  padding: 7% 37%;

  & .logInBox {
    margin: 2%;
  }
  & .btn {
    height: 50px;
    border-radius: 8px;
    font-size: 1rem;
  }

  @media (max-width: 600px) {
    padding: 30% 10%;
  }
`

const Title = styled(motion.div)`
  padding-top: 5%;
  font-weight: bold;
  font-size: 2.5em;
`

const Pointer = styled(motion.div)`
  & :hover {
    cursor: pointer;
  }
`

const LogIn: NextPage = () => {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const dispatch = useDispatch()

  const logInHandler = useCallback(
    (event: FormEvent<HTMLFormElement>) => {
      event.preventDefault()
      axios
        .post('  ', {
          username: username,
          password: password,
        })
        .then((response: AxiosResponse<Account>) => {
          dispatch(setLogin(true))
          dispatch(setAccount(response.data))
          Router.replace('/')
        })
    },
    [username, password, dispatch]
  )

  const [showPassword, setShowPassword] = React.useState(false);
  const handleClickShowPassword = () => setShowPassword((show) => !show);
  const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
  };
  const isMobile = useMediaQuery('(max-width:600px)');

  return (
    <Body>
      <Container>
        <Pointer>
          <Image
            src='/image/favicon.png'
            width={isMobile ? 50 : 90} height={isMobile ? 50 : 90}
            alt='Title'
            onClick={() => {
              Router.push('/')
            }}
          />
        </Pointer>
        <Title> 로그인 </Title>

        {/* LogIn Box */}
        <Box
          className='logInBox'
          component="form"
          onSubmit={logInHandler}
          noValidate
          autoComplete="on"
        >
          {/* username */}
          <FormControl sx={{ mt: 3, mb: 2, width: '100%' }} variant="standard">
            <InputLabel>아이디*</InputLabel>
            <Input
              required
              value={username}
              onChange={(e) => {
                setUsername(e.target.value)
              }}
            />
          </FormControl>
          <div />

          {/* password */}
          <FormControl sx={{ mt: 3, mb: 2, width: '100%' }} variant="standard">
            <InputLabel>비밀번호*</InputLabel>
            <Input
              required
              value={password}
              type={showPassword ? 'text' : 'password'}
              endAdornment={
                <InputAdornment position="end">
                  <IconButton
                    onClick={handleClickShowPassword}
                    onMouseDown={handleMouseDownPassword}
                  >
                    {showPassword ? <VisibilityOff /> : <Visibility />}
                  </IconButton>
                </InputAdornment>
              }
              autoComplete="current-password"
              onChange={(e) => {
                setPassword(e.target.value)
              }}
            />
          </FormControl>
          <div />

          <Button
            className='btn'
            type="submit"
            variant="contained"
            fullWidth
            sx={{ mt: 3.5, }}
          >
            로그인
          </Button>

          {/* SignIn */}
          <Divider
            textAlign="center"
            sx={{ mt: 5, mb: 2, }}>
            회원 가입
          </Divider>

          <Button
            className='btn'
            variant="contained"
            fullWidth
            color="success"
            sx={{ mb: 2, backgroundColor: "#000", color: "#fff" }}
            onClick={() => {
              Router.push('/kakao LogIn URL')
            }}
          >
            카카오톡 간편계정 만들기
          </Button>

          {/* 아이디 & 비번 찾기 */}
          <Box
            sx={{
              display: 'flex',
              alignItems: 'center',
              justifyContent: "center",
              mt: 1,
              '& svg': { m: 1.5, },
              '& hr': { mx: 1, },
            }}
          >
            <Typography variant='caption'>
              아이디 찾기
            </Typography>
            <Divider orientation="vertical" flexItem />
            <Typography variant='caption'>
              비밀번호 찾기
            </Typography>
          </Box>
        </Box>
      </Container>
      <Footer />
    </Body>
  )
}

export default LogIn