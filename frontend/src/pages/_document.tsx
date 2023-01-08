import Document, { Head, Html, Main, NextScript } from 'next/document'

class CustomDocument extends Document {
  render() {
    return (
      <Html>
        <Head>
          <link rel="icon" href="/image/favicon.ico" />
          <meta charSet="utf-8" />
          <meta name="author" content="earlgrey02" />
          <meta name="keywords" content="Whiteblue" />
          <meta name="description" content="SYU Security Team" />
        </Head>
        <body>
          <Main />
          <NextScript />
        </body>
      </Html>
    )
  }
}

export default CustomDocument
