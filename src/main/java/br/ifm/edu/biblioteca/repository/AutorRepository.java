package br.ifm.edu.biblioteca.repository;

import br.ifm.edu.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}


