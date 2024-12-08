package com.aluraCursos.Spring_Framework.service;

public interface ICombinesData {
    //creamos una Interfas
    //Implementamso los datos genericos en JAva
    //LA t significa wu vamos a trbajar con TIPOS de datos Genericos.
    <T> T getDatas(String json, Class<T> clase);
}
