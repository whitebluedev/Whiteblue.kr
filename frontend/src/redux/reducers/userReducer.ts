import { createSlice, PayloadAction } from '@reduxjs/toolkit'

interface Profile {
  team: string
  name: string
  major: string
  grade: string
  phone: string
  email: string
  techStack: Array<string>
  interest: string
  project: Array<string>
  portfolio: Array<string>
  resume: string
}

interface Account {
  userID: string
  userPWD: string
  login: boolean
  profile?: Profile
}

const userInitialState: Account = {
  userID: '',
  userPWD: '',
  login: false,
}

const formInitialState: Profile = {
  team: '',
  name: '',
  major: '',
  grade: '',
  email: '',
  phone: '',
  techStack: [],
  interest: '',
  project: [],
  portfolio: [],
  resume: '',
}

const userSlice = createSlice({
  name: 'user',
  initialState: userInitialState,
  reducers: {
    setLogin: (state, action: PayloadAction<boolean>) => {
      state.login = action.payload
    },
    setAccount: (state, action: PayloadAction<Profile | undefined>) => {
      state.profile = action.payload
    },
  },
})

const formSlice = createSlice({
  name: 'form',
  initialState: formInitialState,
  reducers: {
    setEssentialForm: (state, action: PayloadAction<string>) => {
      state.team = action.payload
      state.name = action.payload
      state.major = action.payload
      state.grade = action.payload
      state.email = action.payload
      state.phone = action.payload
      state.resume = action.payload
    },
    setAdditionalForm: (state, action: PayloadAction<Array<string> & string>) => {
      state.techStack = action.payload
      state.interest = action.payload
      state.project = action.payload
      state.portfolio = action.payload
    },
  },
})

export const { setLogin, setAccount } = userSlice.actions
export const { setEssentialForm, setAdditionalForm } = formSlice.actions
export type { Account, Profile }
export default userSlice.reducer
