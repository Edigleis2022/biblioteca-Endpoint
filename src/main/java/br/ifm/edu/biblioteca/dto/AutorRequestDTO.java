package br.ifm.edu.biblioteca.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO usado para enviar dados do autor na requisição (cadastrar ou editar)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorRequestDTO {
    private Long id; // Opcional: se enviado → edição, se null → cadastro

    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;
}

