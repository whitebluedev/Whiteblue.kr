import axios, { AxiosError, AxiosResponse } from 'axios'
import Router from 'next/router'
import { FunctionComponent, ReactNode, useEffect } from 'react'
import { useDispatch } from 'react-redux'
import { setLogin } from 'src/redux/reducers/userReducer'

const Layout: FunctionComponent<{ children: ReactNode }> = ({ children }) => {
  const dispatch = useDispatch()

  useEffect(() => {
    axios
      .get('http://localhost:8000/user/auth')
      .then(
        (
          response: AxiosResponse<{
            id: number
            email: string
            username: string
          }>
        ) => {
          if (response.data) {
            dispatch(setLogin(true))
            console.log(response.data)
          } else {
            dispatch(setLogin(false))
          }
        }
      )
      .catch((error: AxiosError) => {
        console.log(error)
      })
  }, [dispatch, Router.asPath])

  return <>{children}</>
}

export default Layout
