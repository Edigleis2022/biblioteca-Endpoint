package br.ifm.edu.biblioteca.dto;

import lombok.*;

// DTO usado para receber dados de cadastro/edição de Biblioteca
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BibliotecaRequestDTO {

    private Long id;       // usado para edição
    private String nome;   // nome da biblioteca
    private String endereco; // endereço da biblioteca
    
}


