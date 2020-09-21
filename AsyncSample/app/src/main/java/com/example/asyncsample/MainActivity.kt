package com.example.asyncsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvCityList = findViewById<ListView>(R.id.lvCityList)
        val cityList: MutableList<MutableMap<String, String>> = mutableListOf()

        var city = mutableMapOf("name" to "大阪","id" to "270000")
        cityList.add(city)
        city = mutableMapOf("name" to "神戸", "id" to "280010")
        cityList.add(city)
        city = mutableMapOf("name" to "豊岡", "id" to "280020")
        cityList.add(city)
        city = mutableMapOf("name" to "京都", "id" to "260010")
        cityList.add(city)
        city = mutableMapOf("name" to "舞鶴", "id" to "260020")
        cityList.add(city)
        city = mutableMapOf("name" to "奈良", "id" to "290010")
        cityList.add(city)
        city = mutableMapOf("name" to "風屋", "id" to "290020")
        cityList.add(city)
        city = mutableMapOf("name" to "和歌山", "id" to "300010")
        cityList.add(city)
        city = mutableMapOf("name" to "潮岬", "id" to "300020")
        cityList.add(city)

        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)

        val adapter = SimpleAdapter(applicationContext, cityList, android.R.layout.simple_list_item_1, from, to)
        lvCityList.adapter = adapter
        lvCityList.onItemClickListener = ListItemClickListener()
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as Map<String, String>
            val cityName = item["name"]
            val cityId = item["id"]

            val tvCityName = findViewById<TextView>(R.id.tvCityName)
            tvCityName.text = cityName + "の天気："
        }
    }
}