package com.aluraCursos.Spring_Framework.service;

public interface ICombinesData {
    //creamos una Interfas
    //Implementamso los datos genericos en JAva
    <T> T getDatas(String json, Class<T> clase);
}
