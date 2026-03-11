<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { TaskApi } from "../api/taskApi";
import TaskForm from "../components/TaskForm.vue";

const props = defineProps({
  mode: { type: String, required: true },
  id: { type: String, default: null }
});

const router = useRouter();
const model = ref({ name: "" });
const loading = ref(false);

onMounted(async () => {
  if (props.mode === "edit" && props.id) {
    loading.value = true;
    try {
      model.value = await TaskApi.get(props.id);
    } finally {
      loading.value = false;
    }
  }
});

async function onSubmit(payload) {
  if (props.mode === "create") {
    const created = await TaskApi.create({ name: payload.name });
    router.push(`/tasks/${created.id}`);
  } else {
    await TaskApi.update(props.id, { name: payload.name });
    router.push(`/tasks/${props.id}`);
  }
}
</script>

<template>
  <v-container>
    <h2 class="mb-4">{{ mode === "create" ? "Create Task" : "Edit Task" }}</h2>

    <v-progress-linear v-if="loading" indeterminate class="mb-4" />

    <TaskForm v-else :modelValue="model" @submit="onSubmit" />
  </v-container>
</template>