<template>
  <div class="echarts" ref="chartsRef" />
</template>
<script setup lang="ts">
import * as echarts from 'echarts'
import { EChartsType } from 'echarts/core'
import { onMounted, ref, reactive } from 'vue'
const chartsRef = ref<HTMLElement | null>()
const options = {
  title: {
    top: 10,
    left: 'center',
    text: '用户使用系统情况',
    textStyle: {
      color: '#5FBBEB',
      fontSize: 25,
      fontWeight: 5,
    },
  },
  tooltip: {
          trigger: "axis",
          backgroundColor: "rgba(17,95,182,0.5)",
          textStyle: {
            color: "#49D2FC",
            fontSize:20,
          },
          axisPointer:{
            type:"shadow"
          }
        },
  grid: {
    top: '12%',
    left: '1%',
    right: '6%',
    bottom: '3%',
    containLabel: true,
  },
  xAxis: {
    type: 'value',
    axisLine: {
      lineStyle: {
        color: '#5FBBEB',
      },
    },
    axisLabel:{
      fontSize:20
    }
  },
  yAxis: {
    type: 'category',
    data: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期天'],
    axisLine: {
      lineStyle: {
        color: '#5FBBEB',
      },
    },
    axisLabel:{
      fontSize:15
    }
  },
  series: [
    {
      data: [1209, 692, 986, 1078, 1482, 949, 812],
      type: 'bar',
      top:20,
      itemStyle: {
        normal: {
          barBorderRadius: [0, 20, 20, 0],
          color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
            {
              offset: 0,
              color: '#005eaa',
            },
            {
              offset: 0.5,
              color: '#339ca8',
            },
            {
              offset: 1,
              color: '#cda819',
            },
          ]),
        },
      },
    },
  ],
}
let chart: EChartsType
const initChart = () => {
  const chart = echarts.init(chartsRef.value)
  chart.setOption(options)
  return chart
}
onMounted(() => {
  chart = initChart()
  window.addEventListener('resize', function () {
    chart && chart.resize()
  })
})
</script>
<style lang="scss" scoped>
.echarts {
  height: 100%;
  width: 100%;
}
</style>
