package com.example.meuspokemon

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class PagPokemon : AppCompatActivity() {
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag_pokemon)

        val btn: Button = findViewById(R.id.add_poke1)
        val image: ImageView = findViewById(R.id.imagePoke1)
        val savedImageUri = getSavedImageUri()
        if (savedImageUri != null) {
            imageUri = savedImageUri
            image.setImageURI(imageUri)
        }

        btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            val uri = data?.data
            val image: ImageView = findViewById(R.id.imagePoke1)

            if (uri != null) {
                // Save the selected image URI and display it
                imageUri = uri
                image.setImageURI(imageUri)
                saveImageUri(imageUri!!)
            }
        }
    }

    private fun saveImageUri(uri: Uri) {
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("imageUri", uri.toString())
        editor.apply()
    }

    private fun getSavedImageUri(): Uri? {
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val uriString = sharedPref.getString("imageUri", null)
        return uriString?.let { Uri.parse(it) }
    }
}