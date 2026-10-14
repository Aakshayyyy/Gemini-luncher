package com.example.geminilauncher

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Transparent or dark background (change to Color.TRANSPARENT for floating overlay)
        val rootLayout = FrameLayout(this).apply {
            setBackgroundColor(Color.parseColor("#0B0F19"))
        }

        val lottieView = LottieAnimationView(this).apply {
            layoutParams = FrameLayout.LayoutParams(600, 600).apply {
                gravity = Gravity.CENTER
            }
            
            // Load app/src/main/res/raw/intro_anim.json
            setAnimation(R.raw.intro_anim)
            repeatCount = 0 // Play once
            speed = 1.2f    // Adjust playback speed if needed

            addAnimatorListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    launchGeminiWithTransition()
                }
            })
        }

        rootLayout.addView(lottieView)
        setContentView(rootLayout)

        lottieView.playAnimation()
    }

    private fun launchGeminiWithTransition() {
        val geminiPackage = "com.google.android.apps.bard"
        val launchIntent: Intent? = packageManager.getLaunchIntentForPackage(geminiPackage)

        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val options = ActivityOptions.makeCustomAnimation(
                this,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            startActivity(launchIntent, options.toBundle())
        } else {
            Toast.makeText(this, "Gemini app not found", Toast.LENGTH_SHORT).show()
        }

        finish()
    }
}
