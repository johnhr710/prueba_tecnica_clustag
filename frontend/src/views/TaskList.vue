<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { TaskApi } from "../api/taskApi";

const router = useRouter();
const tasks = ref([]);
const loading = ref(false);

async function load() {
  loading.value = true;
  try {
    tasks.value = await TaskApi.list();
  } finally {
    loading.value = false;
  }
}

async function onDelete(t) {
  const ok = confirm(`Delete task "${t.name}"?`);
  if (!ok) return;
  await TaskApi.remove(t.id);
  await load();
}

onMounted(load);
</script>

<template>
  <v-container>
    <v-row class="mb-3">
      <v-col>
        <v-btn color="primary" @click="router.push('/tasks/new')">New Task</v-btn>
      </v-col>
      <v-col class="text-right">
        <v-btn variant="tonal" @click="load">Refresh</v-btn>
      </v-col>
    </v-row>

    <v-data-table
      :items="tasks"
      :loading="loading"
      item-key="id"
      :headers="[
        { title: 'ID', key: 'id' },
        { title: 'Name', key: 'name' },
        { title: 'State', key: 'state' },
        { title: 'Actions', key: 'actions', sortable: false }
      ]"
    >
      <template #item.name="{ item }">
        <a href="#" @click.prevent="router.push(`/tasks/${item.id}`)">{{ item.name }}</a>
      </template>

      <template #item.state="{ item }">
        <v-chip>{{ item.state }}</v-chip>
      </template>

      <template #item.actions="{ item }">
        <v-btn size="small" @click="router.push(`/tasks/${item.id}/edit`)">Edit</v-btn>
        <v-btn size="small" color="error" @click="onDelete(item)">Delete</v-btn>
      </template>
    </v-data-table>
  </v-container>
</template>