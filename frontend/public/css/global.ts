import { css } from '@emotion/react'

const global = css`
  @import url('https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css');
  @font-face {
    font-family: 'GmarketSans';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: 800;
    font-style: normal;
  }
  * {
    margin: 0;
  }
  html,
  body,
  div#__next {
    width: 100%;
    height: 100%;
    text-align: center;
    user-select: none;
  }
`

export default global
