<template>
  <div class="not-found">
    <h1>404 - Page Not Found</h1>
    <p>Oops! The page you are looking for does not exist.</p>
    <el-link type="danger" href="/">点击跳转首页</el-link>
  </div>
</template>
<script>
export default {
  name: 'VueNotFoundPage'
}
</script>

<style>
.not-found {
  text-align: center;
  margin-top: 50px;
}
</style>
