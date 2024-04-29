import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import CKEditor from '@ckeditor/ckeditor5-vue'

const app = createApp(App)
app.use(store).use(router).use(ElementPlus).use(CKEditor).mount('#app')

// 图标全局引用
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
