package br.com.lavajato.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DefaultError>> handleValidation(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors().stream()
                .map(e -> new DefaultError(e.getField(), e.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest().body(erros);
    }

    public record DefaultError(String campo, String mensagem) {}
}
