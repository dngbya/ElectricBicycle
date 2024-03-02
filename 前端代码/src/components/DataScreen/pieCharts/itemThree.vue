<template>
  <div ref="chartsRef" class="echarts" />
</template>
<script lang="ts" setup>
import * as echarts from 'echarts'
import { EChartsType } from 'echarts/core'
import { onMounted, ref, reactive } from 'vue'
const chartsRef = ref<HTMLElement | null>()
const data = reactive([
  { value: 40, name: '双理楼' },
  { value: 52, name: '食堂' },
  { value: 27, name: '九栋' },
  { value: 13, name: '操场' },
  { value: 13, name: '田家炳' },
  { value: 11, name: '红场' },
])
let options = {
  title: {
    top: 10,
    left: 'center',
    text: '充电点使用情况',
    textStyle: {
      color: '#5FBBEB',
      fontSize: 25,
      fontWeight: 5,
    },
  },
  legend: {
    orient: 'vertical',
    //left: 'left',
    left: 10,
    top: 30,
    itemHeight: 40,
    itemWidth: 50,
    textStyle: {
      fontSize: 20,
      color: '#5FBBEB',
    },
  },
  tooltip: {
    show: true,
    backgroundColor: "rgba(17,95,182,0.5)",
    axisPointer: {
      label: {
        fontSize: '20',
      },
    },
    textStyle: {
      color: "#49D2FC",
      fontSize: 20,
    },
  },
  series: [
    {
      type: 'pie',
      data: data,
      //radius: [10, 95],
      radius: [30, 200],
      center: ['50%', '55%'],
      roseType: 'area',
      itemStyle: {
        borderRadius: 30,
      },
      //关闭指示线及文字
      label: {
        normal: {
          show: false,
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
  width: 100%;
  height: 100%;
}
</style>
