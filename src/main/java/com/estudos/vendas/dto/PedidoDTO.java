package com.estudos.vendas.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoDTO {

    private Integer cliente;

    private List<ItemPedidoDTO> itens;

}
