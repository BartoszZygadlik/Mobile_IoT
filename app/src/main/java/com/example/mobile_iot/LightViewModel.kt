package com.example.mobile_iot

import androidx.lifecycle.ViewModel

class LightViewModel: ViewModel() {
    val lxSapling = 10000 //microseconds 50000 micro = 5 mili  lx
    var seriesLux = arrayOf<Number>()

    var numberOfSamples = arrayOf<Number>()
    var counter = 0

}