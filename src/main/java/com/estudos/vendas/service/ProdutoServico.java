package com.estudos.vendas.service;

import com.estudos.vendas.entity.Produto;

import java.util.List;

public interface ProdutoServico {

    Produto getProdutoById(Integer id);

    Produto save(Produto Produto);

    void delete(Integer id);

    void update(Integer id, Produto Produto);

    List<Produto> find(Produto filtro);
    
}
