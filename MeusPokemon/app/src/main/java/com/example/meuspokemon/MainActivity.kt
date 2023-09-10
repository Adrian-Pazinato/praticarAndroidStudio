package com.example.meuspokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val bt_inventario = findViewById<Button>(R.id.bt_inventario)
        val bt_pokemon = findViewById<Button>(R.id.bt_pokemon)


        bt_inventario.setOnClickListener {
            startActivity(Intent(this, PagInventario::class.java))
        }

        bt_pokemon.setOnClickListener {
            startActivity(Intent(this, PagPokemon::class.java))
        }


    }
}