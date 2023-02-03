import styled from '@emotion/styled'
// React Library
import { useEffect, useState, Fragment } from 'react'
import Router from 'next/router'
// Redux & axios
import { useSelector } from 'react-redux'
// Custom Library
import Header from 'src/components/Header'
import Nav from 'src/components/Nav/Nav'
// MUI Library
import {
  Box,
  Button,
  Card, CardActions, CardContent,
  Dialog, DialogActions, DialogTitle, DialogContent, DialogContentText,
  Divider,
  FormControl,
  Input, InputLabel, InputAdornment,
  Paper,
  Typography,
  TextField,
} from '@mui/material'
import { AccountCircle } from '@mui/icons-material'
import { motion, useAnimation } from 'framer-motion'
import { color } from '@mui/system'


const Body = styled(motion.div)`
  display: flex;
  flex-direction: column;
  height: 240%;
  width: 100%;
  align-items: center;
  background-color: #F6F6F6;

  & .contentTitle {
    height: 30px;
    font-family: 'ChosunBg';
    font-size: 1.3rem;
    font-weight: 100;
    color: #4378FF;
    text-align: left;
    padding: 8%;
  }

  @media (max-width: 600px) {

  }
`

const Application = () => {

  {/* LogIn Checking */ }
  const isLogin = useSelector((store: Store) => {
    return store.user.login
  })
  const [introEnd, setIntroEnd] = useState(false)
  useEffect(() => {
    const intro = async () => {
      setIntroEnd(true)
    }
    intro()
  })


  return (
    <Body>
      <Nav />
      <Header name="지원하기" />
      {/* From Wrapper */}
      <Box sx={{ width: '50%' }}>
        {/* Form Contents */}
        <Box
          sx={{
            width: '100%',
            height: '20px'
          }}
        >
          {/* 1. 개인정보 */}
          <Paper
            elevation={3}
            sx={{
              height: '450px',
              mb: 5
            }}
          >
            <Typography className='contentTitle'> <b>|</b> 개인정보 </Typography>
            {/* Input Contents */}
          </Paper>
          {/* 2. 포트폴리오 */}
          <Paper
            elevation={3}
            sx={{
              height: '450px',
              mb: 5
            }}
          >
            <Typography className='contentTitle'> <b>|</b> 포트폴리오 </Typography>
            {/* Input Contents */}
          </Paper>
          {/* 3. 자기소개서 */}
          <Paper
            elevation={3}
            sx={{
              height: '600px',
              mb: 4
            }}
          >
            <Typography className='contentTitle'> <b>|</b> 자기소개서 </Typography>
            {/* Input Contents */}
          </Paper>
          {/* Submit Button */}
          <Button
            type="submit"
            variant="contained"
            fullWidth
            sx={{
              mt: 5,
              width: '50%',
              height: '55px',
              borderRadius: '10px',
            }}
          >
            <Typography
              sx={{
                fontSize: '1.2rem',
                fontWeight: 900
              }}>
              제출하기
            </Typography>
          </Button>
        </Box>
      </Box>
      {/* Interaction */}
      {!isLogin && (
        <>
          <Dialog
            fullWidth={true}
            maxWidth="xs"
            open={introEnd}
            onClose={() => {
              Router.push('/');
            }}
          >
            <DialogTitle sx={{ fontWeight: 800 }}>
              안내
            </DialogTitle>
            <DialogContent>
              <DialogContentText sx={{ color: 'black' }}>
                로그인이 필요한 페이지입니다.
              </DialogContentText>
            </DialogContent>
            <DialogActions>
              <Button
                onClick={() => {
                  if (!isLogin) {
                    Router.push('/account/login');
                  }
                }}
              >
                확인
              </Button>
            </DialogActions>
          </Dialog>
        </>
      )}
    </Body>
  )
}

export default Application
