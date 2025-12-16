package br.ifm.edu.biblioteca.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

/**
 * DTO usado para receber os dados de um empréstimo
 * no cadastro ou edição (PUT).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoRequestDTO {

    /**
     * ID do empréstimo
     * - null → cadastro
     * - preenchido → edição
     */
    private Long id;

    /**
     * Data do empréstimo.
     * Deve ser a data atual ou futura.
     */
    @NotNull(message = "A data de empréstimo é obrigatória")
    private LocalDate dataEmprestimo;

    /**
     * Data de devolução.
     * Não pode ser uma data no passado.
     */
    @NotNull(message = "A data de devolução é obrigatória")
    @FutureOrPresent(message = "A data de devolução deve ser hoje ou no futuro")
    private LocalDate dataDevolucao;

    /**
     * ID do livro vinculado ao empréstimo.
     * Deve ser informado para associar ao livro.
     */
    @NotNull(message = "O ID do livro é obrigatório")
    private Long livroId;

    /**
     * ID do usuário que está realizando o empréstimo.
     * Deve ser informado para associar ao usuário.
     */
    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;
}

