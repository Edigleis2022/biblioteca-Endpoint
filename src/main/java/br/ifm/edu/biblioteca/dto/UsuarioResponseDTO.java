package br.ifm.edu.biblioteca.dto;

import lombok.*;

/**
 * DTO para retornar os dados do usuário
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Long id;      // ID do usuário
    private String nome;  // Nome do usuário
    private String email; // Email do usuário
}
