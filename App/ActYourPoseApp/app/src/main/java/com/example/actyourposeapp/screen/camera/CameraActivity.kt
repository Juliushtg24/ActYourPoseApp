package com.example.actyourposeapp.screen.camera

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actyourposeapp.api.response.PoseResponseItem
import com.example.actyourposeapp.databinding.ActivityCameraBinding
import com.example.actyourposeapp.screen.adapter.PoseItemAdapter
import com.example.actyourposeapp.screen.predict.PredictActivity
import com.example.actyourposeapp.screen.result.ResultActivity
import com.example.actyourposeapp.screen.result.ResultActivity.Companion.CAMERA_X_RESULT
import com.example.actyourposeapp.tools.createFile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding
    private lateinit var cameraExecutor: ExecutorService

    private val cameraViewModel by viewModels<CameraViewModel>()

    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private var imageCapture: ImageCapture? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraExecutor = Executors.newSingleThreadExecutor()

        binding.fabTakePhoto.setOnClickListener { takePhoto() }
        binding.switchCamera.setOnClickListener {
            cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) CameraSelector.DEFAULT_FRONT_CAMERA
            else CameraSelector.DEFAULT_BACK_CAMERA
            startCamera()
        }




        cameraViewModel.pose.observe(this){
            setUpPose(it)
        }


        binding.fabPoseMenu.setOnClickListener {
            setUpPoseLayout()
            // cameraViewModel.getPoseMode("intermediete")
            setModePose()
        }

        binding.ivExitButton.setOnClickListener{
            setUpCameraButton()
        }

        binding.fabPosePredict.setOnClickListener {
            takePhotoPredict()
        }

    }



    public override fun onResume() {
        super.onResume()
        hideSystemUI()
        startCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = createFile(application)

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Toast.makeText(
                        this@CameraActivity,
                        "Failed To Take Image.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val intent = Intent(this@CameraActivity , ResultActivity::class.java)
                    intent.putExtra("picture", photoFile)
                    intent.putExtra(
                        "isBackCamera",
                        cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
                    )
                    setResult(CAMERA_X_RESULT, intent)
                    startActivity(intent)
                    finish()
                }
            }
        )
    }

    private fun takePhotoPredict(){
        val imageCapture = imageCapture ?: return

        val photoFile = createFile(application)

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Toast.makeText(
                        this@CameraActivity,
                        "Failed To Take Image.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val intent = Intent(this@CameraActivity , PredictActivity::class.java)
                    intent.putExtra("picture", photoFile)
                    intent.putExtra(
                        "isBackCamera",
                        cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
                    )
                    setResult(CAMERA_X_RESULT, intent)
                    startActivity(intent)
                }
            }
        )
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )

            } catch (exc: Exception) {
                Toast.makeText(
                    this@CameraActivity,
                    "Failed to Show Camera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setModePose(){
        val auth = Firebase.auth
        val db = Firebase.firestore

        val user = auth.currentUser

        if(user != null) {

            val userData = db.collection("users").document(user.uid)
            userData.get().addOnSuccessListener { document ->
                if(document != null) {
                    val userConfidence = document.get("level")
                    setMode(userConfidence as Long)
                }
            }
        }
    }

    private fun setMode(userConfidence: Long) {
        when {
            userConfidence < 4 -> {
                cameraViewModel.getPoseMode("beginner")
            }
            userConfidence in 4..6 -> {
                cameraViewModel.getPoseMode("intermediete")
            }
            else -> {
                cameraViewModel.getPoseMode("expert")
            }
        }
    }

    private fun setUpPoseLayout(){
        binding.ivExitButton.visibility = View.VISIBLE
        binding.rvPoseList.visibility = View.VISIBLE
        binding.fabTakePhoto.visibility = View.GONE
        binding.fabPoseMenu.visibility = View.GONE
    }

    private fun setUpPose(poses: List<PoseResponseItem>){
        val listPoses = ArrayList<String>()
        for (pose in poses){
            val poseUrl = pose.photoUrl
            Log.d(this@CameraActivity.toString() , "pose url = $poseUrl")
            listPoses.add(poseUrl)
        }

        showPoseList(listPoses)
    }

    private fun showPoseList(listPoses: ArrayList<String>){
        binding.rvPoseList.adapter = PoseItemAdapter(listPoses)
        binding.rvPoseList.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpCameraButton(){
        binding.ivExitButton.visibility = View.GONE
        binding.rvPoseList.visibility = View.GONE
        binding.fabTakePhoto.visibility = View.VISIBLE
        binding.fabPoseMenu.visibility = View.VISIBLE
    }

}