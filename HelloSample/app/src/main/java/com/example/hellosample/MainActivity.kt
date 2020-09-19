package com.example.hellosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // リスナ設定
        val listener = HelloListener()
        val btClick = findViewById<Button>(R.id.btClick)
        btClick.setOnClickListener(listener)
        val btClear = findViewById<Button>(R.id.btClear)
        btClear.setOnClickListener(listener)
    }

    // イベントハンドラ生成
    private inner class HelloListener : View.OnClickListener {
        override fun onClick(view: View) {
            val input = findViewById<EditText>(R.id.etName)
            val output = findViewById<TextView>(R.id.tvOutput)

            when (view.id) {
                R.id.btClick -> {
                    var inputStr = input.text.toString()

                    if (inputStr == "") {
                        output.text = "お名前入力して出直してね"
                    } else {
                        output.text = inputStr + "さん、チース！"
                    }
                }
                R.id.btClear -> {
                    input.setText("") // EditTextのtextプロパティはEditable型でStringを入れられない。専用メソッドを入れてtextを変更する
                    output.text = ""
                }
            }
        }
    }
}