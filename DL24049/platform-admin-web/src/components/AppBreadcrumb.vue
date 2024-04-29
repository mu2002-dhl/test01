<template>
  <!-- 面包屑（放到你想要放的template中的位置） -->
  <el-breadcrumb separator-icon="ArrowRight" style="padding: 10px;">
    <!-- <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item> -->
    <template v-for="(item, index) in breadList">
      <el-breadcrumb-item
        v-if="item.path!=='/' || index === 0 "
        :key="index"
        :to="item.path"
      >
        <el-text class="mx-1">{{ item.meta.title }}</el-text>
      </el-breadcrumb-item>
      <el-breadcrumb-item
        v-else
        :key="item.path"
      >
        <el-text class="mx-1">{{ item.meta.title }}</el-text>
      </el-breadcrumb-item>
    </template>
    <el-divider border-style="double" />
  </el-breadcrumb>
</template>

<script>
import { onMounted, ref, watch } from 'vue'
import { useRoute } from 'vue-router'
export default {
  name: 'appBreadcrumb',
  setup () {
    const route = useRoute()
    const breadList = ref(null)
    const getMatched = () => {
      breadList.value = route.matched.filter(item => item.meta && item.meta.title)
    }
    onMounted(() => {
      getMatched()
    })

    watch(() => route.path, (newValue, oldValue) => { // 监听路由路径是否发生变化，之后更改面包屑
      breadList.value = route.matched.filter(item => item.meta && item.meta.title)
    })

    return {
      breadList
    }
  }
}
</script>
<style>
  .el-breadcrumb{
    font-size: 13px;
  }
</style>
