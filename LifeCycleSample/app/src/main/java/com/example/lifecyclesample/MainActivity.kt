package com.example.lifecyclesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG_CONTEXT: String = "LifeCycleSample"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG_CONTEXT, "Main onCreate() called.")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        Log.i(TAG_CONTEXT, "Main onStart() called.")
        super.onStart()
    }

    public override fun onRestart() {
        Log.i(TAG_CONTEXT, "Main onRestart() called.")
        super.onRestart()
    }

    public override fun onResume() {
        Log.i(TAG_CONTEXT, "Main onResume() called.")
        super.onResume()
    }

    public override fun onPause() {
        Log.i(TAG_CONTEXT, "Main onPause() called.")
        super.onPause()
    }

    public override fun onStop() {
        Log.i(TAG_CONTEXT, "Main onStop() called.")
        super.onStop()
    }

    public override fun onDestroy() {
        Log.i(TAG_CONTEXT, "Main onDestroy() called.")
        super.onDestroy()
    }

    fun onButtonClick(view: View) {
        val intent = Intent(applicationContext, SubActivity::class.java)
        startActivity(intent)
    }
}