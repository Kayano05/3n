export const getToken = () => {
  return localStorage.getItem('token')
}

export const isAuthenticated = () => {
  const token = getToken()
  return !!token  // 将 token 转换为布尔值
}

export const getTokenInfo = () => {
  const token = getToken()
  if (!token) return null
  
  try {
    // 解析 JWT token（取中间部分并解码）
    const payload = token.split('.')[1]
    const decoded = JSON.parse(atob(payload))
    return {
      email: decoded.sub,
      exp: new Date(decoded.exp * 1000)
    }
  } catch (e) {
    console.error('Token parsing failed:', e)
    return null
  }
} 