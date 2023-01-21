package com.example.mobile_iot


import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

        var domainLabels = arrayOf<Number>(1,2,3,4,5,6,7,8,9,10)
        //var series1Number = arrayOf<Number>(50,2,3,14,-20,50,2,3,14,-20)

        val series1 : XYSeries = SimpleXYSeries(Arrays.asList(* AccVm.series1Number),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
        "Series 1 ") //dane
        val series1Format = LineAndPointFormatter(Color.BLUE,Color.RED,null,null)

        binding.AccPlot.addSeries(series1,series1Format)
        binding.AccPlot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format(){
            override fun format(p0: Any?, p1: StringBuffer?, p2: FieldPosition?): StringBuffer {
                val i = Math.round((p0 as Number).toFloat())

                return p1!!.append(domainLabels[i])

            }

            override fun parseObject(p0: String?, p1: ParsePosition?): Any? {
                return null
            }

        }
        PanZoom.attach(binding.AccPlot)
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

            AccVm.series1Number.plus(event.values[0])
            AccVm.series1Number.toMutableList().removeAt(0)

            binding.xAxisTextView.text = xAxis.toString()
            binding.yAxisTextView.text = yAxis.toString()
            binding.zAxisTextView.text = zAxis.toString()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

}