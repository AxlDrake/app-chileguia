package com.brouter.app_chileguia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.brouter.app_chileguia.databinding.ActivitySplashScreenBinding

@Suppress("DEPRECATION")
class Splash_Screen : AppCompatActivity() {

    private val SPLASH_TIME : Long = 3000
    private val version = BuildConfig.VERSION_NAME
    private lateinit var binding:ActivitySplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtVersion.text = "v$version"

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME)
    }
}