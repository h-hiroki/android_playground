package com.example.databinding

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.databinding.ListItemBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initSample(binding)
        initCounter(binding)
        initEditTextConfirm(binding)
        initListView(binding)
    }

    private fun initSample(binding: ActivityMainBinding) {
        binding.sampleTextView.text = "Hello init Binding!"
        binding.bindbutton.setOnClickListener {
            binding.sampleTextView.text = "Button bind success!!"
        }
    }

    private fun initCounter(binding: ActivityMainBinding) {
        binding.counter = 1
        binding.countUpButton.setOnClickListener {
            binding.counter += 1
        }
    }

    private fun initEditTextConfirm(binding: ActivityMainBinding) {
        binding.confirmButton.setOnClickListener {
            Toast.makeText(this, binding.inputText, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListView(binding: ActivityMainBinding) {
        val adapter = MainListAdapter(this).apply {
            add(ListItem("ゴミ出し", Date()))
            add(ListItem("掃除", Date()))
            add(ListItem("ご飯を食べる", Date()))
        }

        binding.listView.adapter = adapter
    }

    class MainListAdapter(context: Context): ArrayAdapter<ListItem>(context, 0) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val binding: ListItemBinding
            if (convertView == null) {
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.list_item,
                    parent,
                    false
                )
                binding.root.tag = binding
            } else {
                binding = convertView.tag as ListItemBinding
            }

            binding.item = getItem(position)
            return binding.root
        }
    }
}