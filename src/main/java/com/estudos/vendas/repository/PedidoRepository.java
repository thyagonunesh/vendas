package com.estudos.vendas.repository;

import com.estudos.vendas.entity.Cliente;
import com.estudos.vendas.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query(value = "select p from Pedido p where p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);

}
