<template>
  <div class="common-layout">
    <el-container>
      <el-header><Header/></el-header>
      <el-container>
        <el-aside width="180px">
          <side-bar/>
        </el-aside>
        <el-container style="background-color: #f0f2f5">
          <el-main>
            <router-view></router-view>
          </el-main>
          <el-footer><Footer/></el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import SideBar from "./sideBar/sideBar.vue";
import Header from './header/header.vue';
import Footer from './footer/footer.vue';
</script>
<style scoped>
/deep/ .el-aside{
  overflow-x: hidden;
}
</style>