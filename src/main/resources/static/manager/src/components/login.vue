<template>
<div class="card-header">
    <h2>用户登录</h2>
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
              :loading="loading"
              class="login-button"
          >
            登录
          </el-button>
            没有账号?
          <el-button
            @click="$emit('switch-component', 'reg')"
            class="reg-button"
          >去注册
          </el-button>
        </el-form-item>
      </el-form>
</div>

</template>

<script setup>
import { reactive, ref } from 'vue'

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

<style lang="css">
.login-button{
    margin-right: 10px;
    border: red solid 1px;
}

.login-form{
    align-items:center
}
</style>