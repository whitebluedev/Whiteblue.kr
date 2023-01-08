import { createSlice, PayloadAction } from '@reduxjs/toolkit'

interface Profile {
  email: string
  username: string
}

interface User {
  login: boolean
  profile?: Profile
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
    setProfile: (state, action: PayloadAction<Profile | undefined>) => {
      state.profile = action.payload
    },
  },
})

export const { setLogin, setProfile } = userSlice.actions
export type { User, Profile }
export default userSlice.reducer
