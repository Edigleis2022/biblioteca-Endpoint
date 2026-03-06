📚 Sistema de Biblioteca – API RESTful




🔹 Sobre o Projeto

API RESTful para gerenciamento de Bibliotecas, Livros, Autores, Usuários e Empréstimos, com:

CRUD completo para todas as entidades

Relacionamentos corretos (OneToMany, ManyToMany, ManyToOne)

Validações de campos obrigatórios (@Valid, @NotBlank, @NotNull)

Swagger integrado para testes rápidos

Este projeto demonstra habilidades em Java, Spring Boot, JPA/Hibernate, DTOs, serviços, validação e arquitetura REST.
```
🔹 Tabela de Endpoints
Entidade	Método	Endpoint	Descrição
Biblioteca	GET	/bibliotecas	Listar todas
	GET	/bibliotecas/{id}	Buscar por ID
	PUT	/bibliotecas	Cadastrar/Editar
Autor	GET	/autores	Listar todos
	GET	/autores/{id}	Buscar por ID
	PUT	/autores	Cadastrar/Editar
Livro	GET	/livros	Listar todos
	GET	/livros/{id}	Buscar por ID
	PUT	/livros	Cadastrar/Editar
Usuário	GET	/usuarios	Listar todos
	GET	/usuarios/{id}	Buscar por ID
	PUT	/usuarios	Cadastrar/Editar
Empréstimo	GET	/emprestimos	Listar todos
	GET	/emprestimos/{id}	Buscar por ID
	PUT	/emprestimos	Cadastrar/Editar
🔹 GIFs de Demonstração
```
Biblioteca


Livro


Usuário


Empréstimo


⚠️ GIFs ilustrativos – substitua pelos seus testes reais no Swagger UI.

🔹 Exemplo de JSON para Testes

Biblioteca

{
  "nome": "Biblioteca Central",
  "endereco": "Rua Principal, 123"
}


Livro

{
  "titulo": "Aprendendo Spring Boot",
  "anoPublicacao": 2024,
  "bibliotecaId": 1,
  "autoresIds": [1, 2]
}


Usuário

{
  "nome": "Edigleis Pereira",
  "email": "edigleis@example.com"
}


Empréstimo

{
  "dataEmprestimo": "2025-12-16",
  "dataDevolucao": "2025-12-23",
  "livroId": 1,
  "usuarioId": 1
}

🔹 Tecnologias

Java 17

Spring Boot 3.x

Spring Data JPA / Hibernate

H2 Database

Swagger / OpenAPI

DTOs e Services para separação de camadas

Validação de dados (@Valid, @NotBlank, @NotNull)
```
🔹 Estrutura do Projeto
src/main/java/br/ifm/edu/biblioteca
│
├── controller          # Endpoints REST
├── service             # Regras de negócio
├── dto                 # Data Transfer Objects (requests/responses)
├── model               # Entidades JPA
└── repository          # Interfaces de persistência
```
🔹 Instalação e Testes

Clone o repositório:

git clone https://github.com/seu-usuario/biblioteca-api.git
cd biblioteca-api


Compile e execute:

mvn clean install
mvn spring-boot:run


Acesse Swagger UI:

http://localhost:8090/swagger-ui.html


Teste os endpoints usando os JSONs de exemplo acima.

🔹 Diferenciais do Projeto

Código modular, organizado e comentado

Validações robustas para evitar inconsistências

Relacionamentos JPA corretos

Testes de endpoints via Swagger

Projeto pronto para portfólio e demonstração a recrutadores

🔹 Contato / Desenvolvedor

Edigleis Pereira
📧 edigleisblade2023@gmail.com

💼 LinkedIn
 | GitHub

Portfólio profissional demonstrando habilidades em APIs RESTful com Spring Boot e desenvolvimento backend completo.
