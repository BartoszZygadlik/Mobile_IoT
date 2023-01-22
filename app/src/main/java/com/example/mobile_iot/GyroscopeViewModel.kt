package com.example.mobile_iot

import androidx.lifecycle.ViewModel

class GyroscopeViewModel: ViewModel() {
    val gyroSapling = 50000
    var seriesX = arrayOf<Number>()
    var seriesY = arrayOf<Number>()
    var seriesZ = arrayOf<Number>()
    var numberOfSamples = arrayOf<Number>()
    var counter = 0
}