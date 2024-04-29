package com.aluracursos.screenmatch2;
import com.aluracursos.screenmatch2.principal.EjemploStreams;
import com.aluracursos.screenmatch2.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Screenmatch2Application implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(Screenmatch2Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraElMenu();
/*		EjemploStreams ejemploStreams = new EjemploStreams();
		ejemploStreams.muestraEjemplo();*/
	}
}
