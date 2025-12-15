package br.ifm.edu.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity // Indica que essa classe é uma entidade JPA
@Table(name = "biblioteca") // Nome da tabela no banco
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Biblioteca {

    @Id // Chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento
    private Long id;

    @Column(name = "nome", nullable = false) // Coluna "nome" obrigatória
    private String nome;

    @Column(name = "endereco", nullable = false) // Coluna "endereco" obrigatória
    private String endereco;

    @OneToMany(mappedBy = "biblioteca") // Relacionamento 1:N com Livro
    private List<Livro> livros;
}
