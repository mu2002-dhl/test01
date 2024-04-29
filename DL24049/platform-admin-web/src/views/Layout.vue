<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <appHeader/>
      </el-header>
      <el-container>
        <el-aside>
          <appAside/>
        </el-aside>
        <el-container>
          <el-header style="height: 30px;">
            <appBreadcrumb/>
          </el-header>
          <el-main style="height: 500px;">
            <RouterView />
          </el-main>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import appHeader from '@/components/AppHeader.vue'
import appAside from '@/components/AppAside.vue'
import appBreadcrumb from '@/components/AppBreadcrumb.vue'
export default {
  name: 'VueLayout',
  components: {
    appHeader,
    appAside,
    appBreadcrumb
  },
  setup () {

  }
}
</script>

<style>
.el-header{
  --el-header-padding: 0 0px;
}
.el-col-12{
  max-width: 100%;
}
.el-container{
  --el-menu-border-color: #73767a
}
.el-aside{
  width: var(--el-aside-width,220px)
}
.el-footer{
  --el-footer-padding: 0 0px;
  --el-footer-height: 40px;
}
.el-main{
  padding: 10px;
}
.el-aside{
  width: 220px;
}
</style>
