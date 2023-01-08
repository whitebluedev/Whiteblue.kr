import styled from '@emotion/styled'
import { Button, DialogContentText } from '@mui/material'
import Dialog from '@mui/material/Dialog'
import DialogActions from '@mui/material/DialogActions'
import DialogContent from '@mui/material/DialogContent'
import DialogTitle from '@mui/material/DialogTitle'
import { motion, useAnimation } from 'framer-motion'
import Router from 'next/router'
import { useEffect, useState } from 'react'
import { useSelector } from 'react-redux'

const Body = styled(motion.div)`
  display: flex;
  flex-direction: column;
  background-color: #292929;
  height: 100vh;
  width: 100%;
  justify-content: center;
  align-items: center;

  & .intro {
    font-size: 5.8rem;
    font-weight: 700;
    position: absolute;
  }

  @media (max-width: 600px) {
    & .intro {
      font-size: 4rem;
    }
  }
`

const Application = () => {
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

  return (
    <Body>
      <motion.div
        className="intro"
        style={{ color: 'white' }}
        initial={{ opacity: 0, filter: 'blur(3px) brightness(60%)' }}
        animate={introAnimation_1}
      >
        동아리 신청
      </motion.div>
      <motion.div
        className="intro"
        style={{ color: 'white' }}
        initial={{ opacity: 0, filter: 'blur(3px) brightness(60%)' }}
        animate={introAnimation_2}
      >
        신청 폼
      </motion.div>

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
            Router.push('/signIn')
          }
        }}
      >
        <DialogTitle fontFamily="nanumSquare" sx={{ fontWeight: 800 }}>
          신청 안내
        </DialogTitle>
        <DialogContent>
          <DialogContentText fontFamily="nanumSquare" sx={{ color: 'black' }}>
            {isLogin ? '어쩌구저쩌구' : '로그인이 필요한 서비스입니다.'}
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
                Router.push(process.env.LOGIN_URL as string)
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
