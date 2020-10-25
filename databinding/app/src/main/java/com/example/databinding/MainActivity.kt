package com.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initSample(binding)
        initCounter(binding)
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
}