package br.ifm.edu.biblioteca.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifm.edu.biblioteca.repository.AutorRepository;
import br.ifm.edu.biblioteca.model.Autor;
import br.ifm.edu.biblioteca.dto.AutorRequestDTO;
import br.ifm.edu.biblioteca.dto.AutorResponseDTO;


@Service // Indica que essa classe contém regras de negócio
public class AutorService {

    @Autowired // Injeta automaticamente o AutorRepository
    private AutorRepository repository;

    /**
     * Método para cadastrar ou editar um Autor.
     * - Se o DTO tiver id == null → cadastro.
     * - Se tiver id → edição.
     */
    public AutorResponseDTO cadastrarOuEditar(AutorRequestDTO dto) {
        Autor autor;

        if (dto.getId() != null) {
            // Caso tenha ID, significa edição
            autor = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado para edição"));
            autor.setNome(dto.getNome()); // Atualiza o nome
        } else {
            // Caso não tenha ID, significa cadastro
            autor = Autor.builder()
                    .nome(dto.getNome())
                    .build();
        }

        // Salva no banco (serve tanto para novo quanto para edição)
        Autor salvo = repository.save(autor);

        // Retorna o DTO de resposta com os dados atualizados
        return AutorResponseDTO.builder()
                .id(salvo.getId())
                .nome(salvo.getNome())
                .build();
    }

    // Método para listar todos os autores cadastrados
    public List<AutorResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(a -> AutorResponseDTO.builder()
                        .id(a.getId())
                        .nome(a.getNome())
                        .build())
                .toList();
    }

    // Método para buscar um autor específico pelo ID
    public AutorResponseDTO buscarPorId(Long id) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        return AutorResponseDTO.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .build();
    }
}




