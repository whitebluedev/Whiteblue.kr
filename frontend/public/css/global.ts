import { css } from '@emotion/react'

const global = css`
  @import url('https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css');
  @font-face {
    font-family: 'Score';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
    font-style: normal;
    font-weight: normal;
}
  @font-face {
    font-family: 'GmarketSans';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
  }
  @font-face {
    font-family: 'ChosunBg';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunBg.woff') format('woff');
    font-weight: normal;
    font-style: normal;
  }
  @font-face {
    font-family: 'LINE';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2') format('woff2');
    font-weight: 100;
    font-style: normal;
  }
  @font-face {
    font-family: 'Pretendard';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
  }


  * {
    margin: 0;
    font-family: 'Pretendard';
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
