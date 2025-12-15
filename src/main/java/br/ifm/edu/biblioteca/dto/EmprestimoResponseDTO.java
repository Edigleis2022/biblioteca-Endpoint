package br.ifm.edu.biblioteca.dto;

import lombok.*;
import java.time.LocalDate;

// DTO usado para retornar dados de Empréstimo
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoResponseDTO {
    private Long id;               // ID do empréstimo
    private LocalDate dataEmprestimo; // data do empréstimo
    private LocalDate dataDevolucao;  // data da devolução
    private Long livroId;          // ID do livro vinculado
    private Long usuarioId;        // ID do usuário vinculado
}
