import { createSlice, PayloadAction } from '@reduxjs/toolkit'

interface Account {
  userID: string
  userPWD: string
}

interface User {
  login: boolean
  account?: Account
}

const initialState: User = {
  login: false,
}

const userSlice = createSlice({
  name: 'user',
  initialState: initialState,
  reducers: {
    setLogin: (state, action: PayloadAction<boolean>) => {
      state.login = action.payload
    },
    setAccount: (state, action: PayloadAction<Account | undefined>) => {
      state.account = action.payload
    },
  },
})



export const { setLogin, setAccount } = userSlice.actions
export type { User, Account }
export default userSlice.reducer
