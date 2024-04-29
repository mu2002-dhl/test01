<template>
  <el-row class="tac">
    <el-col :span="12">
      <div id="menu" style="width: 220px;">
        <el-menu
          style="height: 100%;"
          active-text-color="#ffd04b"
          background-color="#545c64"
          class="el-menu-vertical-demo"
          text-color="#fff"
        >
          <template v-for="item in menuList" :key="item.id.toString()">
            <!-- 没有子集 -->
            <template v-if="item.children.length === 0" >
              <el-menu-item :index="item.id.toString()" @click="goRoute(item)">
                <template #title>
                  <span v-html="item.icon"></span>
                  <span>{{ item.name }}</span>
                </template>
              </el-menu-item>
            </template>
            <!-- 有子集 -->
            <el-sub-menu v-else-if="item.children.length > 0" :index="item.id.toString()">
              <template #title>
                <span v-html="item.icon"></span>
                <span>{{ item.name }}</span>
              </template>
              <template v-for="childItem in item.children" :key="childItem.id.toString()">
                <el-menu-item v-if="childItem.children.length === 0" @click="goRoute(childItem)" :index="childItem.id.toString()">
                  <template #title>
                    <span v-html="childItem.icon"></span>
                    <span>{{ childItem.name }}</span>
                  </template>
                </el-menu-item>
                <el-sub-menu v-else-if="childItem.children.length > 0"  :index="childItem.id.toString()">
                  <template #title>
                    <span v-html="childItem.icon"></span>
                    <span>{{ childItem.name }}</span>
                  </template>
                  <template v-for="subChildItem in childItem.children" :key="subChildItem.id.toString()">
                    <el-menu-item :index="subChildItem.id.toString()" @click="goRoute(subChildItem)">
                      <template #title>
                        <span v-html="subChildItem.icon"></span>
                        <span>{{ subChildItem.name }}</span>
                      </template>
                    </el-menu-item>
                  </template>
                </el-sub-menu>
              </template>
            </el-sub-menu>
          </template>
        </el-menu>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { onBeforeUnmount, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import store from '@/store'

export default {
  name: 'AppAside',
  setup () {
    const menuList = ref(null)
    const route = useRouter()
    const handleResize = () => {
      // el-menu自适应屏幕高度
      document.getElementById('menu').style.height = (window.innerHeight - 60) + 'px'
    }

    onMounted(async () => {
      handleResize()
      // 监听屏幕尺寸变化
      window.addEventListener('resize', handleResize)
    })

    onBeforeUnmount(() => {
      // 移除监听屏幕尺寸变化
      window.removeEventListener('resize', handleResize)
    })

    onMounted(() => {
      const { profile } = store.state.user
      if (profile.menuList) {
        menuList.value = profile.menuList
      }
    })

    const goRoute = (currentItem) => {
      route.push(currentItem.url)
    }

    return { menuList, goRoute }
  }
}
</script>
