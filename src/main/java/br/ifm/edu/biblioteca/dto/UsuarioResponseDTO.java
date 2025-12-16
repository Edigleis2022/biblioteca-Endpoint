package br.ifm.edu.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de resposta, usado para retornar dados do usuário.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Long id;     // ID do usuário
    private String nome; // Nome do usuário
    private String email; // Email do usuário
}
