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

import br.ifm.edu.biblioteca.dto.UsuarioRequestDTO;
import br.ifm.edu.biblioteca.dto.UsuarioResponseDTO;
import br.ifm.edu.biblioteca.service.UsuarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController // Indica que essa classe expõe endpoints REST
@RequestMapping("/usuarios") // Define o caminho base dos endpoints: /usuarios
public class UsuarioController {

    @Autowired // Injeta automaticamente o UsuarioService
    private UsuarioService service;

    @PutMapping // Endpoint para cadastrar ou editar Usuário
    public ResponseEntity<?> cadastrarOuEditar(@RequestBody UsuarioRequestDTO dto) {
        try {
            // Chama o service para cadastrar ou editar o usuário
            UsuarioResponseDTO response = service.cadastrarOuEditar(dto);

            // Retorna 200 OK com o JSON do usuário atualizado
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Caso ocorra erro, retorna 400 BAD REQUEST com mensagem detalhada
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    @GetMapping // Endpoint para listar todos os usuários cadastrados
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}") // Endpoint para buscar um usuário específico pelo ID
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}



