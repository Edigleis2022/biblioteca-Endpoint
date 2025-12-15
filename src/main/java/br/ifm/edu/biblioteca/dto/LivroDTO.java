package br.ifm.edu.biblioteca.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroDTO {
    private Long id;
    private String titulo;
    private int anoPublicacao;
    private Long bibliotecaId;
}


