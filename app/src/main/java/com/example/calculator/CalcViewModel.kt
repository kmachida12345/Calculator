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
    // TODO: DI導入
    val calcData = CalcData()

    // 双方向データバインディングの実装
    // _contents を隠蔽するため(？)、
    // publicな変数responseをつくりゲッターをオーバーライドしている
    // そのまま_contentsをlayoutから参照してもOK(publicにすれば)
    private val _contents: MutableLiveData<CalcData> = MutableLiveData()
    val response: LiveData<CalcData>
        get() = _contents

    /**
     * 計算処理
     * 保持している演算子をもとに計算を行う
     * 1つ前の計算結果と入力値を計算し結果を保持する
     * layoutに通知(=そのまま表示される)まで行う
     * */
    private fun calc(){
        Log.d("hoge", "calc() called")
        when (calcData.operator){
            "+" -> calcData.answer = calcData.prevAnswer + calcData.operand.toDouble()
            "-" -> calcData.answer = calcData.prevAnswer - calcData.operand.toDouble()
            "/" -> calcData.answer = calcData.prevAnswer / calcData.operand.toDouble()
            "*" -> calcData.answer = calcData.prevAnswer * calcData.operand.toDouble()
            else -> calcData.answer = calcData.operand.toDouble()
        }
        _contents.postValue(calcData)
        Log.d("hoge", "calc() operand=${calcData.operand},answer=${calcData.prevAnswer}")
    }

    /**
     * 数字ボタンのクリックリスナ
     * リアルタイムで計算結果を表示させるため
     * ボタン押下のたび計算を実行している
     * */
    fun onNumButtonClicked(num: View){
        Log.d("hoge", "onNumButtonClicked() called")
        val button = num as Button

        calcData.formula += button.text.toString()
        calcData.operand += button.text.toString()

        calc()

        Log.d("hoge", "onNumButtonClicked() ${calcData.formula}, ${button.text.toString()}")
    }

    /**
     * 演算子ボタンのクリックリスナ
     * 押下されたとき
     * 1.保持している計算結果を1つ前の結果として保持
     * 2.保持している演算子をリセット
     * */
    fun onOperatorButtonClicked(ope: View){
        Log.d("hoge", "onOperatorButtonClicked() called")
        val button = ope as Button

        calcData.formula += button.text.toString()
        calcData.operator = button.text.toString()

        calcData.operand = "0"
        calcData.prevAnswer = calcData.answer
        Log.d("hoge", "onOperatorButtonClicked() ${calcData.formula}, ${button.text.toString()}")
    }
}