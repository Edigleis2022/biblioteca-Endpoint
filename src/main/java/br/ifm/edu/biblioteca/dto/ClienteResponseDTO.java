package br.ifm.edu.biblioteca.dto;

import br.ifm.edu.biblioteca.model.cliente;


public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

    public ClienteResponseDTO(cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
}