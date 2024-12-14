package com.aluraCursos.Spring_Framework.main;

import com.aluraCursos.Spring_Framework.model.Episode;
import com.aluraCursos.Spring_Framework.model.EpisodeDatas;
import com.aluraCursos.Spring_Framework.model.SeasonData;
import com.aluraCursos.Spring_Framework.model.SerieDatas;
import com.aluraCursos.Spring_Framework.service.ApiConsumption;
import com.aluraCursos.Spring_Framework.service.CombinesDatas;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    //cremaos la instancia
    private ApiConsumption apiConsumption = new ApiConsumption();
    private CombinesDatas combinesDatas = new CombinesDatas();

    //creamos constantes: son valores fijos que no van a acambiar.
    //La palabra Final determina que va hacer una constante
    //Y al ser contaste escrivimos toto el nombre en mayuscula.
    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=25408fcb";

    //creamos un menu para que el usuario pueda ingresas el nombre de una serie.
    public void  showsTheMenu(){
        System.out.println("enter the name of the series ");
        var nameSerie = scanner.nextLine();
        //tenemso qye tratar el espasio que va entre pañabras . Lo hacemos econ el metodo REPLASE().
        //Siempre que encuntre un espacio lo va a remplasar con un "+".
        var json =  apiConsumption.getDatas(URL_BASE + nameSerie.replace(" ", "+") + API_KEY);
        //combertimos los tados de la SERIE en OBJETOS del tipo JAVA
        var datas = combinesDatas.getDatas(json, SerieDatas.class);
        //Bucamos los tados de la temporada.
        //Creamos un array list pra recorrer las temporadas
        List<SeasonData> season = new ArrayList<>();
        //creamos un for para recorer el Array.
        for (int i = 1; i <= datas.totalDeTemporadas(); i++) {
            json = apiConsumption.getDatas(URL_BASE + nameSerie.replace(" ", "+")+ "&Season=" +i+ API_KEY);
            var seasonData = combinesDatas.getDatas(json, SeasonData.class);
            //los agramos al Array
            season.add(seasonData);
        }
       // season.forEach(System.out::println);
        
        //trbajamos con Listas dentro der listas. || 
        //Mostramos los titulos de los episodiod para la temporada.
        //Iteramos apr poder conseguir los espisodeos
       // for (int i = 0; i < datas.totalDeTemporadas() ; i++) {
         //   List<SerieDatas> episodeSeason = season.get(i).episodeos();
            //este for ietera la lista y tre los titulos
           // for (int j = 0; j <episodeSeason.size() ; j++) {
             //   System.out.println(episodeSeason.get(j).titulo());
           // }
      // }
       // season.forEach(t -> t.episodeos().forEach(e -> System.out.println(e.titulo())));

        //comvertir todas las informacion a una lista de datos episodeos.
        List<EpisodeDatas> episodeData = season.stream()
                .flatMap(t -> t.episodeos().stream())
                .collect(Collectors.toList());

        //Top 5 episode
        System.out.println("Top 5 ");
        episodeData.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeDatas::evaluacion).reversed())
                .limit(5)
                .forEach(System.out::println);
        //Convirtiendo los datos de una lista del tipo episodeo
        List<Episode> episodeList = season.stream()
                .flatMap(t -> t.episodeos().stream()
                         .map(d -> new Episode(t.number(),d)))
                .collect(Collectors.toList());
    }
}
