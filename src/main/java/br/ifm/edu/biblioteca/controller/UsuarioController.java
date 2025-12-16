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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifm.edu.biblioteca.dto.UsuarioRequestDTO;
import br.ifm.edu.biblioteca.dto.UsuarioResponseDTO;
import br.ifm.edu.biblioteca.service.UsuarioService;
import jakarta.validation.Valid;

/**
 * Controller REST para gerenciamento de usuários
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    /**
     * Cadastrar ou editar usuário.
     * PUT /usuarios
     */
    @PutMapping
    public ResponseEntity<?> cadastrarOuEditar(@Valid @RequestBody UsuarioRequestDTO dto) {
        try {
            UsuarioResponseDTO resp = service.cadastrarOuEditar(dto);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    /**
     * Listar todos os usuários.
     * GET /usuarios
     */
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    /**
     * Buscar usuário por ID.
     * GET /usuarios/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            UsuarioResponseDTO resp = service.buscarPorId(id);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
    }
}