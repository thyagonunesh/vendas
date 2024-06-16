package com.estudos.vendas.service.impl;

import com.estudos.vendas.entity.Produto;
import com.estudos.vendas.repository.ProdutoRepository;
import com.estudos.vendas.service.ProdutoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoServico {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto getProdutoById(Integer id) {
        return produtoRepository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @Override
    public Produto save(Produto Produto) {
        return produtoRepository.save(Produto);
    }

    @Override
    public void delete(Integer id) {
        produtoRepository
                .findById(id)
                .map(produto -> {
                    produtoRepository.delete(produto);
                    return produto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @Override
    public void update(Integer id, Produto produto) {
        produtoRepository
                .findById(id)
                .map( produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    produtoRepository.save(produto);
                    return produtoExistente;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @Override
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Produto> example = Example.of(filtro, matcher);
        return produtoRepository.findAll(example);
    }

}
