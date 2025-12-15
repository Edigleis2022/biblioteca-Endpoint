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

@RestController // Indica que essa classe expõe endpoints REST
@RequestMapping("/emprestimos") // Define o caminho base dos endpoints: /emprestimos
public class EmprestimoController {

    @Autowired // Injeta automaticamente o EmprestimoService
    private EmprestimoService service;

    @PutMapping // Endpoint para cadastrar ou editar Empréstimo
    public ResponseEntity<?> cadastrarOuEditar(@RequestBody EmprestimoRequestDTO dto) {
        try {
            // Chama o service para cadastrar ou editar o empréstimo
            EmprestimoResponseDTO response = service.cadastrarOuEditar(dto);

            // Retorna 200 OK com o JSON do empréstimo atualizado
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Caso ocorra erro, retorna 400 BAD REQUEST com mensagem detalhada
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    @GetMapping // Endpoint para listar todos os empréstimos cadastrados
    public ResponseEntity<List<EmprestimoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}") // Endpoint para buscar um empréstimo específico pelo ID
    public ResponseEntity<EmprestimoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}

