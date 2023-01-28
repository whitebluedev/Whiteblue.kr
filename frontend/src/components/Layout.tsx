import {
  FunctionComponent,
  ReactNode,
} from 'react'

const Layout: FunctionComponent<{ children: ReactNode }> = ({ children }) => {
  return (
    <>
      {children}
    </>
  )
}

export default Layout
