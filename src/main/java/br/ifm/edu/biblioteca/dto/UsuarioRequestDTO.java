package br.ifm.edu.biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * DTO para cadastro ou edição de usuário
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {

    /**
     * ID do usuário
     * - null → cadastro
     * - preenchido → edição
     */
    private Long id;

    /**
     * Nome do usuário
     */
    @NotBlank(message = "O nome do usuário é obrigatório")
    private String nome;

    /**
     * Email do usuário
     */
    @NotBlank(message = "O email do usuário é obrigatório")
    @Email(message = "O email informado não é válido")
    private String email;
}
