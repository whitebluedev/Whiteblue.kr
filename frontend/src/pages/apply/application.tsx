import * as React from 'react'
import styled from '@emotion/styled'
// React Library
import { useEffect, useState } from 'react'
import Router from 'next/router'
// Redux & axios
import { useSelector } from 'react-redux'
// Custom Library
import NavFixed from 'src/components/Nav/NavFixed'
import Header from 'src/components/Header'
import Footer from 'src/components/Footer'
// MUI Library
import {
  Button,
  Dialog, DialogActions, DialogTitle, DialogContent, DialogContentText,
  Box,
  Step, Stepper, StepLabel,
  Typography,
  Paper,
  Divider,
} from '@mui/material'
import { motion, useAnimation } from 'framer-motion'


const Body = styled(motion.div)`
  display: flex;
  flex-direction: column;
  height: 100vh;
  width: 100%;
  align-items: center;

  @media (max-width: 600px) {

  }
`

const Application = () => {
  {/* LogIn Checking */ }
  const isLogin = useSelector((store: Store) => {
    return store.user.login
  })
  const [introEnd, setIntroEnd] = useState(false)
  const introAnimation_1 = useAnimation()
  const introAnimation_2 = useAnimation()
  useEffect(() => {
    const intro = async () => {
      await introAnimation_1.start({
        opacity: 1,
        filter: 'blur(0px) brightness(100%)',
        transition: { duration: 1.3 },
      })
      await introAnimation_1.start({
        top: '8%',
        scale: 0.6,
        transition: { duration: 1, ease: 'easeInOut' },
      })
      introAnimation_1.start({
        y: -8,
        transition: {
          repeat: Infinity,
          duration: 1.5,
          repeatType: 'reverse',
        },
      })
      setIntroEnd(true)
    }
    intro()
  }, [introAnimation_1, introAnimation_2])

  {/* Form Stepper */ }
  const steps = ['지원 정보', '자기소개서', '제출'];
  const [activeStep, setActiveStep] = React.useState(0);

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };
  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };
  const handleReset = () => {
    setActiveStep(0);
  };


  return (
    <Body>
      <NavFixed />
      {/* Header */}

      {/* Application Form */}
      <Box sx={{ width: '66%', mt: 10 }}>
        <Stepper activeStep={activeStep}>
          {steps.map((label) => {
            const stepProps: { completed?: boolean } = {};
            return (
              <Step key={label} {...stepProps}>
                <StepLabel>{label}</StepLabel>
              </Step>
            );
          })}
        </Stepper>

        {/* Form BOX */}
        <Box
          sx={{
            display: 'flex',
            flexWrap: 'wrap',
            mt: 3.5,
            width: "100%",
            height: 500,
            '& > :not(style)': {
              width: "100%",
              height: "100%",
            },
          }}
        >
          <Paper elevation={3} />
        </Box>
        {activeStep === steps.length ? (
          <React.Fragment>
            <Typography sx={{ mt: 2, mb: 1 }}>
              제출하시겠습니까?
            </Typography>
            <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
              <Box sx={{ flex: '1 1 auto' }} />
              <Button onClick={handleReset}>
                뒤로
              </Button>
              <Button type="submit" sx={{ color: "green" }}>
                제출
              </Button>
            </Box>
          </React.Fragment>
        ) : (
          <React.Fragment>
            <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
              <Button
                color="inherit"
                disabled={activeStep === 0}
                onClick={handleBack}
                sx={{ mr: 1 }}
              >
                뒤로
              </Button>
              <Box sx={{ flex: '1 1 auto' }} />
              <Button onClick={handleNext}>
                {activeStep === steps.length - 1 ? '다음' : '다음'}
              </Button>
            </Box>
          </React.Fragment>
        )}
      </Box>
      
      <Footer />

      {/* Interaction */}
      <Dialog
        fullWidth={true}
        maxWidth="xs"
        open={introEnd}
        onClose={() => {
          setIntroEnd(false)
          if (isLogin) {
            introAnimation_2.start({
              opacity: 1,
              filter: 'blur(0px) brightness(100%)',
              transition: { delay: 0.3, duration: 1.5 },
            })
          } else {
            Router.push('/');
          }
        }}
      >
        <DialogTitle sx={{ fontWeight: 800 }}>
          신청 안내
        </DialogTitle>
        <DialogContent>
          <DialogContentText sx={{ color: 'black' }}>
            {isLogin ? '~ 지원서 작성 설명 ~' : '로그인이 필요한 서비스입니다.'}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button
            sx={{ fontFamily: 'nanumSquare' }}
            onClick={() => {
              setIntroEnd(false)
              if (isLogin) {
                introAnimation_2.start({
                  opacity: 1,
                  filter: 'blur(0px) brightness(100%)',
                  transition: { delay: 0.3, duration: 1.5 },
                })
              } else {
                Router.push('/');
              }
            }}
          >
            확인
          </Button>
        </DialogActions>
      </Dialog>
    </Body>
  )
}

export default Application
