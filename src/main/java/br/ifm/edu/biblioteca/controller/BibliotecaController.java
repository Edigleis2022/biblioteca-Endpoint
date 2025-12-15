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

import br.ifm.edu.biblioteca.dto.BibliotecaRequestDTO;
import br.ifm.edu.biblioteca.dto.BibliotecaResponseDTO;
import br.ifm.edu.biblioteca.service.BibliotecaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController // Indica que essa classe expõe endpoints REST
@RequestMapping("/bibliotecas") // Define o caminho base dos endpoints: /bibliotecas
public class BibliotecaController {

    @Autowired // Injeta automaticamente o BibliotecaService
    private BibliotecaService service;

    @PutMapping // Endpoint para cadastrar ou editar Biblioteca
    public ResponseEntity<?> cadastrarOuEditar(@RequestBody BibliotecaRequestDTO dto) {
        try {
            // Chama o service para cadastrar ou editar a biblioteca
            BibliotecaResponseDTO response = service.cadastrarOuEditar(dto);

            // Retorna 200 OK com o JSON da biblioteca atualizada
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Caso ocorra erro, retorna 400 BAD REQUEST com mensagem detalhada
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    @GetMapping // Endpoint para listar todas as bibliotecas cadastradas
    public ResponseEntity<List<BibliotecaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}") // Endpoint para buscar uma biblioteca específica pelo ID
    public ResponseEntity<BibliotecaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}





