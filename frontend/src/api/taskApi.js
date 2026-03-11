import axios from "axios";

const api = axios.create({
  baseURL: "/api"
});

export const TaskApi = {
  list: () => api.get("/tasks").then(r => r.data),
  get: id => api.get(`/tasks/${id}`).then(r => r.data),
  create: payload => api.post("/tasks", payload).then(r => r.data),
  update: (id, payload) => api.put(`/tasks/${id}`, payload).then(r => r.data),
  remove: id => api.delete(`/tasks/${id}`)
};