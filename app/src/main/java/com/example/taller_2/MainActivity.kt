package com.example.taller_2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCapturar = findViewById<Button>(R.id.btnCapturar)
        val btnEscoger = findViewById<Button>(R.id.btnEscoger)

        // Pedir permisos de ubicación y cámara
        if (!tienePermisos()) {
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
            ), 1)
        }

        btnCapturar.setOnClickListener {
            // Lanzar actividad para capturar imágenes o videos
            val intent = Intent(this, CapturaActivity::class.java)
            startActivity(intent)
        }

        btnEscoger.setOnClickListener {
            // Lanzar actividad para escoger imágenes o videos
            val intent = Intent(this, GaleriaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun tienePermisos(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }
}
