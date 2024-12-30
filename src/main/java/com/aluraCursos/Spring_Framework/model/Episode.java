package com.aluraCursos.Spring_Framework.model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episode {
    private Integer season;
    private String title;
    private Integer numberEpisode;
    private Double evaluation;
    private LocalDate releeseDate;

    public Episode(Integer number, EpisodeDatas d){
        this.season = number;
        this.title = d.titulo();
        this.numberEpisode = d.numeroEpisodeo();

        try{
            this.evaluation = Double.valueOf(d.evaluacion());
        }catch (NumberFormatException e){
            this.evaluation = 0.0;
        }
        try{
            this.releeseDate = LocalDate.parse(d.fechaLanzamiento());
        }catch (DateTimeException e){
            this.releeseDate = null;
        }

    }
    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumberEpisode() {
        return numberEpisode;
    }

    public void setNumberEpisode(Integer numberEpisode) {
        this.numberEpisode = numberEpisode;
    }

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Double evaluation) {
        this.evaluation = evaluation;
    }

    public LocalDate getReleeseDate() {
        return releeseDate;
    }

    public void setReleeseDate(LocalDate releeseDate) {
        this.releeseDate = releeseDate;
    }


    @Override
    public String toString() {
        return "Episode{" +
               // "season=" + season +
                ", title='" + title + '\'' +
                ", numberEpisode=" + numberEpisode +
                ", evaluation=" + evaluation +
                //", releeseDate=" + releeseDate +
                '}';
    }
}


