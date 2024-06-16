package com.estudos.vendas.controller;

import com.estudos.vendas.dto.PedidoDTO;
import com.estudos.vendas.entity.Pedido;
import com.estudos.vendas.service.PedidoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Pedido getPedidoById(@PathVariable Integer id) {
        return pedidoServico.getPedidoById(id);
    }

}
