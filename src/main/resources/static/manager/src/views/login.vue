<template>
  <div class="login-container">
    <el-form class="login-form" ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="80px" style="width:80%">
      <el-form-item class="login-form-item" label="用户名" prop="username">
           <el-input v-model="metadata.ruleForm.username"></el-input>
      </el-form-item>
      <el-form-item class="login-form-item" label="密码" prop="password">
            <el-input type="password" v-model="metadata.ruleForm.password"></el-input>
      </el-form-item>
      <el-form-item>
            <el-button type="primary" @click="submitForm(ruleFormRef)">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template> 


<script setup>
import { reactive } from 'vue'

const login = () => {

  //校验用户名
  const checkUsername = function (rule, value, callback) {
      if (value.trim() === "") {
          callback(new Error('请输入用户名111'))
      } else {
          callback()
      }
  }

  //校验密码
  const checkPassword = function (rule, value, callback) {
      if (value.trim() === "") {
          callback(new Error('请输入密码222'))
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
              { required: true, validator: login().checkUsername, message: '请输入用户名', trigger: 'blue' }
          ],
          password: [
              { required: true, validator: login().checkPassword,  message: '请输入密码', trigger: 'blue' }
          ]
      }
  })
  
  //表单提交
  const submitForm = async (formEl) => {
      if (!formEl) return;
      await formEl.validate((valid, fields) => {
          if (valid) {
            login();
          } else {
              console.log("error submit!", fields);
          }
      })
  }
</script>

<style>
.login-container {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center;     /* 垂直居中 */
  height: 100vh;           /* 占满全屏 */
}
.login-form{
  width: 400px;              /* 表单宽度 40% */
  height: 20%;
  min-width: 300px;        /* 防止太小 */
  background: #fff;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.1);
}
</style>