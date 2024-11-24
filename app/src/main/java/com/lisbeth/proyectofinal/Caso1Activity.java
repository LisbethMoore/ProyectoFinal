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
    SeekBar seekBarVelocidad, seekBarAngulo;
    TextView textVelocidad, textAngulo, outputResultados;
    Button btnCalcular, btnSimular;
    ParabolicoView parabolicoView;

    private double velocidadInicial = 0;
    private double angulo = 0;

    @Override
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
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                velocidadInicial = progress;
                textVelocidad.setText("Velocidad inicial: " + velocidadInicial + " m/s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBarAngulo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
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

    private void calcularMovimiento() {
        double anguloRad = Math.toRadians(angulo);
        double g = 9.81;

        double alcance = Math.pow(velocidadInicial, 2) * Math.sin(2 * anguloRad) / g;
        double alturaMaxima = Math.pow(velocidadInicial * Math.sin(anguloRad), 2) / (2 * g);

        DecimalFormat df = new DecimalFormat("#.##");
        String resultados = "Alcance: " + df.format(alcance) + " metros\n" +
                "Altura máxima: " + df.format(alturaMaxima) + " metros";

        outputResultados.setText(resultados);

        // Inserción en la base de datos
        Manager manager = new Manager(this);
        Datos datos = new Datos(velocidadInicial, angulo, 0, alcance, alturaMaxima);
        manager.insertarDatos(datos);
    }

    private void simularMovimiento() {
        parabolicoView.simular(velocidadInicial, angulo);
    }
}