package com.aluraCursos.Spring_Framework.main;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.aluraCursos.Spring_Framework.model.Episode;
import com.aluraCursos.Spring_Framework.model.EpisodeDatas;
import com.aluraCursos.Spring_Framework.model.SeasonData;
import com.aluraCursos.Spring_Framework.model.SerieDatas;
import com.aluraCursos.Spring_Framework.service.ApiConsumption;
import com.aluraCursos.Spring_Framework.service.CombinesDatas;

import java.util.*;
import java.util.stream.Collectors;
import java.util.List;

public class Main {
    //cremaos la instancia
    private Scanner scanner = new Scanner(System.in);
    private ApiConsumption apiConsumption = new ApiConsumption();
    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=25408fcb";
    private CombinesDatas combinesDatas = new CombinesDatas();
    private List<SerieDatas> serieDatas = new ArrayList<>();
    //creamos un menu
    public void showsTheMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 : Looking for series
                    2 : Looking for episode
                    3  : Showing series searched
                    
                    0 : Exit
                    """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searcheSerieWeb();
                    break;
                case 2:
                    searcheEpisodeForSerie();
                    break;
                case 3:
                    showSeriesSearched();
                    break;
                case 0:
                    System.out.println("Close for the app...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
}
        private  SerieDatas getSerieDatas(){
            System.out.println("Enter the name of the Series you want to watch");
            var serieName = scanner.nextLine();
            var json = apiConsumption.getDatas(URL_BASE + serieName.replace(" ", "+") + API_KEY);
            System.out.println(json);
            SerieDatas datas = combinesDatas.getDatas(json, SerieDatas.class);
            return datas;
        }

        private void searcheEpisodeForSerie() {
            SerieDatas serieDatas = getSerieDatas();
            List<SeasonData> season = new ArrayList<>();

            for (int i = 1; i <= serieDatas.totalSeasons(); i++) {
                var json = apiConsumption.getDatas(URL_BASE + serieDatas.title().replace(" ", "+") + "&Season=" + i + API_KEY);
                SeasonData seasonData = combinesDatas.getDatas(json, SeasonData.class);
                season.add(seasonData);
            }
            season.forEach(System.out::println);
        }

        private void searcheSerieWeb() {
            SerieDatas datas = getSerieDatas();
            serieDatas.add(datas);
            System.out.println(datas);
        }
       private void showSeriesSearched(){
            serieDatas.forEach(System.out::println);
        }

 /*
        System.out.println("enter the name of the series ");
        //tenemso qye tratar el espasio que va entre pañabras . Lo hacemos econ el metodo REPLASE().
        //Siempre que encuntre un espacio lo va a remplasar con un "+".
        var json = apiConsumption.getDatas(URL_BASE + nameSerie.replace(" ", "+") + API_KEY);
        //combertimos los tados de la SERIE en OBJETOS del tipo JAVA
        var datas = combinesDatas.getDatas(json, SerieDatas.class);

        //Bucamos los tados de la temporada.
        //Creamos un array list pra recorrer las temporadas
        List<SeasonData> season = new ArrayList<>();
        //creamos un for para recorer el Array.
        for (int i = 1; i <= datas.totalDeTemporadas(); i++) {
            json = apiConsumption.getDatas(URL_BASE + nameSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            var seasonData = combinesDatas.getDatas(json, SeasonData.class);
            //los agramos al Array
            season.add(seasonData);
        }


        //season.forEach(t -> t.episodeos().forEach(e -> System.out.println(e.evaluacion())));
        //season.forEach(System.out::println);

        //comvertir todas las informacion a una lista de datos episodeos.
        List<EpisodeDatas> episodeData = season.stream()
                .flatMap(t -> t.episodeos().stream())
                .collect(Collectors.toList());


        //Convirtiendo los datos de una lista del tipo episodeo
        List<Episode> episodeList = season.stream()
                .flatMap(t -> t.episodeos().stream()
                        .map(d -> new Episode(t.number(), d)))
                .collect(Collectors.toList());
        season.forEach(System.out::println);

        //vamos a filtrar nuestras temporada por puntuacion

        Map<Integer, Double> evalutionSeasson = episodeList.stream()
                .filter(e -> e.getEvaluation() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeason,
                        Collectors.averagingDouble(Episode::getEvaluation)));
        System.out.println(evalutionSeasson);

        DoubleSummaryStatistics est = episodeList.stream()
                .filter(e -> e.getEvaluation() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getEvaluation));
        System.out.println("Media de la evaluacion : " + est.getAverage());
        System.out.println("Episodeo Mejor evaluado : " + est.getMax());
        System.out.println("Episodeo menor evaluado : " + est.getMin());
    }

    }
         */

}