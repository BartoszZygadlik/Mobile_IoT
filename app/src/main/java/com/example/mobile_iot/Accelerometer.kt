package com.example.mobile_iot


import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.androidplot.Plot
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.PanZoom
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.XYGraphWidget
import com.androidplot.xy.XYSeries

import com.example.mobile_iot.databinding.ActivityAccelerometerBinding
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*

class Accelerometer : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityAccelerometerBinding
    private lateinit var sensorManager: SensorManager
    private val AccVm by viewModels<AccelerometerViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccelerometerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSensor()

    }

    private fun setUpSensor() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
            sensorManager.registerListener(
                this,
                it,
                AccVm.AccSapling
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val xAxis = event.values[0]// m/s^2
            val yAxis = event.values[1]
            val zAxis = event.values[2]

            AccVm.counter += 1
            AccVm.series1Number.plus(event.values[0])
            AccVm.seriesY.plus(yAxis)
            AccVm.seriesZ.plus(zAxis)
            AccVm.numberOfSamples.plus(AccVm.counter)

            binding.xAxisTextView.text = xAxis.toString()
            binding.yAxisTextView.text = yAxis.toString()
            binding.zAxisTextView.text = zAxis.toString()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

}