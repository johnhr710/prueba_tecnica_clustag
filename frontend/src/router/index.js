import { createRouter, createWebHistory } from "vue-router";
import TaskList from "../views/TaskList.vue";
import TaskDetails from "../views/TaskDetails.vue";
import TaskFormView from "../views/TaskFormView.vue";

export default createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", redirect: "/tasks" },
    { path: "/tasks", component: TaskList },
    { path: "/tasks/new", component: TaskFormView, props: { mode: "create" } },
    { path: "/tasks/:id", component: TaskDetails, props: true },
    { path: "/tasks/:id/edit", component: TaskFormView, props: route => ({ mode: "edit", id: route.params.id }) }
  ]
});