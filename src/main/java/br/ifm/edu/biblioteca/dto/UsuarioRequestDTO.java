package br.ifm.edu.biblioteca.dto;

import lombok.*;

// DTO usado para receber dados de cadastro/edição de Usuário
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {

    private Long id;     // usado para edição
    private String nome; // nome do usuário
    private String email; // email do usuário
    
}

