package br.ifm.edu.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifm.edu.biblioteca.dto.EmprestimoDTO;
import br.ifm.edu.biblioteca.dto.EmprestimoRequestDTO;
import br.ifm.edu.biblioteca.dto.EmprestimoResponseDTO;
import br.ifm.edu.biblioteca.model.Emprestimo;
import br.ifm.edu.biblioteca.model.Livro;
import br.ifm.edu.biblioteca.model.Usuario;
import br.ifm.edu.biblioteca.repository.EmprestimoRepository;
import br.ifm.edu.biblioteca.repository.LivroRepository;
import br.ifm.edu.biblioteca.repository.UsuarioRepository;

@Service // Indica que essa classe contém regras de negócio
public class EmprestimoService {

    @Autowired private EmprestimoRepository repository;
    @Autowired private LivroRepository livroRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    /**
     * Método para cadastrar ou editar um Empréstimo.
     * - Se o DTO tiver id == null → cadastro.
     * - Se tiver id → edição.
     */
    public EmprestimoResponseDTO cadastrarOuEditar(EmprestimoRequestDTO dto) {
        Emprestimo emprestimo;

        if (dto.getId() != null) {
            // Caso tenha ID, significa edição
            emprestimo = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado para edição"));

            // Atualiza datas
            emprestimo.setDataEmprestimo(dto.getDataEmprestimo());
            emprestimo.setDataDevolucao(dto.getDataDevolucao());

            // Atualiza livro vinculado
            Livro livro = livroRepository.findById(dto.getLivroId())
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
            emprestimo.setLivro(livro);

            // Atualiza usuário vinculado
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            emprestimo.setUsuario(usuario);

        } else {
            // Caso não tenha ID, significa cadastro
            Livro livro = livroRepository.findById(dto.getLivroId())
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            emprestimo = Emprestimo.builder()
                    .dataEmprestimo(dto.getDataEmprestimo())
                    .dataDevolucao(dto.getDataDevolucao())
                    .livro(livro)
                    .usuario(usuario)
                    .build();
        }

        // Salva no banco
        Emprestimo salvo = repository.save(emprestimo);

        // Retorna o DTO de resposta com os dados atualizados
        return EmprestimoResponseDTO.builder()
                .id(salvo.getId())
                .dataEmprestimo(salvo.getDataEmprestimo())
                .dataDevolucao(salvo.getDataDevolucao())
                .livroId(salvo.getLivro().getId())
                .usuarioId(salvo.getUsuario().getId())
                .build();
    }

    // Método para listar todos os empréstimos cadastrados
    public List<EmprestimoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(e -> EmprestimoResponseDTO.builder()
                        .id(e.getId())
                        .dataEmprestimo(e.getDataEmprestimo())
                        .dataDevolucao(e.getDataDevolucao())
                        .livroId(e.getLivro().getId())
                        .usuarioId(e.getUsuario().getId())
                        .build())
                .toList();
    }

    // Método para buscar um empréstimo específico pelo ID
    public EmprestimoResponseDTO buscarPorId(Long id) {
        Emprestimo emprestimo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
        return EmprestimoResponseDTO.builder()
                .id(emprestimo.getId())
                .dataEmprestimo(emprestimo.getDataEmprestimo())
                .dataDevolucao(emprestimo.getDataDevolucao())
                .livroId(emprestimo.getLivro().getId())
                .usuarioId(emprestimo.getUsuario().getId())
                .build();
    }
}




