package com.aluraCursos.Spring_Framework.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//cin esto ignora los campos que nomapeamos en esta clase
@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieDatas(
        @JsonAlias("Title") String title,
        @JsonAlias("totalSeasons") Integer totalSeasons ,
        @JsonAlias("imdbRating") String evaluacion
) {
}
