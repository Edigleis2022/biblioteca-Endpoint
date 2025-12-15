package br.ifm.edu.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "emprestimo") // Nome da tabela no banco
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_emprestimo", nullable = false) // Coluna "data_emprestimo"
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao") // Coluna "data_devolucao" (pode ser nula)
    private LocalDate dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "livro_id") // FK para Livro
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "usuario_id") // FK para Usuario
    private Usuario usuario;
}
