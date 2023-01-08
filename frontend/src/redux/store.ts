import { configureStore } from '@reduxjs/toolkit'
import { persistReducer, persistStore } from 'redux-persist'
import { createWrapper } from 'next-redux-wrapper'
import rootReducer from './rootReducer'
import session from 'redux-persist/lib/storage/session'

const persistedReducer = persistReducer(
  {
    key: 'redux',
    storage: session,
    whitelist: ['user'],
  },
  rootReducer
)

const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) => {
    return getDefaultMiddleware({
      serializableCheck: false,
    })
  },
})

const persistor = persistStore(store)

const wrapper = createWrapper(() => {
  return store
})

declare global {
  type Store = ReturnType<typeof store.getState>
}
export { wrapper, persistor }
export default store
