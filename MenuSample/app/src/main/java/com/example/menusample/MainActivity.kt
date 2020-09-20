package com.example.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {
    private var _menuList: MutableList<MutableMap<String, Any>>? = null
    private val FROM = arrayOf("name", "price")
    private val TO = intArrayOf(R.id.tvMenuName, R.id.tvMenuPrice)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _menuList = createTeishokuList()
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(applicationContext, _menuList, R.layout.row, FROM, TO)
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuListOptionTeishoku -> _menuList = createTeishokuList()
            R.id.menuListOptionCurry -> _menuList = createCurryList()
        }

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(applicationContext, _menuList, R.layout.row, FROM, TO)
        lvMenu.adapter = adapter
        return super.onOptionsItemSelected(item)
    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var menu = mutableMapOf("name" to "からあげ定食", "price" to 800, "desc" to "からあげにサラダ、ご飯、スープつくよ。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to 850, "desc" to "ハンバーグにサラダ、ご飯、スープつくよ。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "カキフライ定食", "price" to 850, "desc" to "カキフライとご飯だけ。")
        menuList.add(menu)
        return menuList
    }

    private fun createCurryList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var menu = mutableMapOf("name" to "からあげカレー", "price" to 800, "desc" to "からあげonカレー")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ハンバーグカレー", "price" to 850, "desc" to "ハンバーグonカレー")
        menuList.add(menu)
        menu = mutableMapOf("name" to "カキフライカレー", "price" to 850, "desc" to "カキフライonカレー")
        menuList.add(menu)
        return menuList
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, Any>
            val menuName = item["name"] as String
            val menuPrice = item["price"] as Int
            val intent = Intent(applicationContext, MenuThanksActivity::class.java)
            intent.putExtra("menuName", menuName)
            intent.putExtra("menuPrice", "${menuPrice}円")
            startActivity(intent)
        }
    }
}