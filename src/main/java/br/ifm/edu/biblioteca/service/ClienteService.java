package br.ifm.edu.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifm.edu.biblioteca.dto.ClienteResponseDTO;
import br.ifm.edu.biblioteca.model.cliente;
import br.ifm.edu.biblioteca.dto.ClienteRequestDTO;
import br.ifm.edu.biblioteca.repository.ClienteRepository;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {
        cliente cliente = new cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente salvo = repository.save(cliente);
        return new ClienteResponseDTO(salvo);
    }

    public List<ClienteResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(ClienteResponseDTO::new)
                .toList();
    }
}
