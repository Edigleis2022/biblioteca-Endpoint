package br.ifm.edu.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "usuario") // Nome da tabela no banco
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false) // Coluna "nome"
    private String nome;

    @Column(name = "email", nullable = false, unique = true) // Coluna "email" única
    private String email;

    @OneToMany(mappedBy = "usuario") // Relacionamento 1:N com Emprestimo
    private List<Emprestimo> emprestimos;
}
