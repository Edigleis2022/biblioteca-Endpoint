-- Bibliotecas
INSERT INTO biblioteca (nome, endereco) VALUES ('Biblioteca Central', 'Rua Principal, 123');
INSERT INTO biblioteca (nome, endereco) VALUES ('Biblioteca Comunitária', 'Av. Brasil, 456');

-- Autores
INSERT INTO autor (nome) VALUES ('Robert C. Martin');
INSERT INTO autor (nome) VALUES ('Martin Fowler');

-- Usuários
INSERT INTO usuario (nome, email) VALUES ('João da Silva', 'joao@email.com');
INSERT INTO usuario (nome, email) VALUES ('Maria Oliveira', 'maria@email.com');

-- Livros
INSERT INTO livro (titulo, ano_publicacao, biblioteca_id) VALUES ('Clean Code', 2008, 1);
INSERT INTO livro (titulo, ano_publicacao, biblioteca_id) VALUES ('Refactoring', 1999, 1);

-- Relacionar livros e autores
INSERT INTO livro_autor (livro_id, autor_id) VALUES (1, 1); -- Clean Code -> Robert C. Martin
INSERT INTO livro_autor (livro_id, autor_id) VALUES (2, 2); -- Refactoring -> Martin Fowler

-- Empréstimos
INSERT INTO emprestimo (data_emprestimo, data_devolucao, livro_id, usuario_id)
VALUES ('2025-12-01', '2025-12-15', 1, 1);

INSERT INTO emprestimo (data_emprestimo, data_devolucao, livro_id, usuario_id)
VALUES ('2025-12-05', NULL, 2, 2);

