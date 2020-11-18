package com.leapi.forceoom

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forceOOM()
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun forceOOM() {
        val crashIt = ForceOOM(this.application)
    }

}