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
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;