package com.estudos.vendas.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class ApiErroModel {

    private String mensagem;

    private HttpStatus status;

    private LocalDateTime horario;

}
