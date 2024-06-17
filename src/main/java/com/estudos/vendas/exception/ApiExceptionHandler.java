package com.estudos.vendas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErroModel handleRegraNegocioException(RegraNegocioException exception) {
        return ApiErroModel.builder()
                .horario(LocalDateTime.now())
                .mensagem(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

}
