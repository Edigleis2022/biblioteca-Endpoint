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

    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Método para cadastrar ou editar um Empréstimo.
     * - Se o DTO tiver id == null → cadastro
     * - Se tiver id → edição
     */
    public EmprestimoResponseDTO cadastrarOuEditar(EmprestimoRequestDTO dto) {

        Emprestimo emprestimo;

        if (dto.getId() != null) {
            // EDIÇÃO
            emprestimo = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado para edição"));

            emprestimo.setDataEmprestimo(dto.getDataEmprestimo());
            emprestimo.setDataDevolucao(dto.getDataDevolucao());

        } else {
            // CADASTRO
            emprestimo = new Emprestimo();
            emprestimo.setDataEmprestimo(dto.getDataEmprestimo());
            emprestimo.setDataDevolucao(dto.getDataDevolucao());

            // (Opcional) regra de negócio:
            // impedir empréstimo duplicado do mesmo livro
            /*
            if (repository.existsByLivroIdAndDataDevolucaoIsNull(dto.getLivroId())) {
                throw new RuntimeException("Este livro já está emprestado");
            }
            */
        }

        // Livro (obrigatório)
        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        emprestimo.setLivro(livro);

        // Usuário (obrigatório)
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        emprestimo.setUsuario(usuario);

        // Salva no banco
        Emprestimo salvo = repository.save(emprestimo);

        // Retorna DTO
        return toResponseDTO(salvo);
    }

    // Lista todos os empréstimos
    public List<EmprestimoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Busca empréstimo por ID
    public EmprestimoResponseDTO buscarPorId(Long id) {
        Emprestimo emprestimo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
        return toResponseDTO(emprestimo);
    }

    // Método auxiliar para padronizar o retorno
    private EmprestimoResponseDTO toResponseDTO(Emprestimo e) {
        return EmprestimoResponseDTO.builder()
                .id(e.getId())
                .dataEmprestimo(e.getDataEmprestimo())
                .dataDevolucao(e.getDataDevolucao())
                .livroId(e.getLivro().getId())
                .usuarioId(e.getUsuario().getId())
                .build();
    }
}




