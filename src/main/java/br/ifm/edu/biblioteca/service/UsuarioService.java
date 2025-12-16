package br.ifm.edu.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifm.edu.biblioteca.dto.UsuarioDTO;
import br.ifm.edu.biblioteca.dto.UsuarioRequestDTO;
import br.ifm.edu.biblioteca.dto.UsuarioResponseDTO;
import br.ifm.edu.biblioteca.model.Usuario;
import br.ifm.edu.biblioteca.repository.UsuarioRepository;

@Service // Indica que essa classe contém regras de negócio
public class UsuarioService {

    @Autowired private UsuarioRepository repository;

    /**
     * Método para cadastrar ou editar um Usuário.
     * - Se o DTO tiver id == null → cadastro.
     * - Se tiver id → edição.
     */
    public UsuarioResponseDTO cadastrarOuEditar(UsuarioRequestDTO dto, Long id) {
        Usuario usuario;

        if (dto.getId() != null) {
            // Caso tenha ID, significa edição
            usuario = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado para edição"));
            usuario.setNome(dto.getNome());   // Atualiza nome
            usuario.setEmail(dto.getEmail()); // Atualiza email
        } else {
            // Caso não tenha ID, significa cadastro
            usuario = Usuario.builder()
                    .nome(dto.getNome())
                    .email(dto.getEmail())
                    .build();
        }

        // Salva no banco
        Usuario salvo = repository.save(usuario);

        // Retorna o DTO de resposta com os dados atualizados
        return UsuarioResponseDTO.builder()
                .id(salvo.getId())
                .nome(salvo.getNome())
                .email(salvo.getEmail())
                .build();
    }

    // Método para listar todos os usuários cadastrados
    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(u -> UsuarioResponseDTO.builder()
                        .id(u.getId())
                        .nome(u.getNome())
                        .email(u.getEmail())
                        .build())
                .toList();
    }

    // Método para buscar um usuário específico pelo ID
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
}



