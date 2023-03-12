import {
  useCallback,
  useState,
  MouseEvent
} from 'react'
// Redux & axios
import { useDispatch, useSelector } from 'react-redux'
import axios, { AxiosError, AxiosResponse } from 'axios'
// Custom Library
import { setLogin } from 'src/redux/reducers/userReducer'
import Router from 'next/router'
// MUI Library
import {
  Avatar,
  Box,
  Button,
  Divider,
  IconButton,
  ListItemIcon,
  Menu,
  MenuItem,
  Typography,
  useMediaQuery,
} from '@mui/material'
import { Settings, Logout } from '@mui/icons-material'
import SendIcon from '@mui/icons-material/Send'

export default function AccountMenu() {
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null)
  const open = Boolean(anchorEl)
  const isLogin = useSelector((store: Store) => {
    return store.user.login
  })
  const dispatch = useDispatch()

  const handleClick = useCallback((event: MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget)
  }, [])

  const handleClose = useCallback(() => {
    setAnchorEl(null)
  }, [])

  const logoutHandler = useCallback(() => {
    axios
      .get('http://localhost:8000/logout')
      .then(() => {
        dispatch(setLogin(false))
      })
      .catch((error: AxiosError) => {
        console.log(error)
      })
  }, [dispatch])

  const isMobile = useMediaQuery('(max-width:600px)')

  return (
    <>
      <Box
        sx={{ display: 'inline', alignItems: 'center', textAlign: 'center' }}
      >
        {/* 로그인 상태 */}
        {isLogin && (
          <>
            <IconButton
              onClick={handleClick}
              size="small"
              sx={{ ml: 2 }}
              aria-controls={open ? 'account-menu' : undefined}
              aria-haspopup="true"
              aria-expanded={open ? 'true' : undefined}
            >
              <Avatar src="/broken-image.jpg" sx={{ width: 40, height: 40 }} />
            </IconButton>

            <Menu
              anchorEl={anchorEl}
              open={open}
              onClose={handleClose}
              onClick={handleClose}
              PaperProps={{
                elevation: 0,
                sx: {
                  overflow: 'visible',
                  filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
                  mt: 1.5,
                  '& :before': {
                    content: '""',
                    display: 'block',
                    position: 'absolute',
                    top: 0,
                    right: 14,
                    width: 10,
                    height: 10,
                    bgcolor: 'background.paper',
                    transform: 'translateY(-50%) rotate(45deg)',
                    zIndex: 0,
                  },
                },
              }}
              transformOrigin={{ horizontal: 'right', vertical: 'top' }}
              anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
            >
              <MenuItem
                onClick={() => {
                  handleClose()
                  Router.push('/apply/application')
                }}
              >
                <ListItemIcon>
                  <SendIcon fontSize="small" />
                </ListItemIcon>
                지원하기
              </MenuItem>

              <Divider />
              <MenuItem
                onClick={() => {
                  handleClose()
                  Router.push('/account/profile')
                }}
              >
                <ListItemIcon>
                  <Settings fontSize="small" />
                </ListItemIcon>
                정보수정
              </MenuItem>

              <MenuItem
                onClick={() => {
                  handleClose()
                  logoutHandler()
                }}
              >
                <ListItemIcon>
                  <Logout fontSize="small" />
                </ListItemIcon>
                로그아웃
              </MenuItem>
            </Menu>
          </>
        )}
        {/* 로그아웃 상태 */}
        {!isLogin && (
          <>
            <Button
              sx={{ color: '#000' }}
              onClick={() => {
                Router.push('/account/login')
              }}
            >
              {isMobile ? '' : <Typography> LOGIN&nbsp; </Typography>}
              <Logout fontSize="medium" />
            </Button>
          </>
        )}
      </Box>
    </>
  )
}
