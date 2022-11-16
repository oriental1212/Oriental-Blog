<template>
  <div>
    <div style="width: 400px; margin: 200px auto;">
      <h1 style="text-align: center; margin-bottom: 30px; font-size: 25px">Oriental-Blog</h1>
      <el-form :model="user" :rules="rules" ref="ruleFormRef" size="large">
        <el-form-item prop="username">
          <el-input placeholder="用户名" v-model="user.username"/>
        </el-form-item>
        <el-form-item prop="userpassword">
          <el-input placeholder="密码" v-model="user.userpassword"/>
        </el-form-item>
        <div>
          <el-link type="primary" @click="registerbyour">点击我注册</el-link>
        </div>
        <el-form-item>
          <el-button type="primary" @click="login" style="width: 100%;margin-top: 20px">登 录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { reactive,getCurrentInstance  } from "vue"
import {ElNotification} from "element-plus"
import request from "../../utils/request.js"
import router from "../../router/index.js"
import {useStore} from "vuex";

const { proxy } = getCurrentInstance()
const store = useStore()

const rules = reactive({
  username: [
    {required: true, message: "请输入用户名", trigger: 'blur'}
  ],
  userpassword:[
    {required: true, message: "请输入密码", trigger: 'blur'}
  ]
})

const user = reactive({})

const registerbyour = () => {
  router.push("/register")
}

const login = () =>{
  store.state.user = user
  window.sessionStorage.setItem('token',user.username)
  proxy.$refs.ruleFormRef.validate((valid) => {
    if(valid){
      request.post('/user/login', user).then(res => {
        if(res.code === "200"){
          ElNotification({
            type: "success",
            message: "登录成功"
          })
          router.push("/admin")
        }else{
          ElNotification({
            type: "error",
            message: "用户名或密码错误"
          })
        }
      })
    } else{
      ElNotification({
        type: 'error',
        message: '登陆失败'})
    }
  })
}

</script>

<style scoped>

</style>