import Vue from 'vue'
import App from './App.vue'
import * as VueGoogleMaps from "vue2-google-maps"
import router from './router'
import SmartTable from 'vuejs-smart-table'

Vue.config.productionTip = false

Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyDPVGep1SvpGmUT4CitUNw2K-dTvvdzM3k",
    libraries: "places" // necessary for places input
  }
});

Vue.use(SmartTable)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
