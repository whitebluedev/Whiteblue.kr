import { createSlice, PayloadAction } from '@reduxjs/toolkit'

interface User {
  login: boolean
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
  },
})

export const { setLogin } = userSlice.actions
export type { User }
export default userSlice.reducer
