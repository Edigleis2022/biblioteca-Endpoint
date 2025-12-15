package br.ifm.edu.biblioteca.dto;

import lombok.*;
import java.util.List;

// DTO usado para retornar dados de Livro
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroResponseDTO {
    private Long id;              // ID do livro
    private String titulo;        // título do livro
    private int anoPublicacao;    // ano de publicação
    private Long bibliotecaId;    // ID da biblioteca vinculada
    private List<Long> autoresIds; // IDs dos autores vinculados
}

