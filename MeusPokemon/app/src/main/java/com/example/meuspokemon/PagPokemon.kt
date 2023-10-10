package com.example.meuspokemon

import android.Manifest
import android.app.Activity
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

        val btn1: Button = findViewById(R.id.add_poke1)
        val btn2: Button = findViewById(R.id.add_poke2)
        val image1: ImageView = findViewById(R.id.imagePoke1)
        val image2: ImageView = findViewById(R.id.imagePoke2)
        requestStoragePermission()

        val savedImage = getImageFromExternalStorage()

        if (savedImage != null) {
            image1.setImageBitmap(savedImage)
        }

        if (savedImage != null) {
            image2.setImageBitmap(savedImage)
        }

        btn1.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        btn2.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 2)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data
            val image1: ImageView = findViewById(R.id.imagePoke1)
            val image2: ImageView = findViewById(R.id.imagePoke2)

            if (uri != null) {
                if (requestCode == 1) {
                    imageUri = uri
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri!!))
                    image1.setImageBitmap(bitmap)
                    saveImageToExternalStorage(bitmap, "image1.jpg")
                } else if (requestCode == 2) {
                    imageUri = uri
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri!!))
                    image2.setImageBitmap(bitmap)
                    saveImageToExternalStorage(bitmap, "image2.jpg")
                }
            }
        }
    }



    private fun saveImageToExternalStorage(bitmap: Bitmap, filename: String) {
        val squareBitmap = resizeBitmapToSquare(bitmap)
        val imageFile = File(getExternalFilesDir(null), filename)
        val outputStream = FileOutputStream(imageFile)
        squareBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.close()
    }

    private fun getImageFromExternalStorage(filename: String): Bitmap? {
        val imageFile = File(getExternalFilesDir(null), filename)
        return if (imageFile.exists()) {
            BitmapFactory.decodeFile(imageFile.absolutePath)
        } else {
            null
        }
    }

// ...



    private fun resizeBitmapToSquare(bitmap: Bitmap): Bitmap {
        val maxSize = Math.min(bitmap.width, bitmap.height)
        val startX = (bitmap.width - maxSize) / 3
        val startY = (bitmap.height - maxSize) / 3
        return Bitmap.createBitmap(bitmap, startX, startY, maxSize, maxSize)
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
            } else {
            }
        }
    }
}
