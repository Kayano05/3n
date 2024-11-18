require('dotenv').config()
const express = require('express')
const cors = require('cors')
const authRoutes = require('./routes/auth')

const app = express()

// 中间件
app.use(cors())
app.use(express.json())

// 请求日志中间件
app.use((req, res, next) => {
  console.log(`[${new Date().toISOString()}] ${req.method} ${req.originalUrl}`)
  next()
})

// 路由 - 注意这里不需要 /api 前缀
app.use('/auth', authRoutes)  // 直接使用 /auth

// 404 处理
app.use((req, res) => {
  console.log(`404: ${req.method} ${req.originalUrl}`)
  res.status(404).json({
    success: false,
    message: 'API endpoint not found'
  })
})

const PORT = process.env.PORT || 3000
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`)
  console.log('Available routes:')
  console.log('- POST /auth/send-code')
  console.log('- POST /auth/register')
}) 