import type { AppProps } from 'next/app'
import { NextPage } from 'next'
import { Provider } from 'react-redux'
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
          <AnimatePresence mode="wait">
            <motion.div
              key={router.asPath}
              className="mui-fixed"
              initial={{ opacity: 0 }}
              animate={{
                opacity: 1,
                transition: { duration: 0.3 },
              }}
              exit={{
                opacity: 0,
                transition: { duration: 0.3 },
              }}
            >
              <Layout>
                <Global styles={global} />
                <Component {...pageProps} />
              </Layout>
            </motion.div>
          </AnimatePresence>
        </PersistGate>
      </Provider>
    </>
  )
}

export default wrapper.withRedux(App)
