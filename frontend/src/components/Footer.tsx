import * as React from 'react'
import styled from '@emotion/styled'
import { motion } from 'framer-motion'
// React Library
import { FunctionComponent } from 'react'
import Image from 'next/image'
// Custom Library

// MUI Library
import {
  Toolbar,
  Tooltip,
  Typography,
  useMediaQuery,
} from '@mui/material'

const Body = styled(motion.div)`
  width: 100%;
  padding: 1% 0;
  background-color: #fff;

  @media (max-width: 600px) {
    width: 100%;
  }
`

const LogoWrapper = styled(motion.div)`
  margin-left: 9%;
  text-align: left;
  color: #9d9d9d;

  @media (max-width: 600px) {
    margin-left: 0;
  }
`

const CopyrightWrapper = styled(motion.div)`
  margin-left: 5%;
`

const SNSWrapper = styled(motion.div)`
  margin-right: 11%;

  & :hover {
    cursor: pointer;
  }

  @media (max-width: 600px) {
    margin-right: 0;
  }
`

const Pointer = styled(motion.div)`
  margin: 0 10%;

  & :hover {
    cursor: pointer;
  }
`

const Footer: FunctionComponent = () => {
  const isMobile = useMediaQuery('(max-width:600px)')

  return (
    <Body>
      <Toolbar>
        <LogoWrapper>
          <Toolbar>
            <CopyrightWrapper>
              <Typography
               variant="overline"
               sx={{ 
                fontFamily: 'GmarketSans',
                fontSize: '0.9em'
                }}
              >
                © 2023 WHITEBLUE DEV
              </Typography>
              <Typography sx={{ fontSize: '0.7rem' }}>
                서울특별시 노원구 화랑로 815 삼육대학교 다니엘관 304호
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </Typography>
            </CopyrightWrapper>
          </Toolbar>
        </LogoWrapper>

        <Typography sx={{ flexGrow: 1 }} />

        <SNSWrapper className="sns">
          <Toolbar>
            <Tooltip title={isMobile ? '' : '깃허브'}>
              <Pointer>
                <Image
                  src="/image/githubLogo.png"
                  width={40}
                  height={40}
                  alt="github"
                  onClick={() =>
                    window.open('https://github.com/whitebluedev', '_blank')
                  }
                />
              </Pointer>
            </Tooltip>
            <Tooltip title={isMobile ? '' : '블로그'}>
              <Pointer>
                <Image
                  src="/image/blogLogo.png"
                  width={40}
                  height={40}
                  alt="blog"
                  onClick={() => window.open('/blog_url', '_blank')}
                />
              </Pointer>
            </Tooltip>
          </Toolbar>
        </SNSWrapper>
      </Toolbar>
    </Body>
  )
}
export default Footer
