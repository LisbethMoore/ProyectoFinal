    package com.lisbeth.proyectofinal;


    import android.os.Bundle;
    import android.widget.Button;
    import android.widget.SeekBar;
    import android.widget.TextView;

    import androidx.appcompat.app.AppCompatActivity;

    import com.lisbeth.proyectofinal.model.Datos;
    import com.lisbeth.proyectofinal.model.Manager;

    import java.text.DecimalFormat;

    public class Caso2Activity extends AppCompatActivity {
        SeekBar seekBarVelocidad, seekBarAngulo, seekBarAlturaInicial;
        TextView textVelocidad, textAngulo, textAlturaInicial, outputResultados;
        Button btnCalcular, btnSimular;
        ParabolicoView parabolicoView;

        private double velocidadInicial = 0;
        private double angulo = 0;
        private double alturaInicial = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_caso2);

            seekBarVelocidad = findViewById(R.id.seekBarVelocidad);
            seekBarAngulo = findViewById(R.id.seekBarAngulo);
            seekBarAlturaInicial = findViewById(R.id.seekBarAlturaInicial);
            textVelocidad = findViewById(R.id.textVelocidad);
            textAngulo = findViewById(R.id.textAngulo);
            textAlturaInicial = findViewById(R.id.textAlturaInicial);
            btnCalcular = findViewById(R.id.btnCalcular);
            btnSimular = findViewById(R.id.btnSimular);
            outputResultados = findViewById(R.id.outputResultados);
            parabolicoView = findViewById(R.id.parabolicoView);

            seekBarVelocidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                //metodo que se ejecuta cada vez que el usuario cambia el valor del SeekBar
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // asigna el valor seleccionado del SeekBar a la variable vo
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

            seekBarAlturaInicial.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    alturaInicial = progress;
                    textAlturaInicial.setText("Altura inicial: " + alturaInicial + " metros");
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
            double anguloRad = Math.toRadians(angulo); //convierte el valor del angulo de grados a radianes
            double g = 9.81;

            double alcance = (velocidadInicial * Math.cos(anguloRad)) *
                    (velocidadInicial * Math.sin(anguloRad) + Math.sqrt(Math.pow(velocidadInicial * Math.sin(anguloRad), 2) + 2 * g * alturaInicial)) / g;

            double alturaMaxima = alturaInicial + Math.pow(velocidadInicial * Math.sin(anguloRad), 2) / (2 * g);

            // Limitara el numero de decimales mostrados, los resultados seran mostrados con dos decimales
            DecimalFormat df = new DecimalFormat("#.##");
            String resultados = "Alcance: " + df.format(alcance) + " metros\n" +
                    "Altura máxima: " + df.format(alturaMaxima) + " metros";

            //Muestra los resultados
            outputResultados.setText(resultados);

            // Inserción en la base de datos
            Manager manager = new Manager(this);
            Datos datos = new Datos(velocidadInicial, angulo, alturaInicial, alcance, alturaMaxima);
            manager.insertarDatos(datos);
        }
        //Metodo que inicia la simulacion del mp
        private void simularMovimiento() {
            parabolicoView.simular(velocidadInicial, angulo, alturaInicial);
        }
    }