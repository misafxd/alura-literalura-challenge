package com.misa.libreria;

import com.misa.libreria.principal.Principal;
import com.misa.libreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibreriaApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LibreriaApplication.class, args);
	}

	public void run(String... args) throws Exception{
		Principal principal = new Principal(repository);
		principal.muestraMenu();
	}
}
