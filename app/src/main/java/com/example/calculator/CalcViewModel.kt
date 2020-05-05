package com.example.calculator

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.view.*

class CalcViewModel: ViewModel() {
    var answer: Double = 0.0
    var operand: String = "0"
    var operator: String = ""
    var formula: String = "aaaa"

    fun calc(){
        when (operator){
            "+" -> answer += operand.toDouble()
            "-" -> answer -= operand.toDouble()
            "/" -> answer /= operand.toDouble()
            "*" -> answer *= operand.toDouble()
            else -> answer = operand.toDouble()
        }
    }

    fun onNumButtonClicked(num: View){
        val button = num as Button
        formula = button.text.toString()
        calc()
        Log.d("hoge", "onNumButtonClicked() called $num$formula${button.text}")
    }

    fun onOperatorButtonClicked(ope: View){
        formula += ope
        operator = ope.toString()
    }
}