import { Global } from '@emotion/react'
// React Library
import type { AppProps } from 'next/app'
import { NextPage } from 'next'
import Head from 'next/head'
// Redux & axios
import { Provider } from 'react-redux'
import { PersistGate } from 'redux-persist/integration/react'
import store, { persistor, wrapper} from '../redux/store'
import axios from 'axios'
// MUI Library
// Custom Library
import Layout from 'src/components/Layout'
import global from 'public/css/global'



const App: NextPage<AppProps> = ({ Component, pageProps }) => {
  axios.defaults.withCredentials = true

  return (
    <>
      <Head>
        <title>WHITEBLUE</title>
      </Head>
      <Provider store={store}>
        <PersistGate persistor={persistor}>
          <Layout>
            <Global styles={global} />
            <Component {...pageProps} />
          </Layout>
        </PersistGate>
      </Provider>
    </>
  )
}

export default wrapper.withRedux(App)
