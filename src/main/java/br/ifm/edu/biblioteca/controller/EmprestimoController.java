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
import org.springframework.web.bind.annotation.RestController;
import br.ifm.edu.biblioteca.dto.EmprestimoRequestDTO;
import br.ifm.edu.biblioteca.dto.EmprestimoResponseDTO;
import br.ifm.edu.biblioteca.service.EmprestimoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

/**
 * Controller para gerenciamento de empréstimos.
 */
@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    /**
     * Endpoint para cadastrar ou editar um empréstimo.
     * - PUT /emprestimos
     * - Se o JSON tiver id → edição
     * - Se não tiver id → cadastro
     */
    @PutMapping
    public ResponseEntity<?> cadastrarOuEditar(
            @Valid @RequestBody EmprestimoRequestDTO dto) {

        try {
            EmprestimoResponseDTO resp = service.cadastrarOuEditar(dto);
            return ResponseEntity.ok(resp);

        } catch (RuntimeException e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    /**
     * Endpoint para listar todos os empréstimos.
     * - GET /emprestimos
     */
    @GetMapping
    public ResponseEntity<List<EmprestimoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    /**
     * Endpoint para buscar um empréstimo por ID.
     * - GET /emprestimos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        try {
            EmprestimoResponseDTO resp = service.buscarPorId(id);
            return ResponseEntity.ok(resp);

        } catch (RuntimeException e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
    }
}

