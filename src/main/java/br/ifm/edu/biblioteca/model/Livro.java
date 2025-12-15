package br.ifm.edu.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "livro") // Nome da tabela no banco
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false) // Coluna "titulo"
    private String titulo;

    @Column(name = "ano_publicacao", nullable = false) // Coluna "ano_publicacao"
    private int anoPublicacao;

    @ManyToOne // Muitos livros pertencem a uma biblioteca
    @JoinColumn(name = "biblioteca_id") // FK para biblioteca
    private Biblioteca biblioteca;

    @ManyToMany
    @JoinTable(
        name = "livro_autor", // Nome da tabela de junção
        joinColumns = @JoinColumn(name = "livro_id"), // FK para Livro
        inverseJoinColumns = @JoinColumn(name = "autor_id") // FK para Autor
    )
    private List<Autor> autores;

    @OneToMany(mappedBy = "livro") // Relacionamento 1:N com Emprestimo
    private List<Emprestimo> emprestimos;
}
