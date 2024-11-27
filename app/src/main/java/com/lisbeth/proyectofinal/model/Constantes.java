package com.lisbeth.proyectofinal.model;


public class Constantes {

    public static final String NAME_DB = "Historial";
    public static final int VERSION_DB = 1;

    public static final String SENTENCIATABLA =
            "CREATE TABLE Datos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "velocidad_inicial REAL, " +
                    "angulo REAL, " +
                    "altura_inicial REAL, " +
                    "alcance REAL, " +
                    "altura_maxima REAL)";
}