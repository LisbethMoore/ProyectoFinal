package com.lisbeth.proyectofinal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class    ParabolicoView extends View {

    //variables
    private Paint paint;
    private Paint pathPaint;
    private Paint textPaint;
    private double velocidadInicial;
    private double angulo;
    private double alturaInicial;
    private boolean isSimulating = false;
    private float t = 0; // Tiempo
    private double tiempoDeVuelo; // Tiempo total de vuelo

    // Gravedad (en m/s^2)
    private final double g = 9.8;

    //Constructores
    public ParabolicoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParabolicoView(Context context) {
        super(context);
        init();
    }

    //Metodo init() inicializar la vista personalizada
    private void init() {
        // Pintura para la bolita
        paint = new Paint(); //crea un objeto Paint para dibujar en el lienzo
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);//establece el grosor del trazo en 5 pixeles

        // Pintura para la trayectoria
        pathPaint = new Paint(); //crea un objeto Paint para dibujar la trayectoria
        pathPaint.setColor(Color.BLUE);

        pathPaint.setStrokeWidth(3);
        pathPaint.setStyle(Paint.Style.STROKE);  //establece que la pintura se usara solo para el borde, no para rellenar la forma

        //Pintura para los textos (tiempo, velocidad, etc.)
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        textPaint.setAntiAlias(true); //activa el suavizado de bordes para que el texto se vea mas nitido
    }

    //Método para iniciar simulación sin altura inicial
    public void simular(double velocidadInicial, double angulo) {
        simular(velocidadInicial, angulo, 0);
    }

    //Método para iniciar simulación con altura inicial
    public void simular(double velocidadInicial, double angulo, double alturaInicial) {
        this.velocidadInicial = velocidadInicial;
        this.angulo = Math.toRadians(angulo);
        this.alturaInicial = alturaInicial;
        this.isSimulating = true;
        this.t = 0;

        //calcular el tiempo de vuelo total
        double velocidadVertical = velocidadInicial * Math.sin(this.angulo); // Calcula la velocidad vertical inicial usando el seno del ángulo
        //tiempo de vuelo
        tiempoDeVuelo = (velocidadVertical + Math.sqrt(velocidadVertical * velocidadVertical + 2 * g * alturaInicial)) / g;


        //crea un nuevo hilo para simular el movimiento de la parábola
        //el hilo se ejecuta mientras isSimulating sea verdadero
        new Thread(new Runnable() {
            @Override
            public void run() {
                //incrementa el tiempo 't' en pequeños intervalos (0.03 segundos)
                while (isSimulating) {
                    t += 0.03;
                    postInvalidate();//redibuja la vista para actualizar la simulacion

                    //detener la simulacion si el tiempo supera el tiempo de vuelo total
                    if (t > tiempoDeVuelo) {
                        isSimulating = false;
                    }
                    // Pausa el hilo durante 30 ms para crear un efecto de animación suave
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start(); // Inicia el hilo
    }

    @Override

    //Metodo onDraw : se utiliza para dibujar elementos (trayectoria de un proyectil)
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Definir el margen en píxeles (50px en este caso)
        int margen = 50;

        // Dibujar la trayectoria completa antes de simular la bolita
        for (float tempT = 0; tempT < t; tempT += 0.05) {
            float pathX = (float) (velocidadInicial * tempT * Math.cos(angulo));
            float pathY = (float) (alturaInicial + (velocidadInicial * tempT * Math.sin(angulo) - (0.5 * g * tempT * tempT)))+margen;

            // Verifica que la trayectoria esté dentro de los límites de la pantalla
            if (pathY >= 0) {
                canvas.drawPoint(pathX, getHeight() - pathY, pathPaint);  // Dibuja un punto de la trayectoria
            }
        }

        // Simular la posición de la bolita en el tiempo actual
        float x = (float) (velocidadInicial * t * Math.cos(angulo));
        float y = (float) (alturaInicial + (velocidadInicial * t * Math.sin(angulo) - (0.5 * g * t * t)))+margen;

        if (y >= 0) {
            canvas.drawCircle(x, getHeight() - y , 20, paint);  // Dibuja la bolita

            // Mostrar información sobre la trayectoria actual
            mostrarInformacion(canvas, x, y);
        } else {
            isSimulating = false;
        }
    }

    // Método para mostrar información (tiempo, velocidad, aceleración)
    private void mostrarInformacion(Canvas canvas, float x, float y) {
        // Mostrar tiempo actual
        canvas.drawText("Tiempo: " + String.format("%.2f", t) + " s", 50, 50, textPaint);

        // Velocidad en cada punto
        double velocidadX = velocidadInicial * Math.cos(angulo);
        double velocidadY = velocidadInicial * Math.sin(angulo) - g * t;
        double velocidadTotal = Math.sqrt(velocidadX * velocidadX + velocidadY * velocidadY);
        canvas.drawText("Rapidez Final: " + String.format("%.2f", velocidadTotal) + " m/s", 50, 100, textPaint);

        // Aceleración (es constante en caída libre: 9.81 m/s^2)
        canvas.drawText("Aceleración: " + g + " m/s²", 50, 150, textPaint);

        // Tiempo total de vuelo
        canvas.drawText("Tiempo de vuelo total: " + String.format("%.2f", tiempoDeVuelo) + " s", 50, 200, textPaint);

        // Alcance actual (posición en el eje x)
        canvas.drawText("Alcance actual: " + String.format("%.2f", x) + " metros", 50, 250, textPaint);

        // Altura actual (posición en el eje y)
        canvas.drawText("Altura actual: " + String.format("%.2f", y) + " metros", 50, 300, textPaint);

    }

    public void stopSimulation() {
        isSimulating = false;
        t = 0;
        invalidate();
    }
}
