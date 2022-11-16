<template>
  <router-view>

    <!--顶部-->
    <el-row class="row-bg" justify="space-between" style="margin-bottom: 20px">
      <el-col :span="6"><div class="grid-content ep-bg-purple" />
        <div class="flex items-center" style="font-size: 30px">
          {{ state.title }}
        </div>
      </el-col>
      <el-col :span="2"><div class="grid-content ep-bg-purple" />
        <div class="flex items-center">
          <el-button type="primary" @click="dialogVisible = true">发布</el-button>
        </div>
      </el-col>
    </el-row>

    <!--Markdown-->
    <el-input style="margin-bottom: 20px" v-model="state.title"></el-input>
    <mark-down ref="markdownEditor" v-model="state.model.spdd_content" height="500px"></mark-down>

    <!--弹窗框-->
    <el-dialog
        v-model="dialogVisible"
        title="文章设置"
        width="30%">
      <el-form
          ref="ruleFormRef"
          :model="state"
          :rules="rules"
          label-width="120px">
        <el-form-item label="文章分类" prop="region">
          <el-select v-model="state.region" placeholder="请选择分类" clearable="true">
            <el-option
                v-for="item in state.options"
                :key="item.sortname.valueOf()"
                :value="item.sortname.valueOf()"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit">提交</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </router-view>
</template>

<script setup>
import MarkDown from "./markDown.vue";
import {getCurrentInstance, reactive, ref} from "vue";
import request from "../../../utils/request.js";
import getCurrentTime from "../../../utils/getCurrentTime.js"
import { useRouter } from 'vue-router'

const router = useRouter()
const { proxy } = getCurrentInstance()

request.get("/sort/allsort").then(res => {
  state.options = res.data
})
if(router.currentRoute.value.query.date){
  request.get("/article/getatricle/"+router.currentRoute.value.query.date).then((res) => {
    if (res.code === "200"){
      state.title = res.data[1]
      proxy.$refs.markdownEditor.setValue(res.data[0])
    }
  })
}

let dialogVisible = ref(false)

const rules = reactive({
  region: [
    {
      required: true,
      trigger: 'change',
    }]
})
const state = reactive({
  model:{
    spdd_content: ''
  },
  title: '',
  date: "",
  region: '',
  sortid: 0,
  options: []
})
const from = reactive({
  username: '',
  articletitle: '',
  articleDate: "",
  sortid: '',
  articletext: '',
})

const load = () => {
  state.sortid = 0
}

const submit = () => {
  //获取sortid
  state.options.forEach((option) => {
    if (option.sortname === state.region){
      state.sortid = option.sortid
    }
  })
  from.sortid = state.sortid
  //获取时间
  state.date = getCurrentTime()
  from.articleDate = state.date
  //text赋值
  from.articletext = state.model.spdd_content = proxy.$refs.markdownEditor.getValue()
  //用户name获取
  from.username = window.sessionStorage.getItem('token')
  //获取标题
  from.articletitle = state.title
  request.put('/article/newarticle',from).then((res) => {
    console.log(res.msg)
  })
  proxy.$refs.markdownEditor.setValue('')
  state.title = ''
  dialogVisible = false
  load()
}

</script>

<style scoped>

</style>