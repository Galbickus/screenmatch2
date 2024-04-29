package com.aluracursos.screenmatch2;

import com.aluracursos.screenmatch2.model.DatosEpisodio;
import com.aluracursos.screenmatch2.model.DatosSerie;
import com.aluracursos.screenmatch2.model.DatosTemporadas;
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
		var consumoApi = new ConsumoAPI();
		var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&apikey=26dd704c");
		//var json = consumoApi.obtenerDatos("https://coffee.alexflipnote.dev/random.json");
	System.out.println(json);
		ConvierteDatos conversor = new ConvierteDatos();
		var datos = conversor.obtenerDatos(json, DatosSerie.class);
		System.out.println(datos);
		json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=Game+of+Thrones&Season=1&Episode=1&apikey=26dd704c");
		//la nomenclatura de la instancia debería ser singular
		DatosEpisodio episodios = conversor.obtenerDatos(json, DatosEpisodio.class);
		System.out.println(episodios);

		List<DatosTemporadas> temporadas = new ArrayList<>();
		for (int i = 1; i <=datos.totalDeTemporadas(); i++) {
			json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=Game+of+Thrones&Season="+ i +"&apikey=26dd704c");
			var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
			temporadas.add(datosTemporadas);
		}
		temporadas.forEach(System.out::println);
	}
}
