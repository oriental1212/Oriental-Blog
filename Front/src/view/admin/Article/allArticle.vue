<template>
  <router-view>
    <el-card class="box-card">
      <el-form
          ref="ruleFormRef"
          status-icon
          label-width="120px"
          class="demo-ruleForm">
        <el-row :gutter="80">
          <el-col :span="7"><div class="grid-content ep-bg-purple" />
            <el-form-item label="关键字：">
              <el-input v-model="states.selectform.keywords" type="text" clearable placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="7"><div class="grid-content ep-bg-purple" />
            <el-form-item label="分类选择：">
              <el-select v-model="states.selectform.value" placeholder="请选择" clearable >
                <el-option
                    v-for="item in states.options"
                    :key="item.valueOf()"
                    :value="item.valueOf()"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7"><div class="grid-content ep-bg-purple" />
            <el-form-item>
              <el-button type="primary" @click="submitForm">查询</el-button>
              <el-button @click="reset">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <!--展示数据-->
      <el-table :data="states.tableData" style="width: 100%">
        <el-table-column prop="article.articletitle" label="标题" width="300" />
        <el-table-column prop="sortname" label="分类" width="300" />
        <el-table-column prop="article.articleDate" label="时间" width="300" />
        <el-table-column label="操作" width="370">
          <template #default="scope">
            <el-button size="small" @click="Editor(scope.row)">编辑</el-button>
            <el-popconfirm
                confirm-button-text="确认"
                cancel-button-text="点错了"
                title="确定要删除吗"
                @confirm="Delete(scope.row)">
              <template #reference>
                <el-button size="small" type="danger">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </router-view>
</template>

<script setup>

import {reactive} from "vue";
import request from "../../../utils/request.js";
import router from "../../../router/index.js";

const states = reactive({
  keywords: '',
  value: '',
  options: [],
  selectform:{
    keywords: '',
    value: ''
  },
  form: [],
  tableData: [{
    article: {},
    sortname: {}
  }],
})

request.get("/sort/allsortname").then(res => {
  states.options = res.data
})

request.get("/article/findallarticle").then(res => {
  states.tableData = res.data
})

const load = () => {
  request.get("/article/findallarticle").then(res => {
    states.tableData = res.data
  })
}

const submitForm = () => {
  if(states.selectform.value !== ''){
      for (let i = 0; i < states.tableData.length; i++) {
        if (states.tableData[i].sortname !== states.selectform.value){
          delete states.tableData[i]
        }
      }
    }if(states.selectform.keywords !== ''){
      for (let i = 0; i < states.tableData.length; i++) {
        if (states.tableData[i].article.articletitle === states.selectform.keywords) {
          states.form.push(states.tableData[i])
          states.tableData = states.form
        }
      }
    }
}


const reset = ()=> {
  states.selectform.keywords=''
  states.selectform.value=''
  load()
}

const Editor = (row)=> {
  router.push({
    path: '/admin/writerarticle',
    query: {
      date: row.article.articleDate
    }
  })
}
const Delete = (row)=> {
  console.log(row.article.articletitle)
  request.delete("/article/deletebyarticletitleint/"+row.article.articletitle)
      .then((res) => {
        if (res.code === "200"){
          console.log("删除成功")
        }
      })
  load()
}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 20px;
}
</style>