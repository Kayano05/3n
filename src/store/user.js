import { reactive } from 'vue'

const state = reactive({
  user: JSON.parse(localStorage.getItem('user')) || null,
  token: localStorage.getItem('token') || null,
})

export const useUserStore = () => {
  const setUser = (user) => {
    state.user = user
    if (user) {
      localStorage.setItem('user', JSON.stringify(user))
    } else {
      localStorage.removeItem('user')
    }
  }

  const setToken = (token) => {
    state.token = token
    if (token) {
      localStorage.setItem('token', token)
    } else {
      localStorage.removeItem('token')
    }
  }

  const isLoggedIn = () => {
    return !!state.token && !!state.user
  }

  const logout = () => {
    setUser(null)
    setToken(null)
  }

  return {
    state,
    setUser,
    setToken,
    isLoggedIn,
    logout
  }
} 