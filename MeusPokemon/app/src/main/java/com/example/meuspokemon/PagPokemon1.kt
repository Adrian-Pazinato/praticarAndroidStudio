package com.example.meuspokemon

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class PagPokemon1 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag_pokemon_1)

        val imagePath = intent.getStringExtra("imagePath")

        val viewPokemon1 = findViewById<ImageView>(R.id.ViewPokemon1)

        if (imagePath != null) {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            viewPokemon1.setImageBitmap(bitmap)
        }
    }
}
