package com.estudos.vendas.controller;

import com.estudos.vendas.dto.entrada.AtualizaStatusPedidoDTO;
import com.estudos.vendas.dto.entrada.PedidoDTO;
import com.estudos.vendas.dto.saida.PedidoRetornoDTO;
import com.estudos.vendas.entity.Pedido;
import com.estudos.vendas.enums.StatusPedido;
import com.estudos.vendas.service.PedidoServico;
import com.estudos.vendas.service.impl.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizaStatusPedidoDTO dto) {
        String novoStatus = dto.getNovoStatus();
        pedidoServico.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

}
