package br.ifm.edu.biblioteca.controller;

import br.ifm.edu.biblioteca.dto.ClienteRequestDTO;
import br.ifm.edu.biblioteca.dto.ClienteResponseDTO;
import br.ifm.edu.biblioteca.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody ClienteRequestDTO dto) {
        try {
            ClienteResponseDTO response = service.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    // 🔹 Endpoint para listar todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        List<ClienteResponseDTO> clientes = service.listarTodos();
        return ResponseEntity.ok(clientes);
    }
}
