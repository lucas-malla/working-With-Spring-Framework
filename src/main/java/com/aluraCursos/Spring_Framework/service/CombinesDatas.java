package com.aluraCursos.Spring_Framework.service;

import com.aluraCursos.Spring_Framework.model.SerieDatas;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class  CombinesDatas implements ICombinesData {
    //vamos a mapear losd atos que vienen de la api.
    private ObjectMapper odjectMapper = new ObjectMapper();
    //Se crea la interfas
    @Override
    public <T> T getDatas(String json, Class<T> clase) {
        //Va a retornas un objetomapper que va leer el valor y lo va a tranformar. Lo lee  y lo trasforma en la clase que le psamod
        try {
            return odjectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
