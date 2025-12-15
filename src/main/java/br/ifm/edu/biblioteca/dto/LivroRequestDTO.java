package br.ifm.edu.biblioteca.dto;

import lombok.*;
import java.util.List;
// DTO usado para receber dados de cadastro/edição de Livro
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroRequestDTO {
    private Long id;              // usado para edição
    private String titulo;        // título do livro
    private int anoPublicacao;    // ano de publicação
    private Long bibliotecaId;    // ID da biblioteca vinculada
    private List<Long> autoresIds; // IDs dos autores vinculados
}

