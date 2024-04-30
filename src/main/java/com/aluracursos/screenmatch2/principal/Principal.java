package com.aluracursos.screenmatch2.principal;

import com.aluracursos.screenmatch2.model.DatosEpisodio;
import com.aluracursos.screenmatch2.model.DatosSerie;
import com.aluracursos.screenmatch2.model.DatosTemporadas;
import com.aluracursos.screenmatch2.model.Episodio;
import com.aluracursos.screenmatch2.service.ConsumoAPI;
import com.aluracursos.screenmatch2.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t="; //Creo que le falta el igual
    private final String API_KEY = "&apikey=26dd704c";
    private ConvierteDatos conversor = new ConvierteDatos();
    public void muestraElMenu(){
        System.out.println("Escriba el nombre de la serie a buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos( URL_BASE + nombreSerie.replace(" ","+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);
        //Busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <=datos.totalDeTemporadas(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ","+")+ "&Season="+ i +API_KEY);
            //                                  https://www.omdbapi.com/?t=Game+of+Thrones&Season="+ i +"&apikey=26dd704c
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        //temporadas.forEach(System.out::println);
        //Mostrar solo el tituulo de los episodios para las temporadas

/*        for (int i = 0; i < datos.totalDeTemporadas(); i++) {
            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            System.out.println("\nTemporada: " + (i+1) + "\n");
            for (int j = 0; j < episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }*/

        //mejoría usando funciones lambda
        //temporadas.forEach(t->t.episodios().forEach(e-> System.out.println(e.titulo())));

        //convertir la informacion a una lista de tipo DatosEpisodio

        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        //top 5 episodios

        datosEpisodios.stream()
                .filter(e ->!e.evaluacion().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .limit(5)
                .forEach(System.out::println);
        //conviertiendo los datos a una lista de tipo Episodios

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d->new Episodio(t.numero(),d)))
                .collect(Collectors.toList());
        episodios.forEach(System.out::println);

    }
}
