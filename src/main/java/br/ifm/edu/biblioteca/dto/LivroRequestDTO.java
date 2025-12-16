package br.ifm.edu.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

/**
 * DTO usado para receber os dados de Livro
 * no cadastro ou edição (PUT).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroRequestDTO {

    /**
     * ID do livro
     * - null → cadastro
     * - preenchido → edição
     */
    private Long id;

    @NotBlank(message = "O título do livro é obrigatório")
    private String titulo;

    @NotNull(message = "O ano de publicação é obrigatório")
    private Integer anoPublicacao;

    /**
     * ID da biblioteca à qual o livro pertence
     */
    @NotNull(message = "A biblioteca é obrigatória")
    private Long bibliotecaId;

    /**
     * Lista de IDs dos autores do livro
     * (ManyToMany)
     */
    private List<Long> autoresIds;
}

