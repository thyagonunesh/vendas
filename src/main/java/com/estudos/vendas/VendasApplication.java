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
			repository.salvar(new Cliente("Douglas"));
			repository.salvar(new Cliente("Outro Cliente"));

			List<Cliente> todosClientes = repository.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("Atualizando Clientes");
			todosClientes.forEach( cliente -> {
				cliente.setNome(cliente.getNome() + " atualizado");
				repository.atualizar(cliente);
				System.out.println(cliente);
			});

			System.out.println("Buscando Clientes");
			repository.buscarPorNome("Cli").forEach(System.out::println);

			System.out.println("Deletando Clientes");
			repository.obterTodos().forEach(cliente -> {
				repository.deletar(cliente.getId());
			});

			todosClientes = repository.obterTodos();
			if (todosClientes.isEmpty()) {
				System.out.println("Nenhum cliente encontrado");
			} else {
				todosClientes.forEach(System.out::println);
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
