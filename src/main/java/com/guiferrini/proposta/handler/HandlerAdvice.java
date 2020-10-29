package com.guiferrini.proposta.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class HandlerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErroPadrao> tratativaDoErro(MethodArgumentNotValidException methodArgumentNotValidException){
        Collection<String> mensagens = new ArrayList<>();
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<FieldError> campoDoErros = bindingResult.getFieldErrors();
        campoDoErros.forEach(campoDoErro -> {
            String mensagem = String.format("ERRO no Campo: " + campoDoErro.getField() +  " - Motivo: " + campoDoErro.getDefaultMessage());
            mensagens.add(mensagem);
        });

        ErroPadrao erroPadrao = new ErroPadrao(mensagens);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

}
