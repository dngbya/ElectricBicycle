<template>
  <full-screen-container>
    <div class="m-data-screen">
      <div class="header">
        <div class="header-bg-title">访问量大数据展示平台</div>
        <div class="date">{{timeDate}} {{ hourTime }}  </div>
      </div>
      <div class="center">
        <div class="item" v-for="item in list" :key="item.num">
          <div class="item-icon item-icon1">
            <img src="@/assets/image/circle-bg.png" class="circle-bg" />
          </div>
          <div class="item-right">
            <div class="item-right-inner">
              <div class="text-title">{{item.name}}</div>
              <div class="text-number"
              ><count-to :startVal="0" :endVal="item.n" :duration="4000" separator=""></count-to
              ></div>
              <div class="text-der text-decenter-wrapr">
                <span class="left">同比昨天</span>
                <img src="@/assets/image/allow.png" />
                <span class="right">+{{item.num}}%</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="footer">
        <div class="left">
          <div class="item-complex" style="margin-bottom: 20px">
            <line-charts width="100%" height="100%" />
          </div>
          <div class="item-complex">
            <MultilineCharts />
          </div>
        </div>
        <div class="middle">
         <div class="migration">
           <migration-charts height="100%" width="100%" id="migration" />
         </div>
        </div>
        <div class="right">
          <div class="item-complex" style="margin-bottom: 20px">
            <BarCharts width="100%" height="100%" />
          </div>
          <div class="item-complex">
            <PieCharts />
          </div>
        </div>
      </div>
    </div>
  </full-screen-container>
</template>
<script lang="ts" setup>
import {onBeforeUnmount, onMounted, reactive, ref} from 'vue'
import FullScreenContainer from './components/full-screen-container.vue'
import CountTo from '@/components/CountTo/index.vue'
import LineCharts from '@/components/DataScreen/lineEcharts/index.vue'
import BarCharts from '@/components/DataScreen/barEcharts/index.vue'
import MigrationCharts from '@/components/DataScreen/migrationEcharts/index.vue'
import MultilineCharts from '@/components/DataScreen/Multiline/index'
//import PieCharts from '@/components/DataScreen/Pie/index'
import PieCharts from '@/components/DataScreen/pieCharts/itemThree.vue'

const list = ref([{name:'用户访问量',num:10,n:6785},
{name:'用户注册量',num:14,n:1342},
{name:'用户充电量',num:9,n:879},
{name:'用户充值量',num:23,n:597},
{name:'用户报修量',num:7,n:98},
])

const timeDate = ref()
const hourTime = ref()
const time1 = ref()
const getHour = ()=>{
  let date = new Date()
  let hour = date.getHours().toString().padStart(2,'00')
  let minute = date.getMinutes().toString().padStart(2,'00')
  let second = date.getSeconds().toString().padStart(2,'00')
  hourTime.value =  `${hour}:${minute}:${second}`
  time1.value = setTimeout(()=>{
    time1.value&&clearTimeout(time1.value)
    getHour()
  },1000)
}

const getNowTime = ()=>{
  let date = new Date()
  let year = date.getFullYear()
  let month = date.getMonth()+1
  let day = date.getDate()
  timeDate.value = `${year}年${month}月${day}日`
  getHour()

}
onMounted(()=>{
  getNowTime()
})

onBeforeUnmount(()=>{
  time1.value = null
})
</script>

<style lang="scss" scoped>
@import "./index";
</style>
