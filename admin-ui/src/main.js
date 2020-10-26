import '@fortawesome/fontawesome-free/css/all.css';
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import AdminService from './service/admin';
import RegistryService from './service/registry';

Vue.config.productionTip = false
Vue.prototype.$adminService = new AdminService();
Vue.prototype.$registryService = new RegistryService();

new Vue({
  router,
  vuetify,
  render: h => h(App),
  icons: {
    iconfont: 'fa',
  }
}).$mount('#app')
