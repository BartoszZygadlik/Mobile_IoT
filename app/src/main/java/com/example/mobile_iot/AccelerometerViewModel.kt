package com.example.mobile_iot

import androidx.lifecycle.ViewModel

class AccelerometerViewModel: ViewModel() {
    val AccSapling = 200000 //microseconds 200000 micro = 20 mili  m/s^2
    val AccUnit = "m/s^2"
    var series1Number = arrayOf<Number>(-15,2,3,14,-20,50,2,3,14,-20)
    var domainLabels = arrayOf<Number>(1,2,3,4,5,6,7,8,9,10)
    //array x
    //array y
    //array z



//tutaj będą dane z akcelerometru

}
