package com.estudos.vendas;

import com.estudos.vendas.domain.Cliente;
import com.estudos.vendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository repository){
		return args -> {
			System.out.println("Salvando Clientes");
			repository.save(new Cliente("Fulano"));
			repository.save(new Cliente("Outro Cliente"));

			List<Cliente> result = repository.encontrarPorNome("ano");
			result.forEach(System.out::println);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
