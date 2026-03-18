<script setup>
import { ref, onMounted } from 'vue'
import UserForm from './components/UserForm.vue'
import UserList from './components/UserList.vue'
import {
  getUsers,
  createUser,
  updateUser,
  deleteUser
} from './services/userService'

const users = ref([])

const name = ref('')
const email = ref('')
const editingId = ref(null)

const search = ref('')
const page = ref(0)
const size = ref(5)
const totalPages = ref(0)

const fetchUsers = async () => {
  const response = await getUsers({
    name: search.value,
    page: page.value,
    size: size.value
  })

  users.value = response.data
  totalPages.value = response.totalPages
}

const handleSubmit = async () => {
  const payload = {
    name: name.value,
    email: email.value
  }

  if (editingId.value) {
    await updateUser(editingId.value, payload)
  } else {
    await createUser(payload)
  }

  resetForm()
  await fetchUsers()
}

const handleEdit = (user) => {
  name.value = user.name
  email.value = user.email
  editingId.value = user.id
}

const handleDelete = async (id) => {
  await deleteUser(id)
  await fetchUsers()
}

const resetForm = () => {
  name.value = ''
  email.value = ''
  editingId.value = null
}

const handleSearch = async () => {
  page.value = 0
  await fetchUsers()
}

const previousPage = async () => {
  if (page.value > 0) {
    page.value--
    await fetchUsers()
  }
}

const nextPage = async () => {
  if (page.value < totalPages.value - 1) {
    page.value++
    await fetchUsers()
  }
}

onMounted(() => {
  fetchUsers()
})
</script>

<template>
  <div class="container">
    <h1>CRUD de Usuários Com Quarkus</h1>

    <UserForm
      :name="name"
      :email="email"
      :editingId="editingId"
      @update:name="name = $event"
      @update:email="email = $event"
      @submit="handleSubmit"
      @cancel="resetForm"
    />

    <hr />

    <div class="search-box">
      <input v-model="search" placeholder="Buscar por nome" />
      <button @click="handleSearch">Buscar</button>
    </div>

    <UserList
      :users="users"
      @edit="handleEdit"
      @delete="handleDelete"
    />

    <div class="pagination">
      <button @click="previousPage" :disabled="page === 0">
        Anterior
      </button>

      <span>Página {{ page + 1 }} de {{ totalPages || 1 }}</span>

      <button @click="nextPage" :disabled="page >= totalPages - 1">
        Próxima
      </button>
    </div>
  </div>
</template>

<style scoped>
.container {
  max-width: 900px;
  margin: 60px auto;
  padding: 30px;

  background: white;
  border-radius: 12px;

  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);

  font-family: Arial, sans-serif;
}

h1 {
  margin-bottom: 20px;
}

.search-box {
  display: flex;
  gap: 10px;
  margin: 20px 0;
}

.search-box input {
  flex: 1;
  padding: 10px 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

.search-box button,
.pagination button {
  padding: 10px 14px;
  border: none;
  border-radius: 8px;
  background: gray;
  color: white;
  cursor: pointer;
}

.search-box button:hover,
.pagination button:hover {
  opacity: 0.9;
}

.pagination {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-top: 20px;
}

.pagination button:disabled {
  background: #aaa;
  cursor: not-allowed;
}
</style>