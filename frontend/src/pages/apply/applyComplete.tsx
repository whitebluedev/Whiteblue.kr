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
} from '@mui/material'
import { motion } from 'framer-motion'


const Body = styled(motion.div)`
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
  align-items: center;
  background-color: #F6F6F6;

  & .router {
    height: 30px;
    color: #4378FF;
    font-family: 'ChosunBg';
    font-size: 1rem;
    font-weight: 100;
    margin: 5% 8%;
    text-align: left;
    width: 18%;
  }

  & .content {
    height: 30px;
    font-size: 1rem;
    text-align: left;
    padding: 2% 8%;
    font-family: 'Pretendard';
  }

  & .detail {
    text-align: left;
    padding: 5% 8%;
    font-size: 0.8rem;
    font-weight: 100;
  }

  @media (max-width: 600px) {

  }
`

const applyComplete = () => {
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
      <Header name="지원서" />
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
              제출 완료!
            </Typography>
            <Typography className='content'>
              작성하신 지원서가 정상적으로 제출되었습니다.
            </Typography>
            <Typography className='detail'>
              ~~ 추가 안내 설명 등등 ~~
            </Typography>
            {/* Home Router */}
            <Typography
              className='router'
              sx={{
                '&:hover': {
                  cursor: 'pointer'
                },
              }}
              onClick={() => {
                Router.push('/')
              }}
            >
              홈으로 돌아가기
            </Typography>
          </Paper>
        </Box>
      </Box>
      {/* LogIn Checking */}
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
      {/* Apply Checking */}
    </Body>
  )
}

export default applyComplete
