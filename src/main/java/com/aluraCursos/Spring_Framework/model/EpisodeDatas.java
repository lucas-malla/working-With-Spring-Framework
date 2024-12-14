package com.aluraCursos.Spring_Framework.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeDatas(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Episode") Integer numeroEpisodeo,
        @JsonAlias("imdbRating") String evaluacion,
        @JsonAlias("Released")  String fechaLanzamiento
) {
}
