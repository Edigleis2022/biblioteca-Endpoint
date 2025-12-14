package br.ifm.edu.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.ifm.edu.biblioteca.model.cliente;

@Repository
public interface ClienteRepository extends JpaRepository<cliente, Long> {
    boolean existsByEmail(String email);
}