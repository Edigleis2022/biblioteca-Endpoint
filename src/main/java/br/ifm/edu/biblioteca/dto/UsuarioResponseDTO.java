package br.ifm.edu.biblioteca.dto;

import lombok.*;

// DTO usado para retornar dados de Usuário
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {
    private Long id;     // ID do usuário
    private String nome; // nome do usuário
    private String email; // email do usuário
}
