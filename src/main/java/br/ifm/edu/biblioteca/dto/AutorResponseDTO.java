package br.ifm.edu.biblioteca.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO usado para retornar os dados do autor na resposta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorResponseDTO {
    private Long id;
    private String nome;
}


