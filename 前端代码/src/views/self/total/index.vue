<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header"> 
        <Header/>
      </el-header>
      <el-main class="main">
        <h2>充电订单详情</h2>
        <Charging />
        <h2>充值订单</h2>
        <Topup />
      </el-main>
    </el-container>
    <template>
      Scroll down to see the bottom-right button.
      <el-backtop :right="100" :bottom="100" /> </template>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import Charging from './componetns/listCharging/index.vue'
import Topup from './componetns/listTopup/index.vue'
import Header from './componetns/header/index.vue'
</script>

<style scoped>
.common-layout {
  width: 100%;
  background-color: white;
}
.main {
  width: 100%;
}
.header {
  padding: 1%;
  width: 100%;
  height: 20%;
}
h2 {
  margin-left: 1.5%;
  color: rgb(96, 95, 95);
}
</style>