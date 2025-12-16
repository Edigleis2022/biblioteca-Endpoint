package br.ifm.edu.biblioteca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ifm.edu.biblioteca.dto.LivroRequestDTO;
import br.ifm.edu.biblioteca.dto.LivroResponseDTO;
import br.ifm.edu.biblioteca.dto.LivroResponseDTO.LivroResponseDTOBuilder;
import br.ifm.edu.biblioteca.model.Autor;
import br.ifm.edu.biblioteca.model.Biblioteca;
import br.ifm.edu.biblioteca.model.Livro;
import br.ifm.edu.biblioteca.repository.AutorRepository;
import br.ifm.edu.biblioteca.repository.BibliotecaRepository;
import br.ifm.edu.biblioteca.repository.LivroRepository;

@Service // Indica que essa classe contém regras de negócio
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private AutorRepository autorRepository;

    /**
     * Método para cadastrar ou editar um Livro.
     * - Se o DTO tiver id == null → cadastro
     * - Se tiver id → edição
     */
    public LivroResponseDTO cadastrarOuEditar(LivroRequestDTO dto) {
        Livro livro;

        if (dto.getId() != null) {
            // EDIÇÃO
            livro = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado para edição"));

            livro.setTitulo(dto.getTitulo());
            livro.setAnoPublicacao(dto.getAnoPublicacao());

        } else {
            // CADASTRO
            livro = new Livro();
            livro.setTitulo(dto.getTitulo());
            livro.setAnoPublicacao(dto.getAnoPublicacao());
        }

        // Biblioteca (obrigatória)
        Biblioteca biblioteca = bibliotecaRepository.findById(dto.getBibliotecaId())
                .orElseThrow(() -> new RuntimeException("Biblioteca não encontrada"));
        livro.setBiblioteca(biblioteca);

        // Autores (opcional)
        if (dto.getAutoresIds() != null && !dto.getAutoresIds().isEmpty()) {
            List<Autor> autores = autorRepository.findAllById(dto.getAutoresIds());
            livro.setAutores(autores);
        }

        // Salva no banco
        Livro salvo = repository.save(livro);

        // Retorno DTO
        return LivroResponseDTO.builder()
                .id(salvo.getId())
                .titulo(salvo.getTitulo())
                .anoPublicacao(salvo.getAnoPublicacao())
                .bibliotecaId(salvo.getBiblioteca().getId())
                .autoresIds(
                        salvo.getAutores() == null
                                ? List.of()
                                : salvo.getAutores().stream()
                                        .map(Autor::getId)
                                        .toList()
                )
                .build();
    }

    // Lista todos os livros
    public List<LivroResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Busca livro por ID
    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return toResponseDTO(livro);
    }

    // Método auxiliar (boa prática)
    private LivroResponseDTO toResponseDTO(Livro livro) {
        return LivroResponseDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .anoPublicacao(livro.getAnoPublicacao())
                .bibliotecaId(livro.getBiblioteca().getId())
                .autoresIds(
                        livro.getAutores() == null
                                ? List.of()
                                : livro.getAutores().stream()
                                        .map(Autor::getId)
                                        .toList()
                )
                .build();
    }
}




