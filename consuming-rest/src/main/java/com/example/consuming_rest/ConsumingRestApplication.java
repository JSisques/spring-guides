package com.example.consuming_rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@SpringBootApplication
public class ConsumingRestApplication {

	public static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}


	/**
	 * "@Bean": // En Spring Boot, un bean es un objeto instanciado, ensamblado y gestionado por el contenedor IoC (Inversión de Control) de Spring.
	 * "@Profile("!test")": Este Bean solamente se registra cuando el perfil test NO está activo.
	 * ApplicationRunner es una interfaz de Spring Boot que permite ejecutar código cuando la aplicación ha terminado de arrancar.
	 */
	@Bean
	@Profile("!test")
	public ApplicationRunner run(RestClient.Builder builder) {
		// Creamos el cliente HTTP
		RestClient restClient = builder.baseUrl("http://localhost:8080").build();

		// Lambda
		return args -> {
			Quote quote = restClient
					.get().uri("/api/random")
					.retrieve() // Ejecuta la petición y dame acceso a la respuesta.
					.body(Quote.class); // Convierte el body JSON de la respuesta a un objeto Java Quote.

            log.info(Objects.requireNonNull(quote).toString()); // "Asegúrate de que quote no sea null. Si lo es, lanza una excepción."
		};
	}

}
