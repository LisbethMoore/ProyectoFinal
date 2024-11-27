package com.lisbeth.proyectofinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.lisbeth.proyectofinal.model.Datos;
import com.lisbeth.proyectofinal.model.Manager;
import java.text.DecimalFormat;

public class Caso1Activity extends AppCompatActivity {
    SeekBar seekBarVelocidad, seekBarAngulo; //Barras para seleccionar velocidad inicial y angulo.
    TextView textVelocidad, textAngulo, outputResultados; //Etiquetas para mostrar los datos
    Button btnCalcular, btnSimular; //Botones
    ParabolicoView parabolicoView;   //Vista personalizada donde se vera el mp.

    //Variable que almacenara la Vo y Angulo
    private double velocidadInicial = 0;
    private double angulo = 0;

    @Override
    //creacion de la actividad
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso1);

            seekBarVelocidad = findViewById(R.id.seekBarVelocidad);
            seekBarAngulo = findViewById(R.id.seekBarAngulo);
            textVelocidad = findViewById(R.id.textVelocidad);
            textAngulo = findViewById(R.id.textAngulo);
            btnCalcular = findViewById(R.id.btnCalcular);
            btnSimular = findViewById(R.id.btnSimular);
            outputResultados = findViewById(R.id.outputResultados);
            parabolicoView = findViewById(R.id.parabolicoView);

            seekBarVelocidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //metodo que se ejecuta cada vez que el usuario cambia el valor del SeekBar
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // asigna el valor seleccionado del SeekBar a la variable velocidadInicial
                velocidadInicial = progress;
                //actualiza el TextView para mostrar el nuevo valor de la velocidad inicial
                textVelocidad.setText("Velocidad inicial: " + velocidadInicial + " m/s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
                // este metodo se llama cuando el usuario comienza a mover el SeekBar.
                // no realiza ninguna accion en este caso ya que esta vacio.


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
                // este metodo se llama cuando el usuario deja de mover el SeekBar (cuando suelta el control deslizante).
        });

        seekBarAngulo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //metodo que se ejecuta cada vez que el usuario cambia el valor del SeekBar
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // asigna el valor seleccionado del SeekBar a la variable angulo
                angulo = progress;
                textAngulo.setText("Ángulo: " + angulo + " grados");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnCalcular.setOnClickListener(v -> calcularMovimiento());
        btnSimular.setOnClickListener(v -> simularMovimiento());
    }
    //Metodo para  calcular alcance y altura max
    private void calcularMovimiento() {
        double anguloRad = Math.toRadians(angulo); //convierte el valor del angulo de grados a radianes
        double g = 9.81;

        //Math.pow : utilizado oara calcular una potencia
        double alcance = Math.pow(velocidadInicial, 2) * Math.sin(2 * anguloRad) / g;
        //Math.pow : eleva al cuadrado (2: ahi se pone el exponente que se necesite en este caso 2)
        double alturaMaxima = Math.pow(velocidadInicial * Math.sin(anguloRad), 2) / (2 * g);

        // Limitara el numero de decimales mostrados, los resultados seran mostrados con dos decimales
        DecimalFormat df = new DecimalFormat("#.##");
        String resultados = "Alcance: " + df.format(alcance) + " metros\n" +
                "Altura máxima: " + df.format(alturaMaxima) + " metros";

        //Muestra los resultados
        outputResultados.setText(resultados);

        // Inserta los datos calculados en la base de datos
        Manager manager = new Manager(this);
        Datos datos = new Datos(velocidadInicial, angulo, 0, alcance, alturaMaxima);
        manager.insertarDatos(datos);
    }
    //Metodo que inicia la simulacion del mp
    private void simularMovimiento() {
        parabolicoView.simular(velocidadInicial, angulo);
    }
}