package com.estudos.vendas.controller;

import com.estudos.vendas.dto.ItemPedidoDTO;
import com.estudos.vendas.dto.ItemPedidoRetornoDTO;
import com.estudos.vendas.dto.PedidoDTO;
import com.estudos.vendas.dto.PedidoRetornoDTO;
import com.estudos.vendas.entity.ItemPedido;
import com.estudos.vendas.entity.Pedido;
import com.estudos.vendas.exception.RegraNegocioException;
import com.estudos.vendas.service.PedidoServico;
import com.estudos.vendas.service.impl.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static com.estudos.vendas.service.impl.PedidoServiceImpl.converterPedido;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoServico pedidoServico;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoServico.save(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public PedidoRetornoDTO getPedidoById(@PathVariable Integer id) {
        return pedidoServico
                .obterPedidoCompleto(id)
                .map(PedidoServiceImpl::converterPedido)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
    }

}
