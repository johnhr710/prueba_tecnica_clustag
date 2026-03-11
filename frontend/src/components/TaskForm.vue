<script setup>
import { reactive, watchEffect } from "vue";

const props = defineProps({
  modelValue: { type: Object, required: true }
});

const emit = defineEmits(["submit"]);
const local = reactive({ name: "" });

watchEffect(() => {
  local.name = props.modelValue?.name || "";
});

function submit() {
  emit("submit", { name: local.name });
}
</script>

<template>
  <v-form @submit.prevent="submit">
    <v-text-field id="task-name"  name="taskName" v-model="local.name" label="Name" required />
    <v-btn type="submit" color="primary">Save</v-btn>
  </v-form>
</template>