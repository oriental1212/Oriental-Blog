<template>
  <router-view>
    <el-row :gutter="20">
      <el-col :span="10">
        <el-card class="box-card">
          <div style="text-align: center">
            <el-avatar size="large" src="http://q2.qlogo.cn/headimg_dl?dst_uin=1304206691&spec=100"/>
          </div>
          <el-divider />
          <div style="text-align: center">
            用户名：{{state.user.username}}<br/>
            用户邮箱: {{state.user.useremail}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="14">
        <el-card>
          <div>
            <span>个人资料</span>
          </div>
          <div style="text-align: center">
            <el-form
                ref="ruleFormRef"
                :model="state.user"
                :rules="rules"
                label-width="100px"
                class="demo-ruleForm">
              <el-form-item label="用户名" prop="username">
                <el-input v-model="state.user.username" />
              </el-form-item>
              <el-form-item label="密码" prop="userpassword">
                <el-input v-model="state.user.userpassword" />
              </el-form-item>
              <el-form-item label="邮箱" prop="useremail">
                <el-input v-model="state.user.useremail" />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm">保存</el-button>
                <el-button type="primary" @click="getUser">获取</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </router-view>
</template>

<script setup>
import {computed, onBeforeMount, reactive, ref} from "vue";
import request from "../../../utils/request.js";
import {useStore} from "vuex";

const store = useStore()
let name = ref({})
const state = reactive({
  user: {
    userid: "",
    username: "",
    userpassword: "",
    useremail: ""
  }
})

const getUser = () => {
  name = window.sessionStorage.getItem('token')
  console.log(name)
  request.get("/user/selectuserbyname/"+name).then((res) => {
    state.user=res.data
  })
  console.log(state.user)
}

const rules = reactive({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
  ],
  useremail: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
  ],
  userpassword: [
    { required: true, message: '请输入密码', trigger: 'blur' },
  ]
})

const submitForm = () => {
  request.put("/user/update", state.user).then((res) => {
    if (res.code=== "200"){
      console.log("更新成功")
    }
  })
}
</script>

<style scoped>

</style>