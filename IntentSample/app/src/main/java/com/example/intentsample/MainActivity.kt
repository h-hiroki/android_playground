package com.example.intentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY_NAME  = "name"
        const val KEY_PRICE = "price"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        var menu = mutableMapOf(KEY_NAME to "からあげ定食", KEY_PRICE to "800円")
        menuList.add(menu)
        menu = mutableMapOf(KEY_NAME to "ハンバーグ師匠", KEY_PRICE to "850円")
        menuList.add(menu)
        menu = mutableMapOf(KEY_NAME to "レバニラ定食", KEY_PRICE to "900円")
        menuList.add(menu)

        val from = arrayOf(KEY_NAME, KEY_PRICE)
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        val adapter = SimpleAdapter(applicationContext, menuList, android.R.layout.simple_list_item_2, from, to)
        lvMenu.adapter = adapter
    }
}