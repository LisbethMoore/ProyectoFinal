package com.lisbeth.proyectofinal.model;

public class Datos {
    private int id;
    private double velocidadInicial;
    private double angulo;
    private double alturaInicial;
    private double alcance;
    private double alturaMaxima;

    public Datos(double velocidadInicial, double angulo, double alturaInicial, double alcance, double alturaMaxima) {
        this.velocidadInicial = velocidadInicial;
        this.angulo = angulo;
        this.alturaInicial = alturaInicial;
        this.alcance = alcance;
        this.alturaMaxima = alturaMaxima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVelocidadInicial() {
        return velocidadInicial;
    }

    public double getAngulo() {
        return angulo;
    }

    public double getAlturaInicial() {
        return alturaInicial;
    }

    public double getAlcance() {
        return alcance;
    }

    public double getAlturaMaxima() {
        return alturaMaxima;
    }
}