package com.forientsystem.staff.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.forientsystem.staff.R
import com.forientsystem.staff.common.utils.AppSharedPreferences

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val preferences = AppSharedPreferences(this)
        with(preferences) {
            if (userId.isBlank() || username.isBlank() || password.isBlank()) {

                Handler().postDelayed({
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }, 3000)
            } else {
                startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
                finish()
            }
        }

    }
}
