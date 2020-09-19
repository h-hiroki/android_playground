package com.example.listviewsample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        var menuList = mutableListOf(
                "カレー",
                "からあげ",
                "ハンバーグ",
                "生姜焼き",
                "ステーキ",
                "野菜炒め",
                "とんかつ",
                "チキンカツ",
                "メンチカツ",
                "コロッケ",
                "焼き魚",
                "ごま",
                "焼肉"
        )
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, menuList)
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val dialogFragment = OrderConfirmDialogFragment()
            dialogFragment.show(supportFragmentManager, "OrderConfirmDialogFragment")
        }
    }
}