package br.ifm.edu.biblioteca.dto;

import lombok.*;


// DTO usado para retornar dados de Biblioteca
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BibliotecaResponseDTO {

    private Long id;       // ID da biblioteca
    private String nome;   // nome da biblioteca
    private String endereco; // endereço da biblioteca
    
}

    
