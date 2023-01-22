package com.example.mobile_iot

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_iot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainVm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonAcc.setOnClickListener{
            val explicitIntent = Intent(applicationContext,Accelerometer::class.java)
            startActivity(explicitIntent)
        }
        binding.buttonGyro.setOnClickListener{
            val explicitIntent = Intent(applicationContext,Gyroscope::class.java)
            startActivity(explicitIntent)
        }
        binding.buttonLight.setOnClickListener{
            val explicitIntent = Intent(applicationContext,Light::class.java)
            startActivity(explicitIntent)
        }

    }

}