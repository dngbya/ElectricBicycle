<template>
  <div ref="chartsRef" class="echarts" />
</template>
<script lang="ts" setup>
import * as echarts from 'echarts'
import { EChartsType } from 'echarts/core'
import { onMounted, ref } from 'vue'
const chartsRef = ref<HTMLElement | null>()

let colorList = ['#46ea91', '#2ba0ff', '#ed593b', '#7357ff', '#f2d750']
const options = {
  title: {
    top: 10,
    left: 'center',
    text: '用户充电情况',
    textStyle: {
      color: '#5FBBEB',
      fontSize: 25,
      fontWeight: 5,
    },
  },
  legend: {
    icon: 'circle',
    top: 50,
    right: '5%',
    itemWidth: 40,
    itemGap: 5,
    textStyle: {
      fontSize: 20,
      color: '#5FBBEB',
      padding: [3, 0, 0, 0],
    },
  },
  tooltip: {
    trigger: 'axis',
    backgroundColor: 'rgba(17,95,182,0.5)',
    axisPointer: {
      type: 'cross',
      label: {
        fontSize: '20',
      },
    },
    textStyle: {
      color: '#49D2FC',
      fontSize: 20,
    },
  },
  grid: {
    top: '14%',
    left: '3%',
    right: '4%',
    bottom: '10%',
    containLabel: true,
  },
  xAxis: [
    {
      type: 'category',
      data: ['1', '2', '3', '4', '5', '6', '7'],
      axisLine: {
        lineStyle: {
          color: '#33BBFF',
        },
      },
      axisTick: {
        show: false,
      },
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
      axisPointer: {
        label: {
          // padding: [11, 5, 7],
          padding: [0, 0, 0, 0],
          // 这里的margin和axisLabel的margin要一致!
          margin: 10,
          // 移入时的字体大小
          fontSize: 12,
          backgroundColor: 'rgba(0,0,0,0)',
        },
      },
      boundaryGap: false,
    },
  ],
  yAxis: [
    {
      type: 'value',
      name: '次数/天',
      nameTextStyle: {
        fontSize: 20,
      },
      axisTick: {
        show: false,
      },
      axisLine: {
        show: true,
        lineStyle: {
          color: '#05D5FF',
        },
      },
      axisLabel: {
        fontSize: 20,
        textStyle: {
          color: '#5FBBEB',
        },
      },
      splitLine: {
        show: false,
      },
    },],
  series: [
    {
      name: '学生',
      type: 'line',
      data: [328, 371, 216, 256, 198, 130, 220, 180],
      smooth: true,
      showSymbol: false,
      stack: 'Total',
      emphasis: {
        focus: 'series',
      },

      areaStyle:{
            normal: {
                //线性渐变，前4个参数分别是x0,y0,x2,y2(范围0~1);相当于图形包围盒中的百分比。如果最后一个参数是‘true’，则该四个值是绝对像素位置。
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: "rgb(128, 255, 165,.6)",
                    },
                    {
                        offset: 1,
                        color: "rgb(128, 255, 165,0)",
                    }
                ], false),
            }
        },
        lineStyle: {
            normal: {
                width: 4,
                color: "rgb(128, 255, 165)",// 线条颜色
            },
        },
        itemStyle: {
          color: "rgb(128, 255, 165)",
        },
    },
    {
      name: '教师',
      type: 'line',
      data: [220, 212, 211, 140, 125, 116, 110, 120],
      stack: 'Total', //数据堆叠
      smooth: true, //折线图平滑效果 变成曲线图
      showSymbol: false, //   隐藏所有数据点
      areaStyle:{
            normal: {
                //线性渐变，前4个参数分别是x0,y0,x2,y2(范围0~1);相当于图形包围盒中的百分比。如果最后一个参数是‘true’，则该四个值是绝对像素位置。
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: "rgb(147, 112, 219,0.6)",
                    },
                    {
                        offset: 1,
                        color: "rgb(147, 112, 219,0)",
                    }
                ], false),
            }
        },
        lineStyle: {
            normal: {
                width: 4,
                color: "rgb(147, 112, 219)", // 线条颜色
            },
        },
        itemStyle: {
          color: "rgb(147, 112, 219)",
        },
      emphasis: {
        //设置高亮的图形样式和标签样式
        focus: 'series', //只显示选中的内容高亮
      },
    },
    {
      name: '其他',
      type: 'line',
      data: [150, 120, 120, 110, 70, 50, 60, 60],
      stack: 'Total', //数据堆叠
      smooth: true, //折线图平滑效果 变成曲线图
      showSymbol: false, //   隐藏所有数据点

      areaStyle: {
        normal: {
          //线性渐变，前4个参数分别是x0,y0,x2,y2(范围0~1);相当于图形包围盒中的百分比。如果最后一个参数是‘true’，则该四个值是绝对像素位置。
          color: new echarts.graphic.LinearGradient(0,0,0,1,[
              {
                offset: 0,
                color: 'rgba(0,255,255,.6)',
              },
              {
                offset: 1,
                color: 'rgba(0,255,255, 0)',
              },
            ],
            false,
          ),
        },
      },
      lineStyle: {
            normal: {
                width: 4,
                color: "#00ffff", // 线条颜色
            },
        },
        itemStyle:{
          color: "#00ffff",
        },
      emphasis: {
        //设置高亮的图形样式和标签样式
        focus: 'series', //只显示选中的内容高亮
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
