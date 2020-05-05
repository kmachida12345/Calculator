package com.example.calculator

import androidx.lifecycle.ViewModel

class CalcViewModel: ViewModel() {
    var operator: String = ""
    var answer: Double = 0.0
    var operand: Double = 0.0

}