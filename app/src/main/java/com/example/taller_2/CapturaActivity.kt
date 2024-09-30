package com.example.taller_2

import android.R
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.ToggleButton
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class CapturaActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var videoView: VideoView
    private lateinit var toggleVideo: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_captura)

        imageView = findViewById(R.id.imageView)
        videoView = findViewById(R.id.videoView)
        toggleVideo = findViewById(R.id.toggleVideo)

        val intent = if (toggleVideo.isChecked) {
            Intent(MediaStore.ACTION_VIDEO_CAPTURE) // Capturar video
        } else {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE) // Capturar foto
        }

        startActivityForResult(intent, 1)
    }

    // Procesar el resultado (mostrar foto o video)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val uri = data?.data
            if (toggleVideo.isChecked) {
                videoView.setVideoURI(uri)
                videoView.start()
            } else {
                imageView.setImageURI(uri)
            }
        }
    }
}
