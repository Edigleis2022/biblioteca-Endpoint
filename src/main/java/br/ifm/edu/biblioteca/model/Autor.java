package br.ifm.edu.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "autor") // Nome da tabela no banco
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false) // Coluna "nome"
    private String nome;

    @ManyToMany(mappedBy = "autores") // Relacionamento N:N com Livro
    private List<Livro> livros;
}
