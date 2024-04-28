package com.aluracursos.screenmatch2.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosSeries(
        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons") Integer totalDeTemporadas,
        @JsonAlias("imdbRating") String evaluacion) {
}
