package br.ifm.edu.biblioteca.dto;

import lombok.*;
import java.time.LocalDate;

/**
 * DTO usado para retornar os dados de um empréstimo
 * após cadastro, edição ou consulta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoResponseDTO {

    /**
     * ID do empréstimo
     */
    private Long id;

    /**
     * Data do empréstimo
     */
    private LocalDate dataEmprestimo;

    /**
     * Data da devolução
     */
    private LocalDate dataDevolucao;

    /**
     * ID do livro vinculado ao empréstimo
     */
    private Long livroId;

    /**
     * ID do usuário que realizou o empréstimo
     */
    private Long usuarioId;
}
