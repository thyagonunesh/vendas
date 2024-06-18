package com.estudos.vendas.service.impl;

import com.estudos.vendas.dto.ItemPedidoDTO;
import com.estudos.vendas.dto.ItemPedidoRetornoDTO;
import com.estudos.vendas.dto.PedidoDTO;
import com.estudos.vendas.dto.PedidoRetornoDTO;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
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

    public static PedidoRetornoDTO converterPedido(Pedido pedido) {

        return PedidoRetornoDTO
                .builder()
                .cpf(pedido.getCliente().getCpf())
                .total(pedido.getTotal())
                .codigo(pedido.getId())
                .itens(converterItens(pedido.getItens()))
                .nomeCliente(pedido.getCliente().getNome())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .build();

    }

    public static List<ItemPedidoRetornoDTO> converterItens(List<ItemPedido> itens) {
        List<ItemPedidoRetornoDTO> itensRetornoDTO = new ArrayList<>();

        itens.forEach(itemPedido -> {
            ItemPedidoRetornoDTO itemPedidoRetornoDTO = ItemPedidoRetornoDTO
                    .builder()
                    .descricao(itemPedido.getProduto().getDescricao())
                    .precoUnitario(itemPedido.getProduto().getPreco())
                    .quantidade(itemPedido.getQuantidade())
                    .build();

            itensRetornoDTO.add(itemPedidoRetornoDTO);
        });

        return itensRetornoDTO;

    }

}
