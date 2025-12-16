package br.ifm.edu.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifm.edu.biblioteca.dto.BibliotecaDTO;
import br.ifm.edu.biblioteca.dto.BibliotecaRequestDTO;
import br.ifm.edu.biblioteca.dto.BibliotecaResponseDTO;
import br.ifm.edu.biblioteca.model.Biblioteca;
import br.ifm.edu.biblioteca.repository.BibliotecaRepository;

@Service // Indica que essa classe contém regras de negócio
public class BibliotecaService {

    @Autowired private BibliotecaRepository repository;

    /**
     * Método para cadastrar ou editar uma Biblioteca.
     * - Se o DTO tiver id == null → cadastro.
     * - Se tiver id → edição.
     */
    public BibliotecaResponseDTO cadastrarOuEditar(BibliotecaRequestDTO dto, Long id) {
        Biblioteca biblioteca;

        if (dto.getId() != null) {
            // Caso tenha ID, significa edição
            biblioteca = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Biblioteca não encontrada para edição"));
            biblioteca.setNome(dto.getNome());       // Atualiza nome
            biblioteca.setEndereco(dto.getEndereco()); // Atualiza endereço
        } else {
            // Caso não tenha ID, significa cadastro
            biblioteca = Biblioteca.builder()
                    .nome(dto.getNome())
                    .endereco(dto.getEndereco())
                    .build();
        }

        // Salva no banco
        Biblioteca salvo = repository.save(biblioteca);

        // Retorna o DTO de resposta com os dados atualizados
        return BibliotecaResponseDTO.builder()
                .id(salvo.getId())
                .nome(salvo.getNome())
                .endereco(salvo.getEndereco())
                .build();
    }

    // Método para listar todas as bibliotecas cadastradas
    public List<BibliotecaResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(b -> BibliotecaResponseDTO.builder()
                        .id(b.getId())
                        .nome(b.getNome())
                        .endereco(b.getEndereco())
                        .build())
                .toList();
    }

    // Método para buscar uma biblioteca específica pelo ID
    public BibliotecaResponseDTO buscarPorId(Long id) {
        Biblioteca biblioteca = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Biblioteca não encontrada"));
        return BibliotecaResponseDTO.builder()
                .id(biblioteca.getId())
                .nome(biblioteca.getNome())
                .endereco(biblioteca.getEndereco())
                .build();
    }
}




