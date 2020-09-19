package com.example.lifecyclesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class SubActivity : AppCompatActivity() {
    companion object {
        const val TAG_CONTEXT: String = "LifeCycleSample"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG_CONTEXT, "Sub onCreate() called.")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
    }

    public override fun onStart() {
        Log.i(TAG_CONTEXT, "Sub onStart() called.")
        super.onStart()
    }

    public override fun onRestart() {
        Log.i(TAG_CONTEXT, "Sub onRestart() called.")
        super.onRestart()
    }

    public override fun onResume() {
        Log.i(TAG_CONTEXT, "Sub onResume() called.")
        super.onResume()
    }

    public override fun onPause() {
        Log.i(TAG_CONTEXT, "Sub onPause() called.")
        super.onPause()
    }

    public override fun onStop() {
        Log.i(TAG_CONTEXT, "Sub onStop() called.")
        super.onStop()
    }

    public override fun onDestroy() {
        Log.i(TAG_CONTEXT, "Sub onDestroy() called.")
        super.onDestroy()
    }

    fun onButtonClick(view: View) {
        finish()
    }
}