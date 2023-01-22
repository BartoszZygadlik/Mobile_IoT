package com.example.mobile_iot

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mobile_iot.databinding.ActivityAccelerometerBinding
import com.example.mobile_iot.databinding.ActivityLightBinding

class Light : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityLightBinding
    private lateinit var sensorManager: SensorManager
    private val lxVm by viewModels<LightViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSensor()

    }
    private fun setUpSensor() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)?.also {
            sensorManager.registerListener(
                this,
                it,
                lxVm.lxSapling
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val lux = event.values[0]// lx


            lxVm.counter += 1
            lxVm.seriesLux.plus(lux)

            lxVm.numberOfSamples.plus(lxVm.counter)
            if(lux<10){
                binding.lightTopLayout.setBackgroundColor(Color.BLACK)
            }else{
                binding.lightTopLayout.setBackgroundColor(Color.BLACK)
            }

            binding.luxValue.text = lux.toString()

        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }
}