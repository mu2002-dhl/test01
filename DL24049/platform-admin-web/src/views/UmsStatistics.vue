<template>
  <div class="main">
    <el-row :gutter="12" style="padding-top: 20px;">
      <el-col :span="24">
        <el-card shadow="hover">
          <div id="chart1" style="width: 100%; height: 400px"></div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="12" style="padding-top: 20px;">
      <el-col :span="24">
        <el-card shadow="hover">
          <div id="chart2" style="width: 100%; height: 400px"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>

</template>
<script setup>
import * as echarts from 'echarts'
import { onMounted } from 'vue'
import { statistics } from '@/api/umsPlaces'

onMounted(() => {
  statistics((data) => {
    loadChart1(data.placeNameList, data.placeValueList)
    loadChart2(data.ageNameList, data.ageValueList)
  })
})

function loadChart1 (name, value) {
  if (!name) {
    name = []
  }
  if (!value) {
    value = []
  }
  const chartDom = document.getElementById('chart1')
  const myChart = echarts.init(chartDom)
  const option = {
    title: {
      text: '各景点访问量',
      left: 'center'
    },
    xAxis: {
      type: 'category',
      data: name
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: value,
        type: 'line'
      }
    ]
  }

  option && myChart.setOption(option)
}

function loadChart2 (name, value) {
  if (!name) {
    name = []
  }
  if (!value) {
    value = []
  }
  const chartDom = document.getElementById('chart2')
  const myChart = echarts.init(chartDom)
  const option = {
    title: {
      text: '用户年龄',
      left: 'center'
    },
    xAxis: {
      type: 'category',
      data: name
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: value,
        type: 'line'
      }
    ]
  }

  option && myChart.setOption(option)
}
</script>

<style scoped>
 .main{
    padding: 20px;
  }
  .el-statistic__head{
    font-size: 20px;
  }
  .el-statistic__number{
    font-size: 30px;
  }
  .cardImg{
    font-size: 50px;
  }
</style>
