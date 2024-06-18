package com.estudos.vendas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoRetornoDTO {

    private Integer codigo;

    private String cpf;

    private String nomeCliente;

    private BigDecimal total;

    private String dataPedido;

    private List<ItemPedidoRetornoDTO> itens;

}
