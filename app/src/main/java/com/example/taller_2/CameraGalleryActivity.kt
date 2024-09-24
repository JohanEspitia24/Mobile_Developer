package com.example.taller_2

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller.databinding.ActivityCameraGalleryBinding

class CameraGalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraGalleryBinding
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_VIDEO_CAPTURE = 2
    private val REQUEST_GALLERY_PICK = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCapture.setOnClickListener {
            if (binding.switchVideo.isChecked) {
                captureVideo()
            } else {
                capturePhoto()
            }
        }

        binding.btnSelect.setOnClickListener {
            pickFromGallery()
        }
    }

    private fun capturePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    private fun captureVideo() {
        val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
    }

    private fun pickFromGallery() {
        val pickPhotoIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhotoIntent, REQUEST_GALLERY_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data.extras?.get("data") as Bitmap
                    binding.imageView.setImageBitmap(imageBitmap)
                    binding.videoView.visibility = GONE
                    binding.imageView.visibility = VISIBLE
                }
                REQUEST_VIDEO_CAPTURE -> {
                    val videoUri = data.data
                    binding.videoView.setVideoURI(videoUri)
                    binding.imageView.visibility = GONE
                    binding.videoView.visibility = VISIBLE
                    binding.videoView.start()
                }
                REQUEST_GALLERY_PICK -> {
                    val imageUri: Uri = data.data!!
                    binding.imageView.setImageURI(imageUri)
                    binding.videoView.visibility = GONE
                    binding.imageView.visibility = VISIBLE
                }
            }
        } else {
            Toast.makeText(this, "Error al capturar/seleccionar imagen/video", Toast.LENGTH_SHORT).show()
        }
    }
}
