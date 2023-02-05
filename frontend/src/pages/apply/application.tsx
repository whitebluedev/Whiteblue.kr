import styled from '@emotion/styled'
// React Library
import { ChangeEvent, useEffect, useState, useCallback , FormEvent} from 'react'
import { NextPage } from 'next'
import Router from 'next/router'
// Redux & axios
import { useDispatch, useSelector } from 'react-redux'
import { setEssentialForm, setAdditionalForm, Profile } from 'src/redux/reducers/userReducer'
import axios, { AxiosError, AxiosResponse } from 'axios'
// Custom Library
import Header from 'src/components/Header'
import HideNav from 'src/components/Nav/HideNav'
// MUI Library
import {
  Box,
  Button,
  Checkbox,
  Dialog, DialogActions, DialogTitle, DialogContent, DialogContentText,
  Divider,
  FormControl, FormControlLabel, FormLabel, FormGroup,
  Input, InputAdornment,
  MenuItem,
  Paper,
  Radio, RadioGroup,
  Select, SelectChangeEvent,
  Typography,
  TextField,
} from '@mui/material'
import { motion, useAnimation } from 'framer-motion'


const Body = styled(motion.div)`
  display: flex;
  flex-direction: column;
  height: 480%;
  width: 100%;
  align-items: center;
  background-color: #F6F6F6;

  & .contentTitle {
    height: 30px;
    font-family: 'ChosunBg';
    font-size: 1rem;
    font-weight: 100;
    text-align: left;
    padding: 3% 8% 3% 8%;
  }

  & .content {
    height: 30px;
    font-size: 1rem;
    text-align: left;
    padding: 2% 8%;
    font-family: 'Pretendard';
  }

  & .content-form {
    text-align: left;
    align-items: left;
    padding: 4% 8% 0 8%;
    font-size: 0.8rem;
    font-weight: 100;
  }

  & .detail {
    text-align: left;
    padding: 1% 8%;
    font-size: 0.8rem;
    font-weight: 100;
  }

  & .star {
    color: red;
  }

  @media (max-width: 600px) {

  }
`

