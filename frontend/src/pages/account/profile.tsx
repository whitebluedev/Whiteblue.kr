import styled from '@emotion/styled'
// React Library
import { useEffect, useState } from 'react'
import Router from 'next/router'
// Redux & axios
import { useSelector } from 'react-redux'
// Custom Library
import Header from 'src/components/Header'
import HideNav from 'src/components/Nav/HideNav'
// MUI Library
import {
  Box,
  Button,
  Dialog, DialogActions, DialogTitle, DialogContent, DialogContentText,
  Paper,
  Typography,
  TextField,
} from '@mui/material'
import { motion, useAnimation } from 'framer-motion'


const Body = styled(motion.div)`
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
  align-items: center;
  background-color: #F6F6F6;

  @media (max-width: 600px) {

  }
`

const Profile = () => {
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
      <HideNav />
      <Header name="내 정보" />
      {/* Profile Box */}
      <Box sx={{ width: '50%' }}>
        {/* Contents Box */}
        <Box
          sx={{
            display: 'flex',
            flexWrap: 'wrap',
            width: "100%",
            height: 500,
            '& > :not(style)': {
              width: "100%",
              height: "100%",
            },
          }}
        >
          <Paper
            elevation={3}
            sx={{
              height: '660px',
              mb: 5,
            }}
          >
            <Typography
              sx={{
                height: '10px',
                bgcolor: '#4378FF',
                borderRadius: '5px 5px 0 0'
              }}
            />
            <Typography
              sx={{
                height: '30px',
                fontFamily: 'Score',
                fontSize: '1.9rem',
                fontWeight: '900',
                textAlign: 'left',
                padding: '5% 8% 8% 8%'
              }}
            >
              Profile
            </Typography>
            <Typography className='content'>
              Example content
            </Typography>
          </Paper>
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

export default Profile
