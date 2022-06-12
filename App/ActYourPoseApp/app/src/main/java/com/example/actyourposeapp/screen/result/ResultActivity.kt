package com.example.actyourposeapp.screen.result

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.actyourposeapp.api.ApiConfig
import com.example.actyourposeapp.api.response.FileResponse
import com.example.actyourposeapp.databinding.ActivityResultBinding
import com.example.actyourposeapp.tools.reduceFileImage
import com.example.actyourposeapp.tools.rotateBitmap
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

@Suppress("DEPRECATION")
class ResultActivity : AppCompatActivity() {


    private lateinit var binding : ActivityResultBinding
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)



        takePhotoImage()

        binding.btnBackToMenu.setOnClickListener {
            finish()
        }


        binding.btnSaveGallery.setOnClickListener {
            val myFile = intent.getSerializableExtra(EXTRA_PICTURE_DATA) as File

            Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
                mediaScanIntent.data = Uri.fromFile(myFile)
                sendBroadcast(mediaScanIntent)
                Toast.makeText(this@ResultActivity, "Take Camera Success", Toast.LENGTH_SHORT).show()
            }
            finish()
        }

    }

    companion object {
        const val CAMERA_X_RESULT = 200
        const val EXTRA_PICTURE_DATA = "picture"
        const val EXTRA_IS_BACK_CAMERA = "isBackCamera"
    }

    private fun takePhotoImage(){


        val myFile = intent.getSerializableExtra(EXTRA_PICTURE_DATA) as File
        val isBackCamera = intent.getBooleanExtra(EXTRA_IS_BACK_CAMERA, true)
        getFile = myFile

        getFile = myFile
        val result = rotateBitmap(
            BitmapFactory.decodeFile(getFile?.path),
            isBackCamera
        )

        binding.imageView6.setImageBitmap(result)
    }

}