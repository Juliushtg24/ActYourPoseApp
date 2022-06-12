package com.example.actyourposeapp.screen.predict

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.actyourposeapp.api.ApiConfig
import com.example.actyourposeapp.api.response.FileResponse
import com.example.actyourposeapp.api.response.PoseItem
import com.example.actyourposeapp.api.response.PoseResponseItem
import com.example.actyourposeapp.databinding.ActivityPredictBinding
import com.example.actyourposeapp.screen.adapter.PoseItemAdapter
import com.example.actyourposeapp.screen.result.ResultActivity
import com.example.actyourposeapp.tools.reduceFileImage
import com.example.actyourposeapp.tools.rotateBitmap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PredictActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPredictBinding
    private var getFile: File? = null

    private val predictViewModel by viewModels<PredictViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        predictViewModel.pose.observe(this) {
            setUpPose(it)
        }

        takePhotoImage()
        uploadImage()

        binding.btnExit.setOnClickListener {
            finish()
        }
    }



    private fun takePhotoImage(){


        val myFile = intent.getSerializableExtra(ResultActivity.EXTRA_PICTURE_DATA) as File
        val isBackCamera = intent.getBooleanExtra(ResultActivity.EXTRA_IS_BACK_CAMERA, true)
        getFile = myFile

        getFile = myFile
        val result = rotateBitmap(
            BitmapFactory.decodeFile(getFile?.path),
            isBackCamera
        )

        binding.ivPredictItem.setImageBitmap(result)
    }


    private fun uploadImage() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)

            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                file.name,
                requestImageFile
            )


            val service = ApiConfig.getPredictApi().predictRoom(imageMultipart)

            service.enqueue(object : Callback<FileResponse> {
                override fun onResponse(
                    call: Call<FileResponse>,
                    response: Response<FileResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            binding.tvResultPredict.text = responseBody.message
                            Log.d(this@PredictActivity.toString(), "Predictor : ${responseBody.message}")
                            poseMode(responseBody.message)
                        }
                    } else {
                        Toast.makeText(this@PredictActivity, response.message(), Toast.LENGTH_SHORT).show()
                        Log.d(this@PredictActivity.toString(), "Error ${response.message()}")
                        binding.tvResultPredict.text = "inside"
                        poseMode(binding.tvResultPredict.text.toString())
                    }
                }
                override fun onFailure(call: Call<FileResponse>, t: Throwable) {
                    Toast.makeText(this@PredictActivity, "Retrofit Failed", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this@PredictActivity, "Input Image first", Toast.LENGTH_SHORT).show()
        }
    }


    private fun poseMode(mode: String){
        when (mode) {
            "Inside" -> predictViewModel.getPoseMode("getInside")
            "Outside" -> predictViewModel.getPoseMode("getOutside")
            else -> Toast.makeText(this@PredictActivity, "Error Input Image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpPose(poses: List<PoseItem>){
        val listPoses = ArrayList<String>()
        for (pose in poses){
            val poseUrl = pose.photourl
            Log.d(this@PredictActivity.toString() , "pose url = $poseUrl")
            listPoses.add(poseUrl)
        }

        showPoseList(listPoses)
    }

    private fun showPoseList(listPoses: ArrayList<String>){
        binding.rvPoseResult.adapter = PoseItemAdapter(listPoses)
        binding.rvPoseResult.layoutManager = GridLayoutManager(this , 3)
    }
}