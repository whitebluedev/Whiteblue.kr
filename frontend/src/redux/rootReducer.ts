import { HYDRATE } from 'next-redux-wrapper'
import { CombinedState } from 'redux'
import { AnyAction, combineReducers } from 'redux'
import userReducer, { User } from './reducers/userReducer'

interface ReducerState {
  user: User
}

const rootReducer = (
  state: ReducerState | undefined,
  action: AnyAction
): CombinedState<ReducerState> => {
  switch (action.type) {
    case HYDRATE:
      return action.payload
    default: {
      const combinedReducer = combineReducers({
        user: userReducer,
      })
      return combinedReducer(state, action)
    }
  }
}

export default rootReducer
