package br.ifm.edu.biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para requisições de cadastro ou edição de usuário.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {

    private Long id; // Opcional: usado apenas para edição

    @NotBlank(message = "O nome do usuário é obrigatório")
    private String nome; // Nome do usuário

    @NotBlank(message = "O email do usuário é obrigatório")
    @Email(message = "O email informado não é válido")
    private String email; // Email do usuário
}
