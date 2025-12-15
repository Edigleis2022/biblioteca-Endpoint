package br.ifm.edu.biblioteca.controller;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import br.ifm.edu.biblioteca.dto.AutorRequestDTO;
import br.ifm.edu.biblioteca.dto.AutorResponseDTO;
import br.ifm.edu.biblioteca.service.AutorService;

/**
 * Controller responsável por expor os endpoints REST relacionados a autores.
 * 
 * Funcionalidades:
 * - Cadastrar ou editar autor (PUT /autores)
 * - Listar todos os autores (GET /autores)
 * - Buscar autor por ID (GET /autores/{id})
 */
@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService service;

    /**
     * Endpoint para cadastrar ou editar um autor.
     * - Se o DTO vier com id == null → cadastro
     * - Se vier com id preenchido → edição
     * 
     * @param dto AutorRequestDTO enviado pelo cliente
     * @return ResponseEntity contendo o AutorResponseDTO atualizado ou erro
     */
    @PutMapping
    public ResponseEntity<?> cadastrarOuEditar(@Valid @RequestBody AutorRequestDTO dto) {
        try {
            // Chama o service para cadastrar ou editar
            AutorResponseDTO response = service.cadastrarOuEditar(dto);

            // Retorna 200 OK com o JSON do autor atualizado
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Em caso de falha, retorna 400 BAD REQUEST com mensagem detalhada
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    /**
     * Endpoint para listar todos os autores cadastrados
     * 
     * @return ResponseEntity contendo lista de AutorResponseDTO
     */
    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    /**
     * Endpoint para buscar um autor pelo ID
     * 
     * @param id ID do autor a ser buscado
     * @return ResponseEntity com AutorResponseDTO ou mensagem de erro
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            AutorResponseDTO response = service.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Retorna 404 NOT FOUND caso o autor não seja encontrado
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
    }
}
