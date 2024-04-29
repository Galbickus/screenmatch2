package com.aluracursos.screenmatch2;

import com.aluracursos.screenmatch2.model.DatosEpisodio;
import com.aluracursos.screenmatch2.model.DatosSerie;
import com.aluracursos.screenmatch2.model.DatosTemporadas;
import com.aluracursos.screenmatch2.principal.Principal;
import com.aluracursos.screenmatch2.service.ConsumoAPI;
import com.aluracursos.screenmatch2.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Screenmatch2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Screenmatch2Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraElMenu();
	}
}
