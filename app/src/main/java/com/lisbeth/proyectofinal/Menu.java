package com.lisbeth.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu extends AppCompatActivity {
    Button btnCaso1, btnCaso2, btnListado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnCaso1 = findViewById(R.id.btnCaso1);
        btnCaso2 = findViewById(R.id.btnCaso2);
        btnListado = findViewById(R.id.btnListado);

        btnCaso1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Caso1Activity.class);
                startActivity(intent);
            }
        });

        btnCaso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Caso2Activity.class);
                startActivity(intent);
            }
        });
        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ListarDatos.class);
                startActivity(intent);
            }
        });
    }
}