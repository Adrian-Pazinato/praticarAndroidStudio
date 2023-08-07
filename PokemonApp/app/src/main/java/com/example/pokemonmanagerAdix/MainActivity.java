package com.example.pokemonmanagerAdix;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPokemon1 = findViewById(R.id.btnPokemon1);
        Button btnPokemon2 = findViewById(R.id.btnPokemon2);

        btnPokemon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pokemon1Activity.class);
                startActivity(intent);
            }
        });

        btnPokemon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pokemon2Activity.class);
                startActivity(intent);
            }
        });
    }
}
