import { SpeedDial, SpeedDialAction, SpeedDialIcon } from '@mui/material'
import { FunctionComponent, ReactNode, useCallback, useState } from 'react'
import CreateIcon from '@mui/icons-material/Create'
import AssignmentIndIcon from '@mui/icons-material/AssignmentInd'
import LoginIcon from '@mui/icons-material/Login'
import LogoutIcon from '@mui/icons-material/Logout'
import HomeIcon from '@mui/icons-material/Home'
import Router from 'next/router'
import { Alert, Snackbar } from '@mui/material'
import { useDispatch, useSelector } from 'react-redux'
import { setLogin } from 'redux/reducers/userReducer'

const Layout: FunctionComponent<{ children: ReactNode }> = ({ children }) => {
  const isLogin = useSelector((store: Store) => {
    return store.user.login
  })
  const dispatch = useDispatch()

  const [logout, setLogout] = useState(false)

  const logoutHandler = useCallback(async () => {
    dispatch(setLogin(false))
    setLogout(true)
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
        {Router.asPath != '/application' && (
          <SpeedDialAction
            icon={<AssignmentIndIcon />}
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
          정상적으로 로그아웃 되었습니다.
        </Alert>
      </Snackbar>
    </>
  )
}

export default Layout
