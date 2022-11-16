import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'
import router from './router/index.js'
import store from './store/index.js'
import '@/style/index.css'

createApp(App)
    .use(store)
    .use(ElementPlus)
    .use(router)
    .mount('#app')
