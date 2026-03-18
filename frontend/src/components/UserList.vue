<script setup>
defineProps({
  users: Array
})

const emit = defineEmits(['edit', 'delete'])
</script>

<template>
  <ul class="user-list">
    <li v-for="user in users" :key="user.id" class="user-item">
      <span>{{ user.name }} - {{ user.email }}</span>

      <div class="actions">
        <button @click="emit('edit', user)">Editar</button>
        <button @click="emit('delete', user.id)">Excluir</button>
      </div>
    </li>
  </ul>
</template>

<style scoped>
.user-list {
  list-style: none;
  padding: 0;
  margin: 20px 0;
}

.user-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 12px;
  padding: 12px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background: #f9f9f9;
}

.actions {
  display: flex;
  gap: 8px;
}

.actions button {
  padding: 8px 12px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  background: gray;
  color: white;
}

.actions button:last-child {
  background: #d9534f;
}

.actions button:hover {
  opacity: 0.9;
}
</style>