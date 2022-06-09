package com.example.kotlin_omdbapi2.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_omdbapi2.R
import java.lang.NullPointerException

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_auth)
    }
}