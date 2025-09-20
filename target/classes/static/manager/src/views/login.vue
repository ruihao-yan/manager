<template>
  <div class="login-container">
    <div class="login-card">
      <div class="card-header">
        <h2>用户登录</h2>
      </div>
      <el-form
          class="login-form"
          ref="ruleFormRef"
          :model="metadata.ruleForm"
          :rules="metadata.rules"
          label-width="80px"
      >
        <el-form-item class="login-form-item" label="用户名" prop="username">
          <el-input
              v-model="metadata.ruleForm.username"
              placeholder="请输入用户名"
              prefix-icon="User"
              class="login-input"
          ></el-input>
        </el-form-item>
        <el-form-item class="login-form-item" label="密码" prop="password">
          <el-input
              type="password"
              v-model="metadata.ruleForm.password"
              placeholder="请输入密码"
              prefix-icon="Lock"
              class="login-input"
              show-password
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
              type="primary"
              @click="submitForm(ruleFormRef)"
              class="login-button"
              :loading="loading"
          >
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'

const ruleFormRef = ref()
const loading = ref(false)

const login = () => {
  //校验用户名
  const checkUsername = function (rule, value, callback) {
    if (value.trim() === "") {
      callback(new Error('请输入用户名'))
    } else {
      callback()
    }
  }

  //校验密码
  const checkPassword = function (rule, value, callback) {
    if (value.trim() === "") {
      callback(new Error('请输入密码'))
    } else {
      callback()
    }
  }

  return {
    checkUsername,
    checkPassword
  }
}

const metadata = reactive({
  ruleForm: {
    username: "",
    password: ""
  },
  rules: {
    username: [
      { required: true, validator: login().checkUsername, trigger: 'blur' }
    ],
    password: [
      { required: true, validator: login().checkPassword, trigger: 'blur' }
    ]
  }
})

//表单提交
const submitForm = async (formEl) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      loading.value = true
      // 模拟登录过程
      setTimeout(() => {
        loading.value = false
        console.log("登录成功!", metadata.ruleForm);
      }, 1500)
    } else {
      console.log("error submit!", fields);
    }
  })
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  animation: gradientShift 10s ease infinite;
  background-size: 400% 400%;
}

@keyframes gradientShift {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}

.login-card {
  width: 450px;
  min-width: 350px;
  background: rgba(255, 255, 255, 0.9);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  padding: 40px 30px;
  transform: translateY(0);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.login-card:hover {
  transform: translateY(-10px);
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.3);
}

.card-header {
  text-align: center;
  margin-bottom: 30px;
}

.card-header h2 {
  margin: 0;
  color: #333;
  font-size: 28px;
  font-weight: 600;
  background: linear-gradient(135deg, #667eea, #764ba2);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.login-form {
  width: 100%;
}

.login-form-item {
  margin-bottom: 25px;
}

.login-form-item :deep(.el-form-item__label) {
  color: #555;
  font-weight: 500;
}

.login-input {
  height: 45px;
}

.login-input :deep(.el-input__inner) {
  border-radius: 10px;
  border: 2px solid #e1e5e9;
  transition: all 0.3s ease;
  padding-left: 40px;
}

.login-input :deep(.el-input__inner):focus {
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.2);
}

.login-input :deep(.el-input__prefix) {
  left: 12px;
  color: #909399;
}

.login-button {
  width: 100%;
  height: 45px;
  border-radius: 10px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  font-size: 16px;
  font-weight: 600;
  letter-spacing: 1px;
  transition: all 0.3s ease;
  margin-top: 10px;
}

.login-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(102, 126, 234, 0.4);
}

.login-button:active {
  transform: translateY(0);
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.login-card {
  animation: fadeInUp 0.6s ease-out forwards;
}

/* 响应式设计 */
@media (max-width: 500px) {
  .login-card {
    width: 90%;
    padding: 30px 20px;
    min-width: auto;
  }

  .card-header h2 {
    font-size: 24px;
  }
}
</style>
