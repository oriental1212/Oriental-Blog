<template>
  <router-view>
    <el-card>
      <el-form
          ref="ruleFormRef"
          :model="ruleForm"
          :rules="rules"
          label-width="120px"
          class="demo-ruleForm"
      >
        <el-form-item label="关键词：" prop="name">
          <el-input style="width: 300px" v-model="ruleForm.keyword" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm"
          >查询</el-button
          >
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
      <el-table :data="states.tableData" style="width: 100%">
        <el-table-column prop="comment.commentcontent" label="评论内容" width="500" />
        <el-table-column prop="articlename" label="评论文章" width="350" />
        <el-table-column prop="comment.commentDate" label="评论时间" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </router-view>
</template>

<script setup>
import {reactive} from "vue";
import request from "../../../utils/request.js";
import {ElNotification} from "element-plus";

request.get("/comment/selectAllComment").then((res) => {
  states.tableData = res.data
})
const load = () => {
  request.get("/comment/selectAllComment").then((res) => {
    states.tableData = res.data
  })
}

const states = reactive({
  tableData: [{
    comment: {},
    articlename: {}
  }]
})
const rules = reactive({
  name: [
    { message: '请输入查询的关键词', trigger: 'blur' },
  ]
})
const ruleForm = reactive({
  keyword: '',
})


const submitForm = () => {
  request.get("/comment/selectcommentbyname/"+ruleForm.keyword).then((res) => {
    if(res.data !== null){
      states.tableData = res.data
      ElNotification({
        type: "success",
        message: "查询成功"
      })
    }else {
      ElNotification({
        type: "error",
        message: "没有找到哟"
      })
    }
  })
}
const resetForm = () => {
  ruleForm.keyword = ''
  load()
}
const handleDelete = (row) => {
  request.delete("/comment/deletebycommentidint/"+row.comment.commentid).then((res) => {
    if(res.code = "200"){
      ElNotification({
        type: "success",
        message: "删除成功"
      })
    }
  })
}
</script>

<style>
</style>