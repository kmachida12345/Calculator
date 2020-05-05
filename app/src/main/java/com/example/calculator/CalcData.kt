package com.example.calculator

data class CalcData(
    var prevAnswer: Double = 0.0,
    var answer: Double = 0.0,
    var operand: String = "0",
    var operator: String = "",
    var formula: String = "aaadaftest"){

    fun addFormula(str: String): String{
        formula += str
        return formula
    }
}