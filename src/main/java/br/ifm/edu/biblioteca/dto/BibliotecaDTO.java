package br.ifm.edu.biblioteca.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BibliotecaDTO {
    private Long id;
    private String nome;
    private String endereco;
}


