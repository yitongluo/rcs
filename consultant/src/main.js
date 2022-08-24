import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";  

router.beforeEach((to, from, next)=> {
  let loggedIn = store.state.auth.status.loggedIn;
  let whiteList = [ '/login', '/register' ];
  let inWhiteList = whiteList.indexOf(to.path) !== -1;

  if (loggedIn) {
    if (inWhiteList) {
      next('/')
    }
    else {
      next();
    }
  }
  else {
    if (inWhiteList) {
      next()
    }
    else {
      next(`/login`)
    }
  }
});

createApp(App)
  .use(router)
  .use(store)
  .mount("#app");