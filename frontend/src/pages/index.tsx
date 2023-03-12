import styled from '@emotion/styled'
import { motion } from 'framer-motion'
import { NextPage } from 'next'
import Image from 'next/image'
import Router from 'next/router'
import { 
  Button,
  Typography,
  useMediaQuery,
 } from '@mui/material'
import Nav from 'src/components/Nav/Nav'

const Body = styled(motion.div)`
  width: 100%;
  display: flex;
  flex-direction: column;
`

const Title = styled(motion.div)`
  height: 65rem;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  background-color: #4378FF;

  & .titleText {
    font-size: 9rem;
    font-weight: 800;
    font-family: 'OA';
    color: #fff;
    width: 70%;
    height: 11rem;
    text-align: left;
  }

  & .subText {
    font-size: 1.3rem;
    font-weight: 800;
    font-family: 'LINE';
    color: #fff;
    width: 68%;
    text-align: left;
    padding-top: 2%;
  }

  & .typingText {
    font-size: 7.5rem;
    font-weight: 800;
    font-family: 'OA';
    color: #00126F;
    width: 70%;
    text-align: left;

    animation: typing 2s steps(22), blink .5s step-end infinite alternate;
    white-space: nowrap;
    overflow: hidden;
    border-right: 2px solid;
  }
  
  @keyframes typing {
    from {
      width: 0
    }
  }
      
  @keyframes blink {
    50% {
      border-color: transparent
    }
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

const Section = styled(motion.div)`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 5% 0;

  & .titleText {
    font-size: 3.5rem;
    font-weight: 600;
    font-family: 'One';
    width: 70%;
    text-align: left;
  }

  & .subText {
    font-size: 1.3rem;
    color: #8A8A8A;
    width: 70%;
    text-align: left;
    margin: 2% 0;
  }

  & .subTitle {
    font-size: 1.5rem;
    font-family: PretendardB;
    color: #4378FF;
    width: 70%;
    text-align: left;
  }

  & .sub {
    font-size: 1rem;
    color: #000;
    font-family: PretendardL;
    width: 70%;
    text-align: left;
    margin: 1% 0 3% 0;
  }
`

const Box1 = styled(motion.div)`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  column-gap: 15px;
  color: #000;  

  & .titleText {
    margin-top: 5%;
    font-size: 1.8rem;
    font-family: 'PretendardB'; 
    margin-left: 13%;  
  }
  & .subText {
    font-size: 1rem;
    font-family: 'PretendardL'; 
    color: #000;  
    margin-left: 13%;
  }
`

const Box2 = styled(motion.div)`
  display: grid;
  grid-template-columns: repeat(2, 2fr);
  column-gap: 30px;

  & .titleText {
    margin-top: 5%;
    font-size: 1.8rem;
    font-family: 'PretendardB'; 
  }
  & .subText {
    font-size: 1rem;
    font-family: 'PretendardL'; 
    color: #000; 
  }
`
const Box3 = styled(motion.div)`
   display: grid;
   grid-template-columns: repeat(4, 2fr);
   column-gap: 50px;
   margin: 2% 0 3% 0;
