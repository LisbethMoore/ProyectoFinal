package com.lisbeth.proyectofinal;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.lisbeth.proyectofinal.model.Datos;
import com.lisbeth.proyectofinal.model.Manager;

import java.util.ArrayList;
import java.util.List;

public class ListarDatos extends AppCompatActivity {
    ListView listViewDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_datos);

        listViewDatos = findViewById(R.id.listViewDatos);

        Manager manager = new Manager(this);
        List<Datos> datosList = manager.listarDatos();

        // Convertimos los datos a formato legible para el ListView
        List<String> datosFormattedList = new ArrayList<>();
        for (Datos datos : datosList) {
            datosFormattedList.add(
                    "ID: " + datos.getId() + "\n" +
                            "Velocidad Inicial: " + datos.getVelocidadInicial() + " m/s\n" +
                            "Ángulo: " + datos.getAngulo() + " grados\n" +
                            "Altura Inicial: " + datos.getAlturaInicial() + " metros\n" +
                            "Alcance: " + datos.getAlcance() + " metros\n" +
                            "Altura Máxima: " + datos.getAlturaMaxima() + " metros"
            );
        }

        // Configuramos el adaptador para mostrar los datos en el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datosFormattedList);
        listViewDatos.setAdapter(adapter);
    }
}