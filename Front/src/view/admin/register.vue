<template>
  <div style="width: 400px; margin: 200px auto;">
    <h1 style="text-align: center; margin-bottom: 30px; font-size: 25px">Oriental-register</h1>
    <el-form :model="user" :rules="rules" ref="allRule" size="large">
      <el-form-item prop="username">
        <el-input placeholder="用户名" v-model="user.username"/>
      </el-form-item>
      <el-form-item prop="userpassword">
        <el-input placeholder="密码" v-model="user.userpassword" />
      </el-form-item>
      <div>
        <el-link type="primary" @click="loginBy">点击我返回登录</el-link>
      </div>
      <el-form-item>
        <el-button type="primary" @click="onRegister" style="width: 100%;margin-top: 20px">注 册</el-button>
      </el-form-item>
    </el-form>
  </div>>
</template>

<script setup>
import {getCurrentInstance, reactive} from 'vue'
import {ElNotification} from "element-plus"
import request from "../../utils/request.js"
import router from "../../router/index.js"

const { proxy } = getCurrentInstance()

const user = reactive({})

const rules = reactive({
  username: [
    {required: true, message: "请输入注册用户名", trigger: 'blur'},
  ],
  userpassword:[
    {required: true, message: "请输入注册密码", trigger: 'blur'}
  ]
})

const loginBy = () => {
  router.push("/login")
}

const onRegister = () =>{
  proxy.$refs.allRule.validate((valid) => {
    if(valid){
      request.post('/user/register', user).then(res => {
        if(res.code === "200"){
          ElNotification({
            type: "success",
            message: "注册成功"
          })
          router.push("/login")
        }else{
          ElNotification({
            type: "error",
            message: "该用户已注册"
          })
        }
      })
    } else{
      ElNotification({
        type: 'error',
        message: '请完整输入用户名和密码'})
    }
  })
}
</script>