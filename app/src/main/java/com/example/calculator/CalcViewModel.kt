package com.example.calculator

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalcViewModel: ViewModel() {
    // 変数とかをデータクラスにしてみる
    // このアプリの動作的に、ボタンが押下されるたび
    // このデータクラスが通知されるのだけど
    // パフォーマンス的にどうなのかしら
    // 必要な変数だけLiveDataにするほうがいいのかな
    val calcData = CalcData()

    // 双方向データバインディングの実装
    // _contents を隠蔽するため(？)、
    // publicな変数responseをつくりゲッターをオーバーライドしている
    // そのまま_contentsをlayoutから参照してもOK(publicにすれば)
    private val _contents: MutableLiveData<CalcData> = MutableLiveData()
    val response: LiveData<CalcData>
        get() = _contents


    private fun calc(){
        Log.d("hoge", "calc() called")
        when (calcData.operator){
            "+" -> calcData.tmpAnswer = calcData.answer + calcData.operand.toDouble()
            "-" -> calcData.tmpAnswer = calcData.answer - calcData.operand.toDouble()
            "/" -> calcData.tmpAnswer = calcData.answer / calcData.operand.toDouble()
            "*" -> calcData.tmpAnswer = calcData.answer * calcData.operand.toDouble()
            else -> calcData.tmpAnswer = calcData.operand.toDouble()
        }
        _contents.postValue(calcData)
        Log.d("hoge", "calc() operand=${calcData.operand},answer=${calcData.answer}")
    }

    fun onNumButtonClicked(num: View){
        Log.d("hoge", "onNumButtonClicked() called")
        val button = num as Button

        calcData.formula += button.text.toString()
        calcData.operand += button.text.toString()

        calc()

        Log.d("hoge", "onNumButtonClicked() ${calcData.formula}, ${button.text.toString()}")
    }

    fun onOperatorButtonClicked(ope: View){
        Log.d("hoge", "onOperatorButtonClicked() called")
        val button = ope as Button

        calcData.formula += button.text.toString()
        calcData.operator = button.text.toString()

        calcData.operand = "0"
        calcData.answer = calcData.tmpAnswer
        Log.d("hoge", "onOperatorButtonClicked() ${calcData.formula}, ${button.text.toString()}")
    }
}