package br.ifm.edu.biblioteca.dto;

import lombok.*;
import java.time.LocalDate;

// DTO usado para receber dados de cadastro/edição de Empréstimo
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoRequestDTO {
    private Long id;               // usado para edição
    private LocalDate dataEmprestimo; // data do empréstimo
    private LocalDate dataDevolucao;  // data da devolução
    private Long livroId;          // ID do livro vinculado
    private Long usuarioId;        // ID do usuário vinculado
}

