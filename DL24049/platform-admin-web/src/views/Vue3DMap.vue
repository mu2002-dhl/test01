<template>
  <div>
    <div>
      <div id="main" style="width: 1000px; height: 700px"></div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import 'echarts-gl'
import '@/assets/jiangshu.json'
export default {
  mounted () {
    this.drawLine()
  },
  methods: {
    // 开始画图了
    drawLine () {
      const myChart = echarts.init(document.getElementById('main'))
      const option = {
        tooltip: {
          trigger: 'item',
          showDelay: 0,
          transitionDuration: 0.2,
          formatter: function (params) {
            let value = (params.value + '').split('.')
            value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,')
            return params.seriesName + '<br/>' + params.name + ': ' + value
          }
        },
        visualMap: {
          left: 'left',
          min: 500000,
          max: 3800000,
          inRange: {
            color: '#4D9CFF'
          },
          text: ['High', 'Low'],
          calculable: true,
          show: false
        },
        series: [
          {
            type: 'map3D',
            roam: true,
            map: '江苏',
            label: {
              normal: {
                show: true,
                textStyle: {
                  fontFamily: 'sans-serif', // 文字的字体系列。
                  fontSize: '14', // 文字的字体大小。
                  fontWeight: 500, // 文字字体的粗细。
                  backgroundColor: 'rgba(0,23,11,0)', // 字体背景色
                  distance: 20 // 标签距离图形的距离，在三维的散点图中这个距离是屏幕空间的像素值，其它图中这个距离是相对的三维距离
                }
              }
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#eee'
            },
            emphasis: {
              itemStyle: {
                color: '#A2FFFF'
              }
            },
            // 文本位置修正
            textFixed: {
              Alaska: [20, -20]
            },
            data: [
              { name: '南京市', value: 4822023 },
              { name: '常州市', value: 731449 },
              { name: '徐州市', value: 6553255 },
              { name: '淮安市', value: 2949131 },
              { name: '南通市', value: 3804143 },
              { name: '宿迁市', value: 5187582 },
              { name: '无锡市', value: 3590347 },
              { name: '扬州市', value: 917092 },
              { name: '盐城市', value: 632323 },
              { name: '苏州市', value: 1931751 },
              { name: '泰州市', value: 9919945 },
              { name: '镇江市', value: 1392313 },
              { name: '连云港市', value: 1595728 }
            ],
            viewControl: {
              // 用于鼠标的旋转，缩放等视角控制。
              projection: 'perspective', // 投影方式，默认为透视投影'perspective'，也支持设置为正交投影'orthographic'。
              autoRotate: false, // 是否开启视角绕物体的自动旋转查看。[ default: false ]
              autoRotateDirection: 'cw', // 物体自传的方向。默认是 'cw' 也就是从上往下看是顺时针方向，也可以取 'ccw'，既从上往下看为逆时针方向。
              autoRotateSpeed: 10, // 物体自传的速度。单位为角度 / 秒，默认为10 ，也就是36秒转一圈。
              autoRotateAfterStill: 3, // 在鼠标静止操作后恢复自动旋转的时间间隔。在开启 autoRotate 后有效。[ default: 3 ]
              damping: 0, // 鼠标进行旋转，缩放等操作时的迟滞因子，在大于等于 1 的时候鼠标在停止操作后，视角仍会因为一定的惯性继续运动（旋转和缩放）。[ default: 0.8 ]
              rotateSensitivity: 1, // 旋转操作的灵敏度，值越大越灵敏。支持使用数组分别设置横向和纵向的旋转灵敏度。默认为1, 设置为0后无法旋转。
              // rotateSensitivity: [1, 0]——只能横向旋转； rotateSensitivity: [0, 1]——只能纵向旋转。
              zoomSensitivity: 2, // 缩放操作的灵敏度，值越大越灵敏。默认为1,设置为0后无法缩放。
              panSensitivity: 1, // 平移操作的灵敏度，值越大越灵敏。默认为1,设置为0后无法平移。支持使用数组分别设置横向和纵向的平移灵敏度
              panMouseButton: 'left', // 平移操作使用的鼠标按键，支持：'left' 鼠标左键（默认）;'middle' 鼠标中键 ;'right' 鼠标右键(注意：如果设置为鼠标右键则会阻止默认的右键菜单。)
              rotateMouseButton: 'left', // 旋转操作使用的鼠标按键，支持：'left' 鼠标左键;'middle' 鼠标中键（默认）;'right' 鼠标右键(注意：如果设置为鼠标右键则会阻止默认的右键菜单。)
              distance: 110, // [ default: 100 ] 默认视角距离主体的距离，对于 grid3D 和 geo3D 等其它组件来说是距离中心原点的距离,对于 globe 来说是距离地球表面的距离。在 projection 为'perspective'的时候有效。
              minDistance: 40, // [ default: 40 ] 视角通过鼠标控制能拉近到主体的最小距离。在 projection 为'perspective'的时候有效。
              maxDistance: 400, // [ default: 400 ] 视角通过鼠标控制能拉远到主体的最大距离。在 projection 为'perspective'的时候有效。
              alpha: 40, // 视角绕 x 轴，即上下旋转的角度。配合 beta 可以控制视角的方向。[ default: 40 ]
              beta: -30, // 视角绕 y 轴，即左右旋转的角度。[ default: 0 ]
              minAlpha: -360, // 上下旋转的最小 alpha 值。即视角能旋转到达最上面的角度。[ default: 5 ]
              maxAlpha: 360, // 上下旋转的最大 alpha 值。即视角能旋转到达最下面的角度。[ default: 90 ]
              minBeta: -360, // 左右旋转的最小 beta 值。即视角能旋转到达最左的角度。[ default: -80 ]
              maxBeta: 360, // 左右旋转的最大 beta 值。即视角能旋转到达最右的角度。[ default: 80 ]
              center: [0, 0, 0], // 视角中心点，旋转也会围绕这个中心点旋转，默认为[0,0,0]。
              animation: true, // 是否开启动画。[ default: true ]
              animationDurationUpdate: 1000, // 过渡动画的时长。[ default: 1000 ]
              animationEasingUpdate: 'cubicInOut' // 过渡动画的缓动效果。[ default: cubicInOut ]
            }
          }
        ]
      }
      myChart.setOption(option)
      myChart.on('click', (args) => {
        console.log(args.name)
      })
    }
  }
}
</script>
<style scoped>
#main {
  margin: 150px auto;
  border: 1px solid red;
}
#main canvas {
  position: absolute;
  left: -200px;
}
</style>
