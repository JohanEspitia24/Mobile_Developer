package com.example.taller2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnImage = findViewById<ImageView>(R.id.btnImage)
        val btnMap = findViewById<ImageView>(R.id.btnMap)

        // Lanzar la actividad de galería y cámara
        btnImage.setOnClickListener {
            val intent = Intent(this, ImageVideoActivity::class.java)
            startActivity(intent)
        }

        // Lanzar la actividad de mapa
        btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }
}
