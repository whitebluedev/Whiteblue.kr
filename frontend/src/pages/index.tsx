import styled from '@emotion/styled'
import { motion, useAnimation } from 'framer-motion'
import { NextPage } from 'next'
import Image from 'next/image'
import CodeIcon from '@mui/icons-material/Code'
import DnsIcon from '@mui/icons-material/Dns'
import { useMediaQuery } from '@mui/material'
import { useEffect, useState } from 'react'
import { shallowEqual, useSelector } from 'react-redux'
import Nav from 'src/components/Nav/Nav'
import Footer from 'src/components/Footer'

const Body = styled(motion.div)`
  width: 100%;
  display: flex;
  flex-direction: column;
`

const Title = styled(motion.div)`
  height: 45rem;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

  & .image {
    border: 0.8rem solid #ffffff;
    border-radius: 3rem;
    padding: 2rem;
  }

  & .titleText {
    font-size: 5.8rem;
    font-weight: 800;
    margin-top: 2.4rem;
  }

  & .subText {
    font-size: 1.8rem;
    font-weight: 700;
  }

  @media (max-width: 600px) {
    height: 36rem;

    & .titleText {
      font-size: 4rem;
    }

    & .subText {
      font-size: 1.5rem;
    }
  }
`

const Intro = styled(motion.div)`
  height: 35rem;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background-color: white;

  & .titleText {
    font-size: 1.6rem;
  }

  & .container {
    height: 15rem;
    width: 50%;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    margin-top: 5.5rem;
  }

  & .mark {
    border-radius: 50%;
    height: 13rem;
    width: 13rem;
    background-color: #f6f6f6;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  & .markText {
    margin-top: 1.4rem;
    font-size: 1.9rem;
    font-weight: 710;
  }

  @media (max-width: 600px) {
    height: 22rem;

    & .titleText {
      font-size: 1rem;
    }

    & .container {
      width: 100%;
      height: 10rem;
      margin-top: 2rem;
    }

    & .mark {
      border-radius: 50%;
      height: 7.5rem;
      width: 7.5rem;
    }

    & .markText {
      margin-top: 0.8rem;
      font-size: 1.1rem;
    }
  }
`

const Section = styled(motion.div)`
  height: 50rem;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
`

const FooterWraaper = styled(motion.div)`
  height: 6rem;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`

