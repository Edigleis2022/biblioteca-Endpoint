package br.ifm.edu.biblioteca.dto;

import lombok.*;

import java.util.List;

/**
 * DTO usado para retornar os dados do Livro
 * após cadastro, edição ou consulta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroResponseDTO {

    private Long id;

    private String titulo;

    private Integer anoPublicacao;

    /**
     * ID da biblioteca vinculada ao livro
     */
    private Long bibliotecaId;

    /**
     * Lista de IDs dos autores vinculados
     */
    private List<Long> autoresIds;
}

