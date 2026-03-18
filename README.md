# Boas-vindas ao repositório do projeto de teste CRUD de Usuários utilizando Quarkus

<img width="1039" height="699" alt="image" src="https://github.com/user-attachments/assets/1b85613f-bb09-425c-b1fb-f533fcf8c414" />


Seja muito bem-vindo ao repositório do Projeto de teste CRUD de Usuários utilizando Quarkus, Aqui você encontrará informações sobre o que foi desenvolvido neste teste, bem como os principais aprendizados adquiridos durante o processo. Para explorar o conteúdo, basta clicar nas seções abaixo. Boa leitura!

<br/>

<details>
  <summary><strong>🧠 Habilidades desenvolvidas neste teste</strong></summary><br />

Neste projeto, fui capaz de:

* Criar uma API REST com **Quarkus**
* Trabalhar com arquitetura em camadas (**Resource, Service, Repository**)
* Utilizar **DTOs** para segurança e organização da API
* Implementar **mapper manual** para conversão de dados
* Conectar aplicação com banco **PostgreSQL**
* Utilizar **Docker** para subir banco de dados
* Trabalhar com **Vue.js (Composition API)**
* Implementar **paginação no back-end**
* Implementar **filtro por nome (query params)**
* Consumir API no front-end com `fetch`
* Organizar o front-end em **components + services**
* Implementar **logs de requisição no backend**
* Tratar erros de API

</details>

<br/>

<details>
  <summary><strong>⚙️ Como rodar o projeto</strong></summary><br />

## 📦 Pré-requisitos

Antes de começar, você precisa ter instalado:

* [Java 21+](https://adoptium.net/)
* [Maven](https://maven.apache.org/)
* [Node.js](https://nodejs.org/)
* [Docker](https://www.docker.com/)
* [Git](https://git-scm.com/)

---

## 🐳 Subindo o banco com Docker

Na raiz do projeto, execute:

```bash
docker compose up -d
```

Isso irá subir um container PostgreSQL.

---

## 🧱 Configuração do banco

O banco já será criado automaticamente pelo container com:

* Database: `crud_db`
* User: `postgres`
* Password: `postgres123`
* Porta: `5433`

---

## 🔧 Configuração do back-end (Quarkus)

Entre na pasta:

```bash
cd backend
```

Execute o projeto:

```bash
.\mvnw.cmd quarkus:dev
```

O servidor estará disponível em:

```
http://localhost:8080
```

O Quarkus irá criar automaticamente a tabela `users`.

---

## 🌐 Rodando o front-end (Vue)

Entre na pasta:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Inicie o projeto:

```bash
npm run dev
```

Acesse:

```
http://localhost:5173
```

---

</details>

<br/>

<details>
  <summary><strong>📡 Endpoints da API</strong></summary><br />

### 🔍 Listar usuários (com paginação e filtro)

```http
GET /api/users?name=rob&page=0&size=5
```

---

### ➕ Criar usuário

```http
POST /api/users
```

Body:

```json
{
  "name": "Robson Aires",
  "email": "robson@email.com"
}
```

---

### ✏️ Atualizar usuário

```http
PUT /api/users/{id}
```

---

### ❌ Deletar usuário

```http
DELETE /api/users/{id}
```

---

### 🔎 Buscar por ID

```http
GET /api/users/{id}
```

</details>

<br/>

<details>
  <summary><strong>📁 Estrutura do projeto</strong></summary><br />

```bash
frontend/
  src/
    components/
      UserForm.vue
      UserList.vue
    services/
      userService.js
    App.vue

backend/
  src/main/java/com/robson/
    resource/
    services/
    repository/
    dto/
    mapper/
    entity/
    filter/

docker-compose.yml
```

</details>