const Application: NextPage = () => {

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

  {/* Form State & Values */ }
  const [team, setTeam] = useState('');
  const [name, setName] = useState('');
  const [major, setMajor] = useState('');
  const [grade, setGrade] = useState('');
  const [email, setEmail] = useState({
    name: '',
    site: '',
  });
  const [phone, setPhone] = useState('');
  const [techStack, setTeckstack] = useState({
    ReactJS: false,
    NextJS: false,
    VueJS: false,
    NodeJS: false,
    Spring: false,
    Django: false,
    ExpressJS: false,
    NustJS: false,
    AWS: false,
    Docker: false,
    ReactNative: false,
    Android: false,
    iOS: false,
    Etc: '',
  })
  const [interest, setInterest] = useState('')
  const [project, setProject] = useState([])
  const [portfolio, setPortfolio] = useState([])
  const [resume, setResume] = useState('')

  const {
    ReactJS, NextJS, VueJS,
    NodeJS, Spring, Django, ExpressJS, NustJS,
    AWS, Docker,
    ReactNative, Android, iOS
  } = techStack

  const emails = [
    {
      value: 'gmail.com',
      label: 'gmail.com',
    },
    {
      value: 'naver.com',
      label: 'naver.com',
    },
    {
      value: 'daum.com',
      label: 'daum.net',
    },
    {
      value: 'hanmail.net',
      label: 'hamail.net',
    },
  ]
  const sns = [
    {
      value: 'github',
      label: 'Github',
    },
    {
      value: 'notion',
      label: 'Notion',
    },
    {
      value: 'blog',
      label: 'Blog',
    },
    {
      value: 'etc',
      label: '기타',
    },
  ]

  const techStackInput = (e: ChangeEvent<HTMLInputElement>) => {
    setTeckstack({
      ...techStack,
      [e.target.name]: e.target.checked
    });
  };

  {/* formHandler */}
  const dispatch = useDispatch()
  const formHandler = useCallback(
    (event: FormEvent<HTMLFormElement>) => {
      event.preventDefault()
      axios
        .post('http://localhost:8888/user', {
          이름: name,
          지원분야: team,
          전공: major,
          학년: grade,
          이메일: email.name,
          이메일사이트: email.site,
          전화번호: phone,
          기술스택: techStack,
          관심분야: interest,
          프로젝트: project,
          포트폴리오: portfolio,
          자기소개서: resume,
        })
        .then((response: AxiosResponse) => {
          dispatch(setEssentialForm(response.data))
          dispatch(setAdditionalForm(response.data))
          Router.replace('/apply/applyComplete')
        })
    },
    [
      name, team, major, grade, email, phone,
      techStack, interest, project, portfolio, resume,
      dispatch,
    ]
  )

  return (
    <Body>
      <HideNav />
      <Header name="지원서" />
      {/* From Wrapper */}
      <Box sx={{ width: '50%' }}>
        <Box
          component="form"
          onSubmit={formHandler}
          noValidate
          autoComplete="off"
          sx={{
            width: '100%',
            height: '20px',
          }}
        >
          {/* 0. 모집안내 */}
          <Paper
            elevation={3}
            sx={{
              height: '70vh',
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
              WHITEBLUE 동아리원 모집안내
            </Typography>
            <Typography className='content'>
              ~ 동아리 설명 ~
            </Typography>
            {/* Contents */}
            <Typography className='contentTitle' sx={{ color: '#4378FF' }}>
              지원자격
            </Typography>
            <Typography className='content'>
              삼육대학교 재학생
            </Typography>

            <Typography className='contentTitle' sx={{ color: '#4378FF' }}>
              모집분야
            </Typography>
            <Typography className='content'>
              WEB) 프론트엔드, 백엔드
            </Typography>

            <Typography className='contentTitle' sx={{ color: '#4378FF' }}>
              모집과정
            </Typography>
            <Typography className='content'>
              example context
            </Typography>

            <Typography className='contentTitle' sx={{ color: '#4378FF' }}>
              활동개요
            </Typography>
            <Typography className='content'>
              example context
            </Typography>

            <Divider sx={{ my: 3 }} />
            <Typography className='detail'>
              ※ 지원 및 기타관련 문의 wayandway@kakao.com
            </Typography>
          </Paper>
          
        {/* Form Contents */}
          {/* 1. 개인정보 */}
          {/* 1-1. 지원분야 */}
          <Paper
            elevation={3}
            sx={{
              height: '18vh',
              mb: 5
            }}
          >
            <Typography className='contentTitle' sx={{ color: '#000' }}>
              지원 분야<sup className='star'>*</sup>
            </Typography>
            <Typography className='content-form'>
              <FormControl required>
                <RadioGroup
                  row
                  onChange={(e) => {
                    setTeam(e.target.value)
                  }}>
                  <FormControlLabel value="FrontEnd" control={<Radio />} label="프론트엔드" />
                  <FormControlLabel value="BackEnd" control={<Radio />} label="백엔드" />
                </RadioGroup>
              </FormControl>
            </Typography>
          </Paper>

          {/* 1-2. 이름 */}
          <Paper
            elevation={3}
            sx={{
              height: '18vh',
              mb: 5
            }}
          >
            <Typography className='contentTitle' sx={{ color: '#000' }}>
              이름<sup className='star'>*</sup>
            </Typography>
            <Typography className='content-form'>
              <FormControl>
                <Input
                  required
                  placeholder='내 답변'
                  value={name}
                  onChange={(e) => {
                    setName(e.target.value)
                  }}
                />
              </FormControl>
            </Typography>
          </Paper>

          {/* 1-3. 전공 & 학년 */}
          <Paper
            elevation={3}
            sx={{
              height: '23vh',
              mb: 5
            }}
          >
            <Typography className='contentTitle' sx={{ color: '#000' }}>
              전공 및 학년<sup className='star'>*</sup>
            </Typography>
            <Typography className='detail'>
              ※ 휴학생은 지원이 불가합니다.
            </Typography>
            <Typography className='content-form'>
              <FormControl sx={{ width: '30%' }}>
                <Input
                  required
                  value={major}
                  placeholder='내 답변'
                  endAdornment={
                    <InputAdornment
                      position="end"
                      sx={{ '& :hover': { cursor: 'default' } }}
                    >
                      전공
                    </InputAdornment>
                  }
                  onChange={(e) => {
                    setMajor(e.target.value)
                  }}
                />
              </FormControl>
              <FormControl variant='standard' sx={{ ml: 1, width: '6%' }}>
                <Select
                  required
                  value={grade}
                  onChange={(e: SelectChangeEvent) => {
                    setGrade(e.target.value)
                  }}
                  endAdornment={
                    <InputAdornment
                      position="end"
                      sx={{ '& :hover': { cursor: 'default' } }}
                    >
                      학년
                    </InputAdornment>
                  }
                >
                  <MenuItem value={1}>1</MenuItem>
                  <MenuItem value={2}>2</MenuItem>
                  <MenuItem value={3}>3</MenuItem>
                  <MenuItem value={4}>4</MenuItem>
                </Select>

              </FormControl>
            </Typography>
          </Paper>

          {/* 1-4. 전화번호 & 이메일 */}
          <Paper
            elevation={3}
            sx={{
              height: '26vh',
              mb: 5
            }}
          >
            <Typography className='contentTitle' sx={{ color: '#000' }}>
              연락처<sup className='star'>*</sup>
            </Typography>
            <Typography className='content-form'>
              {/* 전화번호 */}
              <FormControl sx={{ width: '30%' }}>
                <Input
                  value={phone}
                  type='text'
                  sx={{ mb: 4 }}
                  onChange={(e) => {
                    setPhone(e.target.value)
                  }}
                />
              </FormControl>
              <br />
              {/* 이메일 */}
              <FormControl sx={{ width: '30%' }}>
                <Input
                  value={email.name}
                  required
                  placeholder='example'
                  endAdornment={
                    <InputAdornment
                      position="end"
                      sx={{ '& :hover': { cursor: 'default' } }}
                    >
                      @
                    </InputAdornment>
                  }
                  onChange={(e) => {
                    setEmail({...email, name: e.target.value})
                  }}
                />
              </FormControl>
              <FormControl variant='standard' sx={{ mx: 1, width: '25%' }}>
                <TextField
                  value={email.site}
                  required
                  select
                  variant='standard'
                  defaultValue='gmail.com'
                  onChange={(e) => {
                    setEmail({...email, site: e.target.value})
                  }}
                >
                  {emails.map((option) => (
                    <MenuItem key={option.value} value={option.value}>
                      {option.label}
                    </MenuItem>
                  ))}
                </TextField>
              </FormControl>
            </Typography>
          </Paper>

          {/* 2. 개인역량 ~ 포트폴리오 */}
          {/* 2-1. 개인 역량 */}
          <Paper
            elevation={3}
            sx={{
              height: '65vh',
              mb: 5
            }}
          >
            <Typography className='contentTitle' sx={{ color: '#000' }}>
              개인 역량
            </Typography>
            <Typography className='detail'>
              ※ 지원분야와 상관없이 이용해본 프레임워크/라이브러리/서비스를 체크해주세요.
            </Typography>
            <Typography className='content-form'>
              <FormControl focused={false}>
                <Typography sx={{ mr: 6 }}>
                  <FormLabel>프론트엔드</FormLabel>
                  <FormGroup sx={{ flexDirection: 'row' }}>
                    <FormControlLabel
                      control={
                        <Checkbox checked={ReactJS} onChange={techStackInput} name="ReactJS" />
                      }
                      label="React"
                    />
                    <FormControlLabel
                      control={
                        <Checkbox checked={NextJS} onChange={techStackInput} name="NextJS" />
                      }
                      label="Next.js"
                    />
                    <FormControlLabel
                      control={
                        <Checkbox checked={VueJS} onChange={techStackInput} name="VueJS" />
                      }
                      label="Vue.js"
                    />
                  </FormGroup>
                </Typography>
                <Typography sx={{ mr: 6 }}>
                  <FormLabel>백엔드</FormLabel>
                  <FormGroup sx={{ flexDirection: 'row' }}>
                    <FormControlLabel
                      control={
                        <Checkbox checked={NodeJS} onChange={techStackInput} name="NodeJS" />
                      }
                      label="Node.js"
                    />
                    <FormControlLabel
                      control={
                        <Checkbox checked={Spring} onChange={techStackInput} name="Spring" />
                      }
                      label="Spring"
                    />
                    <FormControlLabel
                      control={
                        <Checkbox checked={Django} onChange={techStackInput} name="Django" />
                      }
                      label="Django"
                    />
                    <FormControlLabel
                      control={
                        <Checkbox checked={ExpressJS} onChange={techStackInput} name="ExpressJS" />
                      }
                      label="Express.js"
                    />
                    <FormControlLabel
                      control={
                        <Checkbox checked={NustJS} onChange={techStackInput} name="NustJS" />
                      }
                      label="Nust.js"
                    />
                  </FormGroup>
                </Typography>
                <Typography sx={{ mr: 6 }}>
                  <FormLabel>서버</FormLabel>
                  <FormGroup sx={{ flexDirection: 'row' }}>
                    <FormControlLabel
                      control={
                        <Checkbox checked={AWS} onChange={techStackInput} name="AWS" />
                      }
                      label="AWS"
                    />
                    <FormControlLabel
                      control={
                        <Checkbox checked={Docker} onChange={techStackInput} name="Docker" />
                      }
                      label="Docker"
                    />
                  </FormGroup>
                </Typography>
                <Typography>
                  <FormLabel>모바일</FormLabel>
                  <FormGroup sx={{ flexDirection: 'row' }}>
                    <FormControlLabel
                      control={
                        <Checkbox checked={ReactNative} onChange={techStackInput} name="ReactNative" />
                      }
                      label="React Native"
                    />
                    <FormControlLabel
                      control={
                        <Checkbox checked={Android} onChange={techStackInput} name="Android" />
                      }
                      label="Android"
                    />
                    <FormControlLabel
                      control={
                        <Checkbox checked={iOS} onChange={techStackInput} name="iOS" />
                      }
                      label="iOS"
                    />
                  </FormGroup>
                </Typography>
              </FormControl>
              <Divider sx={{ my: 4 }} />
              <FormControl sx={{ width: '40%' }}>
                <FormLabel focused={false}>기타</FormLabel>
                <Input
                  value={techStack.Etc}
                  name="Etc"
                  placeholder='내 답변'
                  onChange={(e) => {
                    setTeckstack({
                      ...techStack,
                      [e.target.name]: e.target.value
                    });
                  }} 
                />
              </FormControl>
            </Typography>
          </Paper>
          {/* 2-2. 관심분야 */}
          <Paper
            elevation={3}
            sx={{
              height: '23vh',
              mb: 5
            }}
          >
            <Typography className='contentTitle' sx={{ color: '#000' }}>
              관심 분야
            </Typography>
            <Typography className='detail'>
              예) React를 이용한 웹서비스 개발, API 개발 등
            </Typography>
            <Typography className='content-form'>
              <FormControl sx={{ width: '40%' }}>
                <Input
                  placeholder='내 답변'
                  value={interest}
                  onChange={(e) => {
                    setInterest(e.target.value)
                  }}
                />
              </FormControl>
            </Typography>
          </Paper>
          {/* 2-3. 프로젝트 이력 */}
          <Paper
            elevation={3}
            sx={{
              height: '48vh',
              mb: 5
            }}
          >
            <Typography className='contentTitle' sx={{ color: '#000' }}>
              프로젝트 이력
            </Typography>
            <Typography className='detail'>
              예) [프로젝트명] 상세 내용
            </Typography>
            <Typography className='content-form'>
              <FormControl sx={{ width: '80%', mb: 5 }}>
                <Input disabled placeholder='[000 서비스] 리액트를 활용한 000 서비스' />
              </FormControl>
              <FormControl sx={{ width: '80%', mb: 6 }}>
                <Input
                 placeholder='내 답변 1)'
                 value=''
                />
              </FormControl>
              <FormControl sx={{ width: '80%', mb: 6 }}>
                <Input
                  placeholder='내 답변 2)'
                  value=''
                />              
                </FormControl>
              <FormControl sx={{ width: '80%', mb: 6 }}>
                <Input
                  placeholder='내 답변 3)'
                  value=''
                />              
                </FormControl>
            </Typography>
          </Paper>
          {/* 2-4. 포트폴리오 */}
          <Paper
            elevation={3}
            sx={{
              height: '36vh',
              mb: 5
            }}
          >
            <Typography className='contentTitle' sx={{ color: '#000' }}>
              포트폴리오
            </Typography>
            <Typography className='detail'>
              깃허브/블로그/노션 등의 주소를 작성해주세요.
            </Typography>
            <Typography className='content-form'>
              <FormControl variant='standard' sx={{ width: '15%' }}>
                <TextField
                  select
                  variant="standard"
                  value=''
                >
                  {sns.map((option) => (
                    <MenuItem key={option.value} value={option.value}>
                      {option.label}
                    </MenuItem>
                  ))}
                </TextField>
              </FormControl>
              <FormControl sx={{ width: '65%', mx: 1 }}>
                <Input
                  value=''
                  placeholder='https://'
                />
              </FormControl>
              <br/>
              <FormControl variant='standard' sx={{ width: '15%', my: 3 }}>
                <TextField
                  select
                  defaultValue="gmail"
                  variant="standard"
                >
                  {sns.map((option) => (
                    <MenuItem key={option.value} value={option.value}>
                      {option.label}
                    </MenuItem>
                  ))}
                </TextField>
              </FormControl>
              <FormControl sx={{ width: '65%', mx: 1, my: 3 }}>
                <Input
                  value=''
                  placeholder='https://'
                />
              </FormControl>
              <br/>
              <FormControl variant='standard' sx={{ width: '15%' }}>
                <TextField
                  select
                  defaultValue="gmail"
                  variant="standard"
                >
                  {sns.map((option) => (
                    <MenuItem key={option.value} value={option.value}>
                      {option.label}
                    </MenuItem>
                  ))}
                </TextField>
              </FormControl>
              <FormControl sx={{ width: '65%', mx: 1 }}>
                <Input
                  value=''
                  placeholder='https://'
                />
              </FormControl>
            </Typography>
          </Paper>

          {/* 3. 자기소개서 */}
          <Paper
            elevation={3}
            sx={{
              height: '60vh',
              mb: 5
            }}
          >
            <Typography className='contentTitle' sx={{ color: '#000' }}>
              자기 소개서<sup className='star'>*</sup>
            </Typography>
            <Typography className='detail'>
              ※ 공백 포함 700자 이내
            </Typography>
            <Typography className='content-form'>
              <FormControl fullWidth>
                <TextField
                  focused={false}
                  multiline
                  rows={14}
                  variant='filled'
                  onChange={(e) => {
                    setResume(e.target.value)
                  }}
                />
              </FormControl>
            </Typography>

          </Paper>
          {/* Submit Button */}
          <Button
            type='submit'
            variant='contained'
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
    </Body>
  )
}

export default Application
