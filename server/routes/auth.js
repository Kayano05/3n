const express = require('express')
const router = express.Router()
const nodemailer = require('nodemailer')
const crypto = require('crypto')

// 存储验证码
const verificationCodes = new Map()

// 配置邮件发送器 - 使用 Outlook 的简单配置
const transporter = nodemailer.createTransport({
  service: 'Outlook',  // 使用预定义的 Outlook 服务
  auth: {
    user: 'kayano04@outlook.jp',
    pass: 'your-password'  // 替换为你的真实密码
  }
})

// 测试邮件配置
transporter.verify((error, success) => {
  if (error) {
    console.error('邮件服务器配置错误:', error)
  } else {
    console.log('邮件服务器连接成功')
  }
})

// 在文件开头添加调试日志
console.log('Loading auth routes...')

// 发送验证码
router.post('/send-code', async (req, res) => {
  console.log('Received send-code request:', req.body)
  try {
    const { email } = req.body
    console.log('收到发送验证码请求:', email)

    if (!email) {
      return res.status(400).json({
        success: false,
        message: '邮箱地址不能为空'
      })
    }

    // 生成验证码
    const code = Math.floor(100000 + Math.random() * 900000).toString()
    console.log('生成的验证码:', code)

    // 存储验证码
    verificationCodes.set(email, {
      code,
      timestamp: Date.now()
    })

    // 发送邮件
    try {
      await transporter.sendMail({
        from: 'kayano04@outlook.jp',
        to: email,
        subject: 'NoviceNest - 注册验证码',
        html: `
          <div style="padding: 20px; background-color: #f5f5f7; border-radius: 10px;">
            <h2 style="color: #1d1d1f;">您的注册验证码</h2>
            <p style="font-size: 24px; color: #FF2D55; letter-spacing: 2px; margin: 20px 0;">
              ${code}
            </p>
            <p style="color: #6e6e73;">验证码有效期为5分钟，请尽快完成注册。</p>
          </div>
        `
      })

      console.log('验证码邮件发送成功')
      res.json({
        success: true,
        message: '验证码已发送，请查收邮件'
      })
    } catch (emailError) {
      console.error('邮件发送失败:', emailError)
      res.status(500).json({
        success: false,
        message: '邮件发送失败，请检查邮箱地址是否正确',
        error: emailError.message
      })
    }
  } catch (error) {
    console.error('发送验证码失败:', error)
    res.status(500).json({
      success: false,
      message: '发送验证码失败，请稍后重试',
      error: error.message
    })
  }
})

// 注册
router.post('/register', async (req, res) => {
  try {
    const { email, verificationCode, password } = req.body
    console.log('收到注册请求:', { email, verificationCode })

    // 验证验证码
    const storedData = verificationCodes.get(email)
    if (!storedData || storedData.code !== verificationCode) {
      return res.status(400).json({
        success: false,
        message: '验证码错误'
      })
    }

    // 验证码是否过期（5分钟）
    if (Date.now() - storedData.timestamp > 5 * 60 * 1000) {
      return res.status(400).json({
        success: false,
        message: '验证码已过期'
      })
    }

    // 密码加密
    const hashedPassword = crypto
      .createHash('sha256')
      .update(password)
      .digest('hex')

    // TODO: 保存用户信息到数据库
    console.log('注册成功:', { email, hashedPassword })
    
    // 清除验证码
    verificationCodes.delete(email)

    res.json({ 
      success: true,
      message: '注册成功'
    })
  } catch (error) {
    console.error('注册失败:', error)
    res.status(500).json({
      success: false,
      message: '注册失败，请稍后重试',
      error: error.message
    })
  }
})

module.exports = router 