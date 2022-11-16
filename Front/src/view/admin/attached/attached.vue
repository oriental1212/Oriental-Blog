<template>
  <router-view>
    <el-upload
        name="file"
        class="upload-demo"
        drag
        action="http://localhost:8000/upload"
        multiple
        :on-change="success"
        :on-error="error"
    >
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">
        上传图片请 <em>点击</em> 这里
      </div>
      <template #tip>
        <div class="el-upload__tip">
          图片大小尽量小于500kb
        </div>
      </template>
    </el-upload>
    <el-button @click="submit" type="primary">提交</el-button>
  </router-view>
</template>

<script setup>
import { ref,reactive } from 'vue'
import { UploadFilled } from '@element-plus/icons-vue'
import request from "../../../utils/request.js";

const state = reactive({
  imgUrl: {}
})

const success = () => {
}

const error = () => {

}


</script>

<style scoped>
.img {
  width: 100%;
  display: block;
}
</style>