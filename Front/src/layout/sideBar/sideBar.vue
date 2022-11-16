<template>
  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      :unique-opened="true"
      :router='true'
      background-color="#3c454c"
      text-color="#ffffff"
      style="height: calc(100vh - 60px)"
      >
    <el-menu-item index="dashboard"><el-icon><House /></el-icon><template #title><span>主页</span></template>
    </el-menu-item>
    <el-sub-menu index="admin">
      <template #title><el-icon><Edit /></el-icon><span>文章</span></template>
      <el-menu-item index="allarticle">所有文章</el-menu-item>
      <el-menu-item index="writerarticle">写文章</el-menu-item>
      <el-menu-item index="tablearticle">分类目录</el-menu-item>
    </el-sub-menu>
    <el-menu-item index="attached"><el-icon><FolderAdd /></el-icon><template #title>附件</template></el-menu-item>
    <el-menu-item index="comment"><el-icon><ChatDotRound /></el-icon><template #title>评论</template></el-menu-item>
    <el-menu-item index="profile"><el-icon><User /></el-icon><template #title>个人资料</template></el-menu-item>
  </el-menu>
</template>

<script setup>
import {House,Notebook,FolderAdd,Edit,ChatDotRound,Mug,User} from '@element-plus/icons-vue'

</script>

<style lang=css scoped>
/deep/ .el-sub-menu .el-menu-item{
  width: 100%;
}
</style>