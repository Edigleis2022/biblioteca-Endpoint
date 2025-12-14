package br.ifm.edu.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@GetMapping("/olamundo")
public ResponseEntity<String> hello() {
	//\n quebra de linha
    return ResponseEntity.ok(
        "Hello World, Olá Mundo!\n" +
        "Estamos desenvolvendo Trabalho de Endpoint.\n" +
        "Edigleis e Bruno"
    );
}
}