`


const Index: NextPage = () => {
  const isMobile = useMediaQuery('(max-width:600px)')

  return (
    <Body>
      <Nav />
      <Title>
        <motion.div
          className="titleText"
          initial={{
            opacity: 0,
            filter: 'blur(2px)'
          }}
          whileInView={{
            opacity: 1,
            filter: 'blur(0px)',
            transition: { duration: 1 }
          }}
          animate={{
            y: [40, 0],
            transition: {
              duration: 1.3,
              delay: 0.2
            }
          }}
          viewport={{ once: true }}
        >
            We Become
        </motion.div>
        <motion.div
          className="titleText"
          initial={{
            opacity: 0,
            filter: 'blur(2px)'
          }}
          whileInView={{
            opacity: 1,
            filter: 'blur(0px)',
            transition: { duration: 1, delay: 0.8 }
          }}
          animate={{
            y: [40, 0],
            transition: {
              duration: 1,
              delay: 1
            }
          }}
          viewport={{ once: true }}
        >
            Creative
        </motion.div>
        <motion.div
         className="typingText"
        >
          Web Developer
        </motion.div>

        <motion.div
          className="subText"
        >
          WHITEBLUE는 2022년부터 활동해온 삼육대학교 SW동아리입니다. <br/>
          웹/모바일 개발 스터디와 프로젝트를 주로 활동하고 있습니다.
        </motion.div>
      </Title>
      {/* Section 1 : Intro */}
      <Section style={{ height: '50rem' }}>
        <motion.div
          className="titleText"
          initial={{
            opacity: 0,
            filter: 'blur(2px)'
          }}
          whileInView={{
            opacity: 1,
            filter: 'blur(0px)',
            transition: { duration: 1 }
          }}
          animate={{
            y: [40, 0],
            transition: {
              duration: 1.3,
              delay: 0.2
            }
          }}
          viewport={{ once: true }}
        >
          일상에 도움이 될 수 있는 플랫폼을 <br/>
          만들고자 합니다 
        </motion.div>
        <motion.div
          className="subText"
        >
          WHITEBLUE는 웹/모바일 개발 '프로젝트'를 중심으로 활동합니다. <br/>
          프론트엔드와 백엔드 각 팀은 매주 개발스터디 및 프로젝트/대회출전 준비 등을 하고 있습니다.
        </motion.div>
        <Box1>
          <motion.span>
            <Image src='/image/intro1.png' width={300} height={300} alt='intro1' />
            <Typography className="titleText">프론트엔드</Typography>
            <Typography className="subText">React, Next.JS, Redux 라이브러리 등을 주로 사용합니다.</Typography>
          </motion.span>
          <motion.span>
            <Image src='/image/intro2.png' width={300} height={300} alt='intro2' />
            <Typography className="titleText">백엔드</Typography>
            <Typography className="subText">Java Spring, Node.JS 및 Express.JS를 사용합니다.</Typography>
          </motion.span>
          <motion.span>
            <Image src='/image/intro3.png' width={300} height={300} alt='intro3' />
            <Typography className="titleText">스터디</Typography>
            <Typography className="subText">매주 팀별(프론트엔드, 백엔드) 개발 스터디를 진행합니다. </Typography>
          </motion.span>
        </Box1>
      </Section>
      {/* Section 2 : Project */}
      <Section style={{ height: '60rem' }}>
        <motion.div
          className="titleText"
          style={{ fontSize: '2.7rem' }}
          initial={{
            opacity: 0,
            filter: 'blur(2px)'
          }}
          whileInView={{
            opacity: 1,
            filter: 'blur(0px)',
            transition: { duration: 1 }
          }}
          animate={{
            y: [40, 0],
            transition: {
              duration: 1.3,
              delay: 0.2
            }
          }}
          viewport={{ once: true }}
        >
          다음과 같은 프로젝트를 만들었습니다
        </motion.div>
        <motion.div
          className="subText"
        >
          지난 1년 간 동아리 내에서 직접 개발한 프로젝트들입니다. <br/>
          
        </motion.div>
        <Box2>
          <motion.span>
            <Image src='/image/project1.png' width={500} height={300} alt='intro1' />
            <Typography className="titleText">Whiteblue.kr</Typography>
            <Typography className="subText">
              (2022. 05) WHITEBLUE 공식 웹사이트 <br/>
              (2023. 03) 사이트 리뉴얼
            </Typography>
          </motion.span>
          <motion.span>
            <Image src='/image/project2.png' width={500} height={300} alt='intro2' />
            <Typography className="titleText">Re-Wind.today</Typography>
            <Typography className="subText">(2022. 09) 삼육대학교 총학생회 X WHITEBLUE <br/> 교내 축제 안내 서비스</Typography>
          </motion.span>
        </Box2>
      </Section>
      {/* Section 3 : Apply */}
      <Section style={{ height: '95rem' }}>
        <motion.div
          className="titleText"
          style={{ fontSize: '2.7rem' }}
          initial={{
            opacity: 0,
            filter: 'blur(2px)'
          }}
          whileInView={{
            opacity: 1,
            filter: 'blur(0px)',
            transition: { duration: 1 }
          }}
          animate={{
            y: [40, 0],
            transition: {
              duration: 1.3,
              delay: 0.2
            }
          }}
          viewport={{ once: true }}
        >
          함께 성장할 수 있는 '개발자'를 찾고 있습니다
        </motion.div>
        <motion.div
          className="subText"
          style={{ fontSize: '1rem' }}
        >
          ※ WHITEBLUE는 1년 단위로 활동합니다. <br/>
          ※ 하단 '지원하기' 버튼을 통해 지원서를 작성할 수 있습니다.
        </motion.div>
        <Typography className="subTitle"> 지원 자격 </Typography>
          <Typography className="sub">
            삼육대학교 재학생 (휴학생 X) <br/>
            전공/학년 무관 <br/>
            웹/모바일 개발에 관심있으신 분
          </Typography>
        <Typography className="subTitle"> 활동 개요 </Typography>
          <Typography className="sub">
            <b> 4월 </b> <br/>
            팀별 개발 스터디 (프론트엔드, 백엔드)<br/> <br/>
            <b> 5월 초 </b> <br/>
            중간 발표 <br/> <br/>
            <b> 5월 - 7월 </b> <br/>
            팀별 프로젝트 <br/> <br/>
            <b> 7월 말 </b> <br/>
            최종 공개 발표 <br/> <br/>
            <b> 8월 </b> <br/>
            하계 방학 <br/> <br/>
            <b> 9월 - 11월 </b> <br/>
            교외/내 대회 준비 및 출전 <br/> <br/>
          </Typography>
        <Typography className="subTitle"> 모집 기간 </Typography>
          <Box3>
            <motion.span>
              <Image src='/image/apply1.png' width={190} height={190} alt='apply1' />
            </motion.span>
            <motion.span>
              <Image src='/image/apply2.png' width={190} height={190} alt='apply2' />
            </motion.span>
            <motion.span>
              <Image src='/image/apply3.png' width={190} height={190} alt='apply3' />
            </motion.span>
            <motion.span>
              <Image src='/image/apply4.png' width={190} height={190} alt='apply4' />
            </motion.span>
          </Box3>
        <Button
            variant="contained"
            fullWidth
            sx={{
              mt: 20,
              color: '#fff',
              width: '25%',
              height: '4.3rem',
              borderRadius: '15px',
            }}
            onClick={() => {
              Router.push('/apply/application')
            }}
          >
            <Typography
             sx={{ 
              fontSize: '1.3rem',
              fontFamily: 'ScoreMedium',
              }}>
                WHITEBLUE에 합류하기
            </Typography>
          </Button>
      </Section>
    </Body>
  )
}

export default Index
