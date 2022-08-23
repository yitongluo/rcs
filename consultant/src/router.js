import { createWebHistory, createRouter } from "vue-router";

const routes =  [
  {
    path: "/",
    alias: "/cases",
    name: "cases",
    component: () => import("./components/CaseList")
  },
  {
    path: "/cases/:id",
    name: "case-details",
    component: () => import("./components/Case")
  },
  {
    path: "/add",
    name: "add",
    component: () => import("./components/AddCase")
  },
  {
    path: "/register",
    name: "register",
    component: () => import("./components/Register")
  },
  {
    path: "/login",
    name: "login",
    component: () => import("./components/Login")
  },
  {
    path: "/profile",
    name: "profile",
    component: () => import("./components/Profile")
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;