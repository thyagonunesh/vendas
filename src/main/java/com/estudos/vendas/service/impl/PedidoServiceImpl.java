package com.estudos.vendas.service.impl;

import com.estudos.vendas.dto.ItemPedidoDTO;
import com.estudos.vendas.dto.PedidoDTO;
import com.estudos.vendas.entity.Cliente;
import com.estudos.vendas.entity.ItemPedido;
import com.estudos.vendas.entity.Pedido;
import com.estudos.vendas.entity.Produto;
import com.estudos.vendas.exception.RegraNegocioException;
import com.estudos.vendas.repository.ClienteRepository;
import com.estudos.vendas.repository.ItemPedidoRepository;
import com.estudos.vendas.repository.PedidoRepository;
import com.estudos.vendas.repository.ProdutoRepository;
import com.estudos.vendas.service.PedidoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoServico {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido save(PedidoDTO pedidoDTO) {

        Cliente cliente = clienteRepository
                            .findById(pedidoDTO.getCliente())
                            .orElseThrow(() -> new RegraNegocioException("Código de cliente " + pedidoDTO.getCliente() + " inválido"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());

        List<ItemPedido> itemPedidos = converterItens(pedido, pedidoDTO.getItens());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);

        return pedido;
    }

    @Override
    public Pedido getPedidoById(Integer id) {
        return pedidoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itensDTO) {

        if (itensDTO.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return itensDTO
                .stream()
                .map(itemDTO -> {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(itemDTO.getQuantidade());
                    itemPedido.setPedido(pedido);

                    Produto produto = produtoRepository
                            .findById(itemDTO.getProduto())
                            .orElseThrow(() ->  new RegraNegocioException("Código de produto " + itemDTO.getProduto() +  " inválido!"));
                    itemPedido.setProduto(produto);

                    calculaTotalPedido(pedido, produto, itemPedido);

                    return itemPedido;
                }).collect(Collectors.toList());
    }

    private static void calculaTotalPedido(Pedido pedido, Produto produto, ItemPedido itemPedido) {
        BigDecimal subtotalItem = produto.getPreco().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));

        pedido.setTotal(Objects.isNull(pedido.getTotal())
                            ? BigDecimal.valueOf(0).add(subtotalItem)
                            : pedido.getTotal().add(subtotalItem));
    }
}
