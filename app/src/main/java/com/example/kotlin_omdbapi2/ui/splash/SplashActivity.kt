package com.example.kotlin_omdbapi2.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_omdbapi2.R
import com.example.kotlin_omdbapi2.ui.auth.AuthActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash)

        val timerThread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                   // val intent = Intent(this@SplashActivity, BottomNavigationBarActivity::class.java)
                    val intent = Intent(this@SplashActivity, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        timerThread.start()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}