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

import br.ifm.edu.biblioteca.dto.LivroRequestDTO;
import br.ifm.edu.biblioteca.dto.LivroResponseDTO;
import br.ifm.edu.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController // Indica que essa classe expõe endpoints REST
@RequestMapping("/livros") // Define o caminho base dos endpoints: /livros
public class LivroController {

    @Autowired // Injeta automaticamente o LivroService
    private LivroService service;

    @PutMapping // Endpoint para cadastrar ou editar Livro
    public ResponseEntity<?> cadastrarOuEditar(@RequestBody LivroRequestDTO dto) {
        try {
            // Chama o service para cadastrar ou editar o livro
            LivroResponseDTO response = service.cadastrarOuEditar(dto);

            // Retorna 200 OK com o JSON do livro atualizado
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Caso ocorra erro, retorna 400 BAD REQUEST com mensagem detalhada
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    @GetMapping // Endpoint para listar todos os livros cadastrados
    public ResponseEntity<List<LivroResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}") // Endpoint para buscar um livro específico pelo ID
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}


