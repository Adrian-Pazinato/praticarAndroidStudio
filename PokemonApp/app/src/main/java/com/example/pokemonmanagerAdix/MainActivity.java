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

    }
}
