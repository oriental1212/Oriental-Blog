<template>
  <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      :router='true'
      mode="horizontal"
      :ellipsis="false"
      @select="handleSelect">
    <el-menu-item index="dashboard">Oriental—Blog</el-menu-item>
    <div class="flex-grow" />
    <el-sub-menu index="1">
      <template #title>
        <el-avatar :src="qqHeader" />
      </template>
      <el-menu-item index="profile"><span style="margin:0 auto">个人资料</span></el-menu-item>
      <el-popconfirm
          confirm-button-text="确认登出"
          cancel-button-text="点错了"
          @confirm="gologin"
          :icon="InfoFilled"
          icon-color="#626AEF"
          title="你确定要登出吗？"
          width="auto"
      >
        <template #reference>
          <el-menu-item index="">
            <span style="margin:0 auto">退出登录</span>
          </el-menu-item>
        </template>
      </el-popconfirm>
    </el-sub-menu>
  </el-menu>
</template>

<script setup>
import { ref } from 'vue'
import router from "../../router/index.js";
import { InfoFilled } from '@element-plus/icons-vue'

const activeIndex = ref('1')
const handleSelect = (key, keyPath) => {
  console.log(key, keyPath)
}

const gologin = () => {
  window.sessionStorage.clear()
  router.push('/login')
}
//这里更改头像
const qqHeader = ref("http://q2.qlogo.cn/headimg_dl?dst_uin=1304206691&spec=100")
</script>

<style>
.flex-grow {
  flex-grow: 1;
}
</style>