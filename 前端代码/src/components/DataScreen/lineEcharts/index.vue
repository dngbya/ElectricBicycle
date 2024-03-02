<template>
  <div class="echarts" ref="chartsRef" />
</template>
<script setup lang="ts">
import BarCharts from './components/bar.vue'
import * as echarts from 'echarts'
import { EChartsType } from 'echarts/core'
import { onMounted, ref, reactive } from 'vue'
const chartsRef = ref<HTMLElement | null>()

const options = {
  title: {
    top: 10,
    left: 'center',
    text: '停车点使用情况',
    textStyle: {
      color: '#5FBBEB',
      fontSize: 25,
      fontWeight: 5,
    },
  },
  grid: {
    left: '3%',
    top: '30%',
    right: '4%',
    bottom: '3%',
    containLabel: true,
  },
  xAxis: {
    type: 'category',
    axisLabel: {
        interval: 0,
        textStyle: {
          color: '#5FBBEB',
        },
        // 默认x轴字体大小
        fontSize: 20,
        // margin:文字到x轴的距离
        margin: 10,
      },
    data: ['1', '2', '3', '4', '5', '6', '7'],
    axisLine: {
      lineStyle: {
        color: '#5FBBEB',
      },
    },
  },
  yAxis: {
    type: 'value',
    axisLabel: {
        interval: 0,
        textStyle: {
          color: '#5FBBEB',
        },
        // 默认x轴字体大小
        fontSize: 20,
        // margin:文字到x轴的距离
        margin: 10,
      },
    axisLine: {
      lineStyle: {
        color: '#5FBBEB',
      },
    },
  },
  legend: {
    top:'15%',
    right: '5%',
    textStyle: {
      fontSize: 20,
      color: '#5FBBEB',
      padding: [3, 0, 0, 0],
    },
  },
  tooltip: {
    backgroundColor: "rgba(17,95,182,0.5)",
    textStyle: {
            color: "#49D2FC",
            fontSize:20,
          },
    trigger: 'axis',
    axisPointer: {
      type: 'shadow',
    },
  },
  series: [
    {
      name: '九栋',
      type: 'bar',
      data: [123, 113, 120, 119, 114, 109, 104, 100, 103],
      stack: 'total',
      label: {
        show: true,
      },
      emphasis: {
        focus: 'series',
      },
    },
    {
      name: '双理楼',
      type: 'bar',
      data: [130, 127, 120, 147, 154, 146, 153, 150, 147],
      stack: 'total',
      label: {
        show: true,
      },
      emphasis: {
        focus: 'series',
      },
    },
    {
      name: '食堂',
      type: 'bar',
      data: [207, 190, 177, 215, 201, 192, 199, 184, 191],
      itemStyle:{
        color:'#5FBBEB'
      },
      stack: 'total',
      label: {
        show: true,
      },
      emphasis: {
        focus: 'series',
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
