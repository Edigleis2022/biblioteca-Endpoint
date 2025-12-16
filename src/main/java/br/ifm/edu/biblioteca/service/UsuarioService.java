package br.ifm.edu.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ifm.edu.biblioteca.dto.UsuarioRequestDTO;
import br.ifm.edu.biblioteca.dto.UsuarioResponseDTO;
import br.ifm.edu.biblioteca.model.Usuario;
import br.ifm.edu.biblioteca.repository.UsuarioRepository;

/**
 * Service que contém a lógica de negócio para a entidade Usuario.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository; // Acesso ao banco de dados

    /**
     * Método para cadastrar ou editar um usuário.
     * - Se dto.id == null → cria novo usuário
     * - Se dto.id != null → edita usuário existente
     *
     * @param dto Dados do usuário
     * @return UsuarioResponseDTO com os dados salvos/atualizados
     */
    public UsuarioResponseDTO cadastrarOuEditar(UsuarioRequestDTO dto) {
        Usuario u;

        if (dto.getId() != null) {
            // Edição
            u = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado para edição"));

            u.setNome(dto.getNome());
            u.setEmail(dto.getEmail());
        } else {
            // Cadastro
            u = Usuario.builder()
                    .nome(dto.getNome())
                    .email(dto.getEmail())
                    .build();
        }

        Usuario salvo = repository.save(u); // Salva no banco

        // Retorna DTO de resposta
        return UsuarioResponseDTO.builder()
                .id(salvo.getId())
                .nome(salvo.getNome())
                .email(salvo.getEmail())
                .build();
    }

    /**
     * Lista todos os usuários cadastrados
     *
     * @return Lista de UsuarioResponseDTO
     */
    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(u -> UsuarioResponseDTO.builder()
                        .id(u.getId())
                        .nome(u.getNome())
                        .email(u.getEmail())
                        .build())
                .toList();
    }

    /**
     * Busca um usuário pelo ID
     *
     * @param id ID do usuário
     * @return UsuarioResponseDTO com os dados encontrados
     */
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario u = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return UsuarioResponseDTO.builder()
                .id(u.getId())
                .nome(u.getNome())
                .email(u.getEmail())
                .build();
    }
}



