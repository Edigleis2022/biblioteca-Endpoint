package br.ifm.edu.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.ifm.edu.biblioteca.dto.LivroRequestDTO;
import br.ifm.edu.biblioteca.dto.LivroResponseDTO;
import br.ifm.edu.biblioteca.service.LivroService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

/**
 * Controller para gerenciamento de livros.
 */
@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    /**
     * Endpoint para cadastrar ou editar um livro.
     * - PUT /livros
     * - Se o JSON tiver id → edição
     * - Se não tiver id → cadastro
     */
    @PutMapping
public ResponseEntity<?> cadastrarOuEditar(@Valid @RequestBody LivroRequestDTO dto,
                                            @RequestParam(required = false) Long id) {
    try {
        LivroResponseDTO resp = service.cadastrarOuEditar(dto, id);
        return ResponseEntity.ok(resp);
    } catch (RuntimeException e) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}

    /**
     * Endpoint para listar todos os livros.
     * - GET /livros
     */
    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    /**
     * Endpoint para buscar um livro por ID.
     * - GET /livros/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        try {
            LivroResponseDTO resp = service.buscarPorId(id);
            return ResponseEntity.ok(resp);

        } catch (RuntimeException e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
    }
}


