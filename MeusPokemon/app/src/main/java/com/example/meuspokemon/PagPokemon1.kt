package com.example.meuspokemon

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class PagPokemon1 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag_pokemon_1)

        val imageUriString = intent.getStringExtra("imageUri")
        val imagePoke1: ImageView = findViewById(R.id.imagePoke1)

        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            imagePoke1.setImageURI(imageUri)
        }
    }
    }
