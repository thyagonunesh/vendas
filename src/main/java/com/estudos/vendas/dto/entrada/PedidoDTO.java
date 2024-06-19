package com.estudos.vendas.dto.entrada;

import lombok.Data;

import java.util.List;

@Data
public class PedidoDTO {

    private Integer cliente;

    private List<ItemPedidoDTO> itens;

}
