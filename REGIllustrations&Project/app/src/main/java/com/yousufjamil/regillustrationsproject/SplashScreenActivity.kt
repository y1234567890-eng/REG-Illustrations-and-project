package com.yousufjamil.regillustrationsproject

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.LinearLayout

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val splashLaunchAppName: LinearLayout = findViewById(R.id.startupHolderLayout)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        splashLaunchAppName.startAnimation(slideAnimation)

        Handler().postDelayed({
            val connectionManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            if (isConnected) {
                val finishLoadingIntent = Intent(this, MainActivity::class.java)
                startActivity(finishLoadingIntent)
                finish()
            } else {
                val internetIssueIntent = Intent(this, InternetProblemActivity::class.java)
                startActivity(internetIssueIntent)
                finish()
            }
        }, 3000)
    }
}