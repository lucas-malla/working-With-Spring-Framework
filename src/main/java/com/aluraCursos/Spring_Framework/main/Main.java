package com.aluraCursos.Spring_Framework.main;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.aluraCursos.Spring_Framework.model.Episode;
import com.aluraCursos.Spring_Framework.model.EpisodeDatas;
import com.aluraCursos.Spring_Framework.model.SeasonData;
import com.aluraCursos.Spring_Framework.model.SerieDatas;
import com.aluraCursos.Spring_Framework.service.ApiConsumption;
import com.aluraCursos.Spring_Framework.service.CombinesDatas;
import com.aluraCursos.Spring_Framework.model.Serie;

import javax.sql.rowset.serial.SerialException;
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
//Creasiones de las funciones de las instancias1
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
           List<Serie> serie = new ArrayList<>();
           serie = serieDatas.stream()
                   .map(d -> new Serie(d))
                   .collect(Collectors.toList());

           serie.stream()
                   .sorted(Comparator.comparing(Serie::getGenre))
                   .forEach(System.out::println);
        }

}