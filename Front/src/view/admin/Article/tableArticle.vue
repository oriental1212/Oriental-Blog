<template>
  <router-view>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card class="box-card">
          <el-form
              ref="ruleFormRef"
              :model="ruleForm"
              :rules="rules"
              label-width="120px"
              class="demo-ruleForm"
          >
            <el-form-item label="类别名称" prop="name">
              <el-input v-model="ruleForm.sortname" />
            </el-form-item>
            <el-form-item label="描述" prop="desc">
              <el-input v-model="ruleForm.sortdescription" type="textarea" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm">保存</el-button>
              <el-button @click="resetForm">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>

      <el-col :span="16">
        <el-card>
          <el-table :data="state.tabledata" style="width: 100%">
            <el-table-column prop="sortname" label="类别名称" width="250" />
            <el-table-column prop="sortdescription" label="类别描述" width="250" />
            <el-table-column label="操作">
              <template #default="scope">
                <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
                <el-popconfirm
                    confirm-button-text="确认"
                    cancel-button-text="点错了"
                    title="确定要删除吗"
                    @confirm="handleDelete(scope.row)">
                  <template #reference>
                    <el-button size="small" type="danger">删除</el-button>
                  </template>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </router-view>
</template>

<script setup>
import {reactive} from "vue";
import request from "../../../utils/request.js";

const state = reactive({
  tabledata: []
})

request.get('/sort/allsort').then((res) => {
  state.tabledata=res.data
})

const load = () => {
  request.get('/sort/allsort').then((res) => {
    state.tabledata=res.data
  })
}

const ruleForm = reactive({
  sortname: '',
  sortdescription: '',
})
const rules = reactive({
  name: [
    { required: true, message: '请输入类别名称', trigger: 'blur' },
    { min: 3, max: 5, message: '长度需要大于3小于5', trigger: 'blur' },
  ],desc: [
    { required: true, message: '请输入描述', trigger: 'blur' },
  ]
})

const submitForm = (ruleForm) => {
  request.put("/sort/"+ruleForm.sortname+"/"+ruleForm.sortdescription).then((res) => {
    console.log("更新成功")
    load()
  })
}

const resetForm = () => {
  ruleForm.sortname = ''
  ruleForm.sortdescription = ''
}

const handleEdit = (row) => {
  ruleForm.sortname = row.sortname
  ruleForm.sortdescription = row.sortdescription
}
const handleDelete = (row) => {
  request.delete("/sort/deletesortbyname/"+row.sortname)
  load()
}
</script>

<style scoped>

</style>