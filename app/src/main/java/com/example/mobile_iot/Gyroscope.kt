package com.example.mobile_iot

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mobile_iot.databinding.ActivityAccelerometerBinding
import com.example.mobile_iot.databinding.ActivityGyroscopeBinding
import kotlin.math.roundToInt

class Gyroscope : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityGyroscopeBinding
    private lateinit var sensorManager: SensorManager
    private val GyroVm by viewModels<GyroscopeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGyroscopeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSensor()


    }

    private fun setUpSensor() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)?.also {
            sensorManager.registerListener(this,it,GyroVm.gyroSapling)

        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            val xAxis = event.values[0]
            val yAxis = event.values[1]
            val zAxis = event.values[2]

            GyroVm.seriesX.plus(xAxis)
            GyroVm.seriesY.plus(yAxis)
            GyroVm.seriesZ.plus(zAxis)

            binding.xAxisGyro.text = xAxis.toString()
            binding.yAxisGyro.text = yAxis.toString()
            binding.zAxisGyro.text = zAxis.toString()
        }
    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

}


