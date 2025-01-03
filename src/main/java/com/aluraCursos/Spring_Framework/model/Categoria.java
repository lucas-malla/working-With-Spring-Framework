
package com.aluraCursos.Spring_Framework.model;

public enum Categoria{
    ACCTION("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    ADVENTURE("Adventure"),
    CRIMEN("Crime");

    private String categoryOmdb;
    Categoria(String categoryOmdb){
        this.categoryOmdb = categoryOmdb;
    }

    public static Categoria fromString(String text){
        for (Categoria categoria : Categoria.values()){
            if (categoria.categoryOmdb.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        try {
            throw new IllegalAccessException("Nunguna categoria encontrada : " + text);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
