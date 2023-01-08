import { SpeedDial, SpeedDialAction, SpeedDialIcon } from '@mui/material'
import {
  FunctionComponent,
  ReactNode,
  useCallback,
  useEffect,
  useState,
} from 'react'
import CreateIcon from '@mui/icons-material/Create'
import DescriptionIcon from '@mui/icons-material/Description'
import PersonIcon from '@mui/icons-material/Person'
import LoginIcon from '@mui/icons-material/Login'
import LogoutIcon from '@mui/icons-material/Logout'
import HomeIcon from '@mui/icons-material/Home'
import Router from 'next/router'
import { useDispatch, useSelector } from 'react-redux'
import { Profile, setLogin, setProfile } from 'redux/reducers/userReducer'
import axios, { AxiosError, AxiosResponse } from 'axios'
import Snackbar from '@mui/material/Snackbar'
import Alert from '@mui/material/Alert'

const Layout: FunctionComponent<{ children: ReactNode }> = ({ children }) => {
  const isLogin = useSelector((store: Store) => {
    return store.user.login
  })
  const dispatch = useDispatch()

  const [logout, setLogout] = useState(false)

  useEffect(() => {
    axios
      .get('http://localhost:8000/user/auth')
      .then((response: AxiosResponse<Profile | undefined>) => {
        if (response.data) {
          dispatch(setLogin(true))
          dispatch(setProfile(response.data))
        } else {
          dispatch(setLogin(false))
          dispatch(setProfile(undefined))
        }
      })
      .catch((error: AxiosError) => {
        console.log(error)
      })
  })

  const logoutHandler = useCallback(async () => {
    axios
      .get('http://localhost:8000/logout')
      .then(() => {
        dispatch(setLogin(false))
        if (Router.asPath != '/') {
          Router.push('/')
        } else {
          setLogout(true)
        }
      })
      .catch((error) => {
        console.log(error)
      })
  }, [dispatch])

  return (
    <>
      {children}
      <SpeedDial
        ariaLabel="speedDial"
        sx={{ position: 'fixed', bottom: 30, right: 30 }}
        icon={<SpeedDialIcon />}
      >
        {!isLogin && (
          <SpeedDialAction
            icon={<LoginIcon />}
            tooltipTitle="로그인"
            onClick={() => {
              Router.push(process.env.LOGIN_URL as string)
            }}
          />
        )}
        {isLogin && (
          <SpeedDialAction
            icon={<LogoutIcon />}
            tooltipTitle="로그아웃"
            onClick={logoutHandler}
          />
        )}

        {isLogin && (
          <SpeedDialAction icon={<PersonIcon />} tooltipTitle="내 정보" />
        )}
        {Router.asPath != '/application' && (
          <SpeedDialAction
            icon={<DescriptionIcon />}
            tooltipTitle="동아리 신청"
            onClick={() => {
              Router.push('/application')
            }}
          />
        )}
        {Router.asPath != '/' && (
          <SpeedDialAction
            icon={<HomeIcon />}
            tooltipTitle="메인"
            onClick={() => {
              Router.push('/')
            }}
          />
        )}
      </SpeedDial>
      <Snackbar
        open={logout}
        autoHideDuration={3000}
        onClose={() => {
          setLogout(false)
        }}
      >
        <Alert
          onClose={() => {
            setLogout(false)
          }}
          severity="success"
          sx={{ width: '100%', fontFamily: 'nanumSquare' }}
        >
          성공적으로 로그아웃 처리되었습니다.
        </Alert>
      </Snackbar>
    </>
  )
}

export default Layout
