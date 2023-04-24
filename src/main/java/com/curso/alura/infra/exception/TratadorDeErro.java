package com.curso.alura.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErro {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DadoserroValidacao::new));
    }

    private record DadoserroValidacao(String campo, String mensagem){
        public DadoserroValidacao(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