const Index: NextPage = () => {
  const isMobile = useMediaQuery('(max-width:600px)')
  const logoAnimation = useAnimation()

  useEffect(() => {
    logoAnimation.start({
      scale: 1,
      filter: 'blur(0px) brightness(100%)',
      borderRadius: '20%',
      transition: { duration: 1.5 },
    })
  }, [logoAnimation])

  return (
    <Body>
      <Nav />

      <Title>
        <motion.div
          className="image"
          initial={{
            scale: 1.15,
            filter: 'blur(3px) brightness(60%)',
            borderRadius: '5%',
          }}
          animate={logoAnimation}
          onAnimationComplete={() => {
            logoAnimation.start({
              y: -10,
              transition: {
                repeat: Infinity,
                duration: 1,
                repeatType: 'reverse',
              },
            })
          }}
        >
          <Image
            src="/image/Whiteblue.png"
            alt="Whiteblue"
            width={isMobile ? 180 : 250}
            height={isMobile ? 180 : 250}
          />
        </motion.div>
        <motion.div
          className="titleText"
          initial={{ filter: 'blur(1px)' }}
          animate={{
            filter: 'blur(0px)',
            transition: { duration: 1, delay: 1 },
          }}
        >
          <motion.span
            initial={{ color: '#9283ff', marginRight: '1rem' }}
            animate={{
              color: '#ffffff',
              marginRight: '0rem',
              transition: { duration: 2 },
            }}
          >
            White
          </motion.span>
          <motion.span
            initial={{ color: '#ffffff' }}
            animate={{
              color: '#afa5ff',
              transition: { duration: 2 },
            }}
          >
            Blue
          </motion.span>
        </motion.div>
        <motion.div
          className="subText"
          initial={{ filter: 'blur(3px) brightness(60%)' }}
          animate={{
            filter: 'blur(0px) brightness(100%)',
            transition: { duration: 1, delay: 1 },
          }}
        >
          <motion.span
            initial={{ color: '#ffffff' }}
            animate={{
              color: '#afa5ff',
              transition: { duration: 1.5, delay: 1 },
            }}
          >
            SYU Software{' '}
          </motion.span>
          <motion.span
            initial={{ color: '#afa5ff' }}
            animate={{
              color: '#ffffff',
              transition: { duration: 1.5, delay: 1 },
            }}
          >
            Team
          </motion.span>
        </motion.div>
      </Title>
      <Intro>
        <motion.div
          className="titleText"
          initial={{ opacity: 0, filter: 'blur(3px)' }}
          whileInView={{
            opacity: 1,
            filter: 'blur(0px)',
            transition: { duration: 1.5 },
          }}
          animate={{
            y: [-4, 4],
            transition: {
              repeat: Infinity,
              duration: 1.3,
              repeatType: 'reverse',
              delay: 0.2,
            },
          }}
          viewport={{ once: true }}
        >
          <span
            style={{ fontWeight: 700, fontSize: isMobile ? '1.4rem' : '2rem' }}
          >
            Whiteblue
          </span>
          는 삼육대학교의 SW 동아리로,
          <br />
          스터디와 모의 프로젝트 협업을 통해{' '}
          <span
            style={{
              fontWeight: 600,
              fontSize: isMobile ? '1.4rem' : '2rem',
            }}
          >
            Web
          </span>
          에 대해 공부하는 동아리입니다.
        </motion.div>
        <motion.div
          className="container"
          initial={{ opacity: 0, filter: 'blur(3px)' }}
          whileInView={{
            opacity: 1,
            filter: 'blur(0px)',
            transition: { duration: 1 },
          }}
          viewport={{ once: true }}
        >
          <motion.div>
            <motion.div
              className="mark"
              initial={{ borderRadius: '20%' }}
              whileInView={{
                borderRadius: '50%',
                transition: { duration: 1 },
              }}
              viewport={{ once: true }}
            >
              <motion.div
                initial={{ opacity: 0, filter: 'blur(3px)' }}
                whileInView={{
                  opacity: 1,
                  filter: 'blur(0px)',
                  transition: { duration: 0.5, delay: 1 },
                }}
                whileHover={{
                  scale: 1.1,
                  transition: { duration: 0.5, type: 'spring' },
                }}
                viewport={{ once: true }}
              >
                <CodeIcon sx={{ fontSize: isMobile ? '5rem' : '8rem' }} />
              </motion.div>
            </motion.div>
            <motion.div
              className="markText"
              initial={{ opacity: 0, filter: 'blur(2px)' }}
              whileInView={{
                opacity: 1,
                filter: 'blur(0px)',
                transition: { duration: 0.5, delay: 1 },
              }}
              viewport={{ once: true }}
            >
              Frontend
            </motion.div>
          </motion.div>
          <motion.div>
            <motion.div
              className="mark"
              initial={{ borderRadius: '20%' }}
              whileInView={{
                borderRadius: '50%',
                transition: { duration: 1 },
              }}
              viewport={{ once: true }}
            >
              <motion.div
                initial={{ opacity: 0, filter: 'blur(3px)' }}
                whileInView={{
                  opacity: 1,
                  filter: 'blur(0px)',
                  transition: { duration: 0.5, delay: 1.5 },
                }}
                whileHover={{
                  scale: 1.1,
                  transition: { duration: 0.5, type: 'spring' },
                }}
                viewport={{ once: true }}
              >
                <DnsIcon sx={{ fontSize: isMobile ? '4rem' : '6.5rem' }} />
              </motion.div>
            </motion.div>
            <motion.div
              className="markText"
              initial={{ opacity: 0, filter: 'blur(2px)' }}
              whileInView={{
                opacity: 1,
                filter: 'blur(0px)',
                transition: { duration: 0.5, delay: 1.5 },
              }}
              viewport={{ once: true }}
            >
              Backend
            </motion.div>
          </motion.div>
        </motion.div>
      </Intro>
      <Section></Section>
      <Footer />
    </Body>
  )
}

export default Index
