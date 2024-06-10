package com.estudos.vendas;

import com.estudos.vendas.domain.Cliente;
import com.estudos.vendas.domain.Pedido;
import com.estudos.vendas.repository.ClienteRepository;
import com.estudos.vendas.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clienteRepository,
								  @Autowired PedidoRepository pedidoRepository){
		return args -> {
			System.out.println("Salvando Clientes");
			Cliente fulano = new Cliente("Fulano");
			clienteRepository.save(fulano);

			Pedido p = new Pedido();
			p.setCliente(fulano);
			p.setDataPedido(LocalDateTime.now());
			p.setTotal(BigDecimal.valueOf(100));
			pedidoRepository.save(p);

//			Cliente cliente = clienteRepository.findClienteFetchPedidos(fulano.getId());
//			System.out.println(cliente);
//			System.out.println(cliente.getPedidos());

			pedidoRepository.findByCliente(fulano).forEach(System.out::println);


		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
