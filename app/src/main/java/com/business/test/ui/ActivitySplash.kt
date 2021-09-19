package com.business.test.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.business.test.R
import kotlinx.coroutines.delay

class ActivitySplash : AppCompatActivity() {
    lateinit var handler:Handler
    lateinit var i: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        i=Intent(this,MainActivity::class.java)
        i.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        supportActionBar?.hide()
        handler= Handler(mainLooper)
        

    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed({
            startActivity(i)
        },1500)
    }

}