package com.lisbeth.proyectofinal.model;

public class Datos {
    private int id;
    private double velocidadInicial;
    private double angulo;
    private double alturaInicial;
    private double alcance;
    private double alturaMaxima;

    // Constructor con parámetros
    public Datos(double velocidadInicial, double angulo, double alturaInicial, double alcance, double alturaMaxima) {
        this.velocidadInicial = velocidadInicial;
        this.angulo = angulo;
        this.alturaInicial = alturaInicial;
        this.alcance = alcance;
        this.alturaMaxima = alturaMaxima;
    }

    // Constructor vacío (útil para inicializar un objeto y asignar valores posteriormente)
    public Datos() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVelocidadInicial() {
        return velocidadInicial;
    }

    public void setVelocidadInicial(double velocidadInicial) {
        this.velocidadInicial = velocidadInicial;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public double getAlturaInicial() {
        return alturaInicial;
    }

    public void setAlturaInicial(double alturaInicial) {
        this.alturaInicial = alturaInicial;
    }

    public double getAlcance() {
        return alcance;
    }

    public void setAlcance(double alcance) {
        this.alcance = alcance;
    }

    public double getAlturaMaxima() {
        return alturaMaxima;
    }

    public void setAlturaMaxima(double alturaMaxima) {
        this.alturaMaxima = alturaMaxima;
    }

    @Override
    public String toString() {
        return "Datos{" +
                "id=" + id +
                ", velocidadInicial=" + velocidadInicial +
                ", angulo=" + angulo +
                ", alturaInicial=" + alturaInicial +
                ", alcance=" + alcance +
                ", alturaMaxima=" + alturaMaxima +
                '}';
    }
}