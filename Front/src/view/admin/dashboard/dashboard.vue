<template>
  <router-view>
    <div>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <span style="font-size:15px;">总文章数</span>
                <el-button type="primary" @click="toWriterArticle"><el-icon><Plus /></el-icon></el-button>
              </div>
            </template>
            <div style="font-size: 30px">
              {{ states.allPage }}
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <span style="font-size:15px;">评论数</span>
                <el-button type="primary" @click="toComment"><el-icon><Plus /></el-icon></el-button>
              </div>
            </template>
            <div style="font-size: 30px">
              {{ states.allComment }}
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <span style="font-size: 15px;">每日一语</span>
              </div>
            </template>
            <div>
              {{ states.power.content }}
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </router-view>
</template>

<script setup>
import {Plus} from '@element-plus/icons-vue'
import {reactive} from "vue";
import router from "@/router/index.js";
import axios from "axios";
import request from "../../../utils/request.js";

const states = reactive({
  allPage: "",
  allComment: "",
  power:{}
})
//跳转编辑文章
const toWriterArticle = () => {
  router.push('/admin/writerarticle')
}
//跳转评论
const toComment = () => {
  router.push('/admin/comment')
}
axios.post("https://api.xygeng.cn/one").then((res) => {
  console.log(res)
  states.power = res.data.data
})

request.get("/getcount").then((res) => {
  states.allPage = res.data[0]
  states.allComment = res.data[1]
})



</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.box-card {
  height: 20vh;
}
</style>