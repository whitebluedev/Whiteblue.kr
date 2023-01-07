import { css } from '@emotion/react'

const global = css`
  @import url('https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css');
  * {
    margin: 0;
    font-family: 'NanumSquare';
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
