package com.estudos.vendas.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ApiErroModel {

    private List<String> erros;

    public ApiErroModel(String mensagem) {
        this.erros.add(mensagem);
    }

}
