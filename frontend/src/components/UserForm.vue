<script setup>
const props = defineProps({
  name: String,
  email: String,
  editingId: Number
})

const emit = defineEmits([
  'update:name',
  'update:email',
  'submit',
  'cancel'
])
</script>

<template>
  <form class="user-form" @submit.prevent="emit('submit')">
    <input
      :value="name"
      @input="emit('update:name', $event.target.value)"
      placeholder="Nome"
    />

    <input
      :value="email"
      @input="emit('update:email', $event.target.value)"
      placeholder="Email"
    />

    <button type="submit">
      {{ editingId ? 'Atualizar' : 'Cadastrar' }}
    </button>

    <button
      v-if="editingId"
      type="button"
      @click="emit('cancel')"
    >
      Cancelar
    </button>
  </form>
</template>

<style scoped>
.user-form {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-bottom: 20px;
}

.user-form input {
  flex: 1;
  min-width: 220px;
  padding: 10px 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 14px;
}

.user-form button {
  padding: 10px 14px;
  border: none;
  border-radius: 8px;
  background: gray;
  color: white;
  cursor: pointer;
  font-size: 14px;
}

.user-form button[type='button'] {
  background: #777;
}

.user-form button:hover {
  opacity: 0.9;
}
</style>