package com.aluracursos.screenmatch2.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {
    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Tony", "Sofi", "Martín", "Bruno", "Vero", "Diana", "Carlos", "Lucia", "Alma", "Ali", "Carlitos", "Jony");
        nombres.stream()
                .sorted()
                /*limitar la lista despues de ordenada*/
                .limit(12)
                /*filtro por inicial*/
                .filter(n -> n.startsWith("C"))
                /*convertir a may*/
                .map(n ->n.toUpperCase())
                /*Este método es parte de la interfaz Iterable en Java y se utiliza para iterar sobre cada elemento en una colección. Toma un argumento que es un objeto de tipo Consumer, el cual representa una operación que se realizará en cada elemento de la colección*/
                .forEach(System.out::println);

    }
}
