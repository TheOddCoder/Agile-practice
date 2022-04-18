import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// import './styles/element-variables.scss'
import 'font-awesome/css/font-awesome.min.css'
import './utils/filter_utils.js'
import VueCropper from 'vue-cropper'
import axios from 'axios';
import VueResource from 'vue-resource';

Vue.use(VueCropper);
Vue.prototype.$axios = axios;
Vue.use(VueResource);
Vue.use(ElementUI);
Vue.config.productionTip = false;
window.bus = new Vue();
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {App}
})
