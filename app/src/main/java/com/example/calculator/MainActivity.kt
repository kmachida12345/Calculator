package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var calcViewModel: CalcViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewModelを取得
        // おまじない的コード
        // ownerに指定したactivityにViewModelをひもづけ＆ViewModel取得
        calcViewModel = ViewModelProvider(this).get(CalcViewModel::class.java)

        // でーたばいんでぃんぐの設定
        // おまじない的コード
        // バインド先のレイアウトを指定
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // バインド先のレイアウトが参照する変数の指定
        binding.viewModel = calcViewModel
        // LiveDataと連携させる(双方向データバインディング実現)のため
        // レイアウトが影響されるライフサイクルを指定
        binding.lifecycleOwner = this
    }
}
