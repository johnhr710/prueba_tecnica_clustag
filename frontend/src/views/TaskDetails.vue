<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { TaskApi } from "../api/taskApi";

const props = defineProps({ id: String });
const router = useRouter();

const task = ref(null);
const loading = ref(false);

onMounted(async () => {
  loading.value = true;
  try {
    task.value = await TaskApi.get(props.id);
  } finally {
    loading.value = false;
  }
});
</script>

<template>
  <v-container>
    <v-progress-linear v-if="loading" indeterminate class="mb-4" />

    <div v-else-if="task">
      <h2 class="mb-2">{{ task.name }}</h2>
      <div class="mb-2"><strong>ID:</strong> {{ task.id }}</div>
      <div class="mb-4">
        <strong>State:</strong>
        <v-chip class="ml-2">{{ task.state }}</v-chip>
      </div>

      <v-btn class="mr-2" @click="router.push(`/tasks/${task.id}/edit`)">Edit</v-btn>
      <v-btn variant="tonal" @click="router.push('/tasks')">Back</v-btn>
    </div>
  </v-container>
</template>