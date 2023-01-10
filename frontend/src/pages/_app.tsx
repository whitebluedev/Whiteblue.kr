import type { AppProps } from 'next/app'
import { NextPage } from 'next'
import { Provider, useDispatch } from 'react-redux'
import { PersistGate } from 'redux-persist/integration/react'
import store, { persistor, wrapper } from '../redux/store'
import { Global } from '@emotion/react'
import Layout from '../components/Layout'
import global from 'public/css/global'
import Head from 'next/head'
import { motion, AnimatePresence } from 'framer-motion'
import axios from 'axios'
import { useRouter } from 'next/router'

const App: NextPage<AppProps> = ({ Component, pageProps }) => {
  axios.defaults.withCredentials = true

  const router = useRouter()

  return (
    <>
      <Head>
        <title>Whiteblue</title>
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
