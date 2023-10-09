package com.example.meuspokemon

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream

class PagPokemon : AppCompatActivity() {
    private var imageUri: Uri? = null
    private val MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag_pokemon)

        val btn: Button = findViewById(R.id.add_poke1)
        val image: ImageView = findViewById(R.id.imagePoke1)
        requestStoragePermission()

        val savedImage = getImageFromExternalStorage()
        if (savedImage != null) {
            image.setImageBitmap(savedImage)
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
                // Save the selected image and display it
                imageUri = uri
                val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri!!))
                image.setImageBitmap(bitmap)
                saveImageToExternalStorage(bitmap)
            }
        }
    }

    private fun saveImageToExternalStorage(bitmap: Bitmap) {
        val imageFile = File(getExternalFilesDir(null), "image.jpg")
        val outputStream = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.close()
    }

    private fun getImageFromExternalStorage(): Bitmap? {
        val imageFile = File(getExternalFilesDir(null), "image.jpg")
        return if (imageFile.exists()) {
            BitmapFactory.decodeFile(imageFile.absolutePath)
        } else {
            null
        }
    }

    private fun requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida, você pode continuar com as operações de leitura e gravação
            } else {
                // Permissão negada, lidar com situação de permissão não concedida
            }
        }
    }
}
