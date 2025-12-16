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

import br.ifm.edu.biblioteca.dto.BibliotecaRequestDTO;
import br.ifm.edu.biblioteca.dto.BibliotecaResponseDTO;
import br.ifm.edu.biblioteca.service.BibliotecaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bibliotecas")
public class BibliotecaController {

    @Autowired
    private BibliotecaService service;

    // Cadastrar ou editar
    @PutMapping
    public ResponseEntity<?> cadastrarOuEditar(@Valid @RequestBody BibliotecaRequestDTO dto,
                                                @RequestParam(required = false) Long id) {
        try {
            BibliotecaResponseDTO resp = service.cadastrarOuEditar(dto, id);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<BibliotecaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            BibliotecaResponseDTO resp = service.buscarPorId(id);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
    }
}





