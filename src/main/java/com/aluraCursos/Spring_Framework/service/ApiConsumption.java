package com.aluraCursos.Spring_Framework.service;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConsumption {
    //creamos un metodo que va recivir una URL

    public String getDatas(String url){
        //implementamos el codigo que a consumor nuestra Api.

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try{
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        String json = response.body();
        return json;
    }




}
