package com.example.geminilauncher

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a dark full-screen container programmatically
        val rootLayout = FrameLayout(this).apply {
            setBackgroundColor(Color.parseColor("#0B0F19"))
        }

        // Create the animated centerpiece icon
        val logoView = ImageView(this).apply {
            setImageResource(android.R.drawable.btn_star_big_on) // Replace with your custom drawable
            scaleX = 0.2f
            scaleY = 0.2f
            alpha = 0f
            layoutParams = FrameLayout.LayoutParams(240, 240).apply {
                gravity = Gravity.CENTER
            }
        }

        rootLayout.addView(logoView)
        setContentView(rootLayout)

        // Play the opening animation (scale up + spin + fade in)
        logoView.animate()
            .scaleX(1.2f)
            .scaleY(1.2f)
            .rotation(180f)
            .alpha(1f)
            .setDuration(550)
            .withEndAction {
                launchGeminiWithTransition()
            }
            .start()
    }

    private fun launchGeminiWithTransition() {
        val geminiPackage = "com.google.android.apps.bard"
        val launchIntent: Intent? = packageManager.getLaunchIntentForPackage(geminiPackage)

        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            // Override the window transition as Gemini opens
            val options = ActivityOptions.makeCustomAnimation(
                this,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )

            startActivity(launchIntent, options.toBundle())
        } else {
            Toast.makeText(this, "Gemini app not found on device", Toast.LENGTH_SHORT).show()
        }

        // Close the wrapper immediately so pressing 'Back' inside Gemini exits cleanly
        finish()
    }
}
