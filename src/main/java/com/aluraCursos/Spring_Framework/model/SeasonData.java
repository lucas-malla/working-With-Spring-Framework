package com.aluraCursos.Spring_Framework.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//cin esto ignora los campos que nomapeamos en esta clase
@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(
        //Creamos una lista de eposodeos
        @JsonAlias("Season") Integer number,
        @JsonAlias("Episodes") List<EpisodeDatas> episodeos

) {

}
