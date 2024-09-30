package com.example.taller_2

import android.R
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.ToggleButton
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class GaleriaActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var videoView: VideoView
    private lateinit var toggleVideo: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria)

        imageView = findViewById(R.id.imageView)
        videoView = findViewById(R.id.videoView)
        toggleVideo = findViewById(R.id.toggleVideo)

        val intent = if (toggleVideo.isChecked) {
            Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI) // Escoger video
        } else {
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI) // Escoger foto
        }

        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 2 && resultCode == RESULT_OK) {
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
