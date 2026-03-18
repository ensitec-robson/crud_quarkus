const API_URL = 'http://localhost:8080/api/users';

export async function getUsers({ name = '', page = 0, size = 5 } = {}) {
  const params = new URLSearchParams();

  if (name) {
    params.append('name', name);
  }

  params.append('page', page);
  params.append('size', size);

  const response = await fetch(`${API_URL}?${params.toString()}`);

  if (!response.ok) {
    throw new Error('Erro ao buscar usuários');
  }

  return response.json();
}

export async function getUserById(id) {
  const response = await fetch(`${API_URL}/${id}`);

  if (!response.ok) {
    throw new Error('Erro ao buscar usuário');
  }

  return response.json();
}

export async function createUser(user) {
  const response = await fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(user)
  });

  if (!response.ok) {
    const errorBody = await response.text();
    throw new Error(`Erro ao criar usuário: ${errorBody}`);
  }

  return response.json();
}

export async function updateUser(id, user) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(user)
  });

  if (!response.ok) {
    const errorBody = await response.text();
    throw new Error(`Erro ao atualizar usuário: ${errorBody}`);
  }

  return response.json();
}

export async function deleteUser(id) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'DELETE'
  });

  if (!response.ok) {
    const errorBody = await response.text();
    throw new Error(`Erro ao excluir usuário: ${errorBody}`);
  }
}