import '@fortawesome/fontawesome-free/css/all.css';
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import AdminService from './service/admin';
import RegistryService from './service/registry';
import AsyncComputed from 'vue-async-computed'

Vue.config.productionTip = false
Vue.prototype.$adminService = new AdminService();
Vue.prototype.$registryService = new RegistryService();

Vue.use(AsyncComputed)

new Vue({
  router,
  vuetify,
  render: h => h(App),
  icons: {
    iconfont: 'fa',
  }
}).$mount('#app')
