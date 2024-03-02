<template>
  <div ref="chartsRef" class="echarts"/>
</template>
<script lang="ts" setup>
import * as echarts from 'echarts'
import { EChartsType } from 'echarts/core'
import { onMounted, ref ,reactive} from 'vue'
const chartsRef = ref<HTMLElement | null>()
  const data = reactive([
  { value: 40, name: '亚洲' },
  { value: 52, name: '非洲' },
  { value: 27, name: '欧洲' },
  { value: 13, name: '北美洲' },
  { value: 9, name: '南美洲' },
  { value: 11, name: '大洋洲' },
])
let options={
    legend: {
      orient: 'vertical',
      left: 'left',
    },
    tooltip: {
      show: true,
    },
    series: [
      {
        type: 'pie',
        data: data,
        radius: [10, 95],
        center: ['52%', '55%'],
        roseType: 'area',
        itemStyle: {
          borderRadius: 20,
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
.echarts{
  width: 100%;
  height: 100%;

}
</style>
