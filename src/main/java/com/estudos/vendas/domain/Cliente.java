package com.estudos.vendas.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente {

    public Cliente(String nome) {
        this.nome = nome;
    }

    private Integer id;
    private String nome;

}
