import Document, { Head, Html, Main, NextScript } from 'next/document'

class CustomDocument extends Document {
  render() {
    return (
      <Html>
        <Head>
          <link rel="icon" href="/image/favicon.png" />
          <meta charSet="utf-8" />
          <meta name="author" content="wayandway" />
          <meta name="keywords" content="WHITEBLUE" />
          <meta name="description" content="WHITEBLUE Website" />
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
