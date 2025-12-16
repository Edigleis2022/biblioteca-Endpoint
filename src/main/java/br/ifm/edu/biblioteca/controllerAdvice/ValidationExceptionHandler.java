package br.ifm.edu.biblioteca.controllerAdvice;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Intercepta erros de validação de DTOs anotados com @Valid
 * e retorna mensagens amigáveis em JSON
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        // Para cada campo inválido, adiciona o campo e a mensagem no JSON
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        // Retorna 400 Bad Request com JSON detalhado
        return ResponseEntity.badRequest().body(errors);
    }
}