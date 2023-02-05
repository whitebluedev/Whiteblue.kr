import * as React from 'react'
import styled from '@emotion/styled'
import { motion, transform } from 'framer-motion'
// React Library
import { useCallback, useState, FormEvent, MouseEvent } from 'react'
import { NextPage } from 'next'
import Router from 'next/router'
// Redux & axios
import { useDispatch } from 'react-redux'
import { Profile, setLogin, setAccount } from 'src/redux/reducers/userReducer'
import axios, { AxiosError, AxiosResponse } from 'axios'
// MUI Library
import {
  Box,
  Button,
  Divider,
  FormControl,
  IconButton,
  Input,
  InputLabel,
  InputAdornment,
  Typography,
  useMediaQuery,
} from '@mui/material'
import { Visibility, VisibilityOff } from '@mui/icons-material'
// Custom Library

const Body = styled(motion.div)`
  width: 100%;
  height: 88%;
  display: flex;
  flex-direction: column;
`

const Container = styled(motion.div)`
  padding: 7% 36%;

  @media (max-width: 600px) {
    padding: 20% 3%;
  }
`

const LogIn: NextPage = () => {
  const [userID, setUserID] = useState('')
  const [userPWD, setPassword] = useState('')
  const dispatch = useDispatch()

  const logInHandler = useCallback(
    (event: FormEvent<HTMLFormElement>) => {
      event.preventDefault()
      axios
        .post('http://localhost:8888/user', {
          userID: userID,
          userPWD: userPWD,
        })
        .then((response: AxiosResponse<Profile>) => {
          dispatch(setLogin(true))
          dispatch(setAccount(response.data))
          Router.replace('/')
        })
    },
    [userID, userPWD, dispatch]
  )

  const [showPassword, setShowPassword] = useState(true)
  const handleClickShowPassword = () => setShowPassword((show) => !show)
  const handleMouseDownPassword = (
    event: MouseEvent<HTMLButtonElement>
  ) => {
    event.preventDefault()
  }
  const isMobile = useMediaQuery('(max-width:600px)')

  return (
    <Body>
      <Container>
        <Typography
          sx={{
            fontSize: '2em',
            fontFamily: 'GmarketSans',
            '&:hover': {
              cursor: 'pointer'
            }            
          }}
          onClick={() => {
            Router.push('/')
          }}
        >
          WHITEBLUE
        </Typography>

        {/* LogIn Box */}
        <Box
          component="form"
          onSubmit={logInHandler}
          noValidate
          autoComplete="off"
          sx={{
            height: '60%',
            m: '5%',
            border: 'solid 0.1rem',
            borderRadius: '10px',
            borderColor: '#D5D5D5'
          }}
        >
          {/* userID */}
          <FormControl sx={{ mt: 4, mb: 2, width: '80%' }} variant="standard">
            <InputLabel>아이디</InputLabel>
            <Input
              required
              value={userID}
              onChange={(e) => {
                setUserID(e.target.value)
              }}
            />
          </FormControl>
          <div />

          {/* userPWD */}
          <FormControl sx={{ mt: 3, mb: 2, width: '80%' }} variant="standard">
            <InputLabel>비밀번호</InputLabel>
            <Input
              required
              value={userPWD}
              type={showPassword ? 'password' : 'text'}
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
            type="submit"
            variant="contained"
            fullWidth
            sx={{
              mt: 6,
              width: '80%',
              height: '55px',
              borderRadius: '10px',
            }}
          >
            <Typography
             sx={{ 
              fontSize: '1.2rem',
              fontWeight: 900
              }}>
                로그인
            </Typography>
          </Button>
          </Box>

          {/* SignIn */}
          <Divider           
           sx={{
            mt: 5,
            mb: 2,
            width: '100%',
            color: '#BDBDBD'
           }}
          >
            회원 가입
          </Divider>
          <Button
            className="btn"
            variant="contained"
            fullWidth
            sx={{
              mb: 2,
              backgroundColor: '#000',
              color: '#fff',
              width: '73%',
              height: '55px',
              borderRadius: '10px',
              '&:hover': {
                backgroundColor: '#000'
              }  
              
            }}
            onClick={() => {
              Router.push('http://localhost:8000/oauth2/authorization/kakao')
            }}
          >
            <Typography
             sx={{ 
              fontWeight: 900
              }}>
                카카오톡 간편계정 만들기
            </Typography>
          </Button>

          {/* 아이디 & 비번 찾기 */}
          <Box
            sx={{
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              mt: 1,
              '& svg': { m: 1.5 },
              '& hr': { mx: 1 },
            }}
          >
            <Typography variant="caption">아이디 찾기</Typography>
            <Divider orientation="vertical" flexItem />
            <Typography variant="caption">비밀번호 찾기</Typography>
          </Box>
        
      </Container>
    </Body>
  )
}

export default LogIn
