package br.ifm.edu.biblioteca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ifm.edu.biblioteca.dto.LivroRequestDTO;
import br.ifm.edu.biblioteca.dto.LivroResponseDTO;
import br.ifm.edu.biblioteca.model.Autor;
import br.ifm.edu.biblioteca.model.Biblioteca;
import br.ifm.edu.biblioteca.model.Livro;
import br.ifm.edu.biblioteca.repository.AutorRepository;
import br.ifm.edu.biblioteca.repository.BibliotecaRepository;
import br.ifm.edu.biblioteca.repository.LivroRepository;

@Service // Indica que essa classe contém regras de negócio
public class LivroService {

    @Autowired private LivroRepository repository;
    @Autowired private BibliotecaRepository bibliotecaRepository;
    @Autowired private AutorRepository autorRepository;

    /**
     * Método para cadastrar ou editar um Livro.
     * - Se o DTO tiver id == null → cadastro.
     * - Se tiver id → edição.
     */
    public LivroResponseDTO cadastrarOuEditar(LivroRequestDTO dto) {
        Livro livro;

        if (dto.getId() != null) {
            // Caso tenha ID, significa edição
            livro = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado para edição"));

            // Atualiza os dados básicos
            livro.setTitulo(dto.getTitulo());
            livro.setAnoPublicacao(dto.getAnoPublicacao());

            // Atualiza a biblioteca vinculada
            Biblioteca biblioteca = bibliotecaRepository.findById(dto.getBibliotecaId())
                    .orElseThrow(() -> new RuntimeException("Biblioteca não encontrada"));
            livro.setBiblioteca(biblioteca);

            // Atualiza os autores vinculados
            if (dto.getAutoresIds() != null && !dto.getAutoresIds().isEmpty()) {
                List<Autor> autores = autorRepository.findAllById(dto.getAutoresIds());
                livro.setAutores(autores);
            }
        } else {
            // Caso não tenha ID, significa cadastro
            Biblioteca biblioteca = bibliotecaRepository.findById(dto.getBibliotecaId())
                    .orElseThrow(() -> new RuntimeException("Biblioteca não encontrada"));

            livro = Livro.builder()
                    .titulo(dto.getTitulo())
                    .anoPublicacao(dto.getAnoPublicacao())
                    .biblioteca(biblioteca)
                    .build();

            // Vincula autores se informados
            if (dto.getAutoresIds() != null && !dto.getAutoresIds().isEmpty()) {
                List<Autor> autores = autorRepository.findAllById(dto.getAutoresIds());
                livro.setAutores(autores);
            }
        }

        // Salva no banco (serve tanto para novo quanto para edição)
        Livro salvo = repository.save(livro);

        // Retorna o DTO de resposta com os dados atualizados
        return LivroResponseDTO.builder()
                .id(salvo.getId())
                .titulo(salvo.getTitulo())
                .anoPublicacao(salvo.getAnoPublicacao())
                .bibliotecaId(salvo.getBiblioteca().getId())
                .autoresIds(salvo.getAutores() == null ? List.of() :
                        salvo.getAutores().stream().map(Autor::getId).toList())
                .build();
    }

    // Método para listar todos os livros cadastrados
    public List<LivroResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(l -> LivroResponseDTO.builder()
                        .id(l.getId())
                        .titulo(l.getTitulo())
                        .anoPublicacao(l.getAnoPublicacao())
                        .bibliotecaId(l.getBiblioteca().getId())
                        .autoresIds(l.getAutores() == null ? List.of() :
                                l.getAutores().stream().map(Autor::getId).toList())
                        .build())
                .toList();
    }

    // Método para buscar um livro específico pelo ID
    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return LivroResponseDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .anoPublicacao(livro.getAnoPublicacao())
                .bibliotecaId(livro.getBiblioteca().getId())
                .autoresIds(livro.getAutores() == null ? List.of() :
                        livro.getAutores().stream().map(Autor::getId).toList())
                .build();
    }
}




