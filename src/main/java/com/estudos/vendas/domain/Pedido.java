package com.estudos.vendas.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Pedido {

    private Integer id;
    private Cliente cliente;
    private LocalDateTime dataPedido;
    private BigDecimal total;

}
