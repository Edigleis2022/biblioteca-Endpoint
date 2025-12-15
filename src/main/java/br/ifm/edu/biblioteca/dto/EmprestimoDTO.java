package br.ifm.edu.biblioteca.dto;


import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoDTO {
    private Long id;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Long livroId;
    private Long usuarioId;
}


