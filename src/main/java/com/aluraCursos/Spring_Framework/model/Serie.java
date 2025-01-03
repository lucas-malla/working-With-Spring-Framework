package com.aluraCursos.Spring_Framework.model;

import java.util.OptionalDouble;
//import com.aluraCursos.Spring_Framework.model.Categoria;

public class Serie{
    private String title;
    private Integer totalSeasons;
    private Double evaluacion;
    private String poster;
    private Categoria genre;
    private String actors;
    private String plot;
    private String language;

    public Serie(SerieDatas serieDatas){
        this.title = serieDatas.title();
        this.totalSeasons = serieDatas.totalSeasons();
        this.evaluacion = OptionalDouble.of(Double.valueOf(serieDatas.evaluacion())).orElse(0);
        this.poster = serieDatas.poster();
        this.genre = Categoria.fromString(serieDatas.genre().split(",")[0].trim());
        this.actors = serieDatas.actors();
        this.plot = serieDatas.plot();
        this.language = serieDatas.language();
    }

    @Override
    public String toString() {
        return
                ", title='" + title + '\'' +
                        "genre=" + genre +
                ", totalSeasons=" + totalSeasons +
                ", evaluacion=" + evaluacion +
                ", language='" + language + '\'' +
                ", poster='" + poster + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' ;
    }

    public Categoria getGenre() {
        return genre;
    }

    public void setGenre(Categoria genre) {
        this.genre = genre;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}



