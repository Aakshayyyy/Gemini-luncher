package com.example.geminilauncher

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootLayout = FrameLayout(this).apply {
            setBackgroundColor(Color.BLACK)
        }

        val videoView = VideoView(this).apply {
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                gravity = Gravity.CENTER
            }
            
            // Point to app/src/main/res/raw/intro.mp4
            val videoUri = Uri.parse("android.resource://$packageName/${R.raw.intro}")
            setVideoURI(videoUri)

            // Launch Gemini as soon as the video finishes
            setOnCompletionListener {
                launchGeminiWithTransition()
            }

            // Fallback: if the video fails to play, still open Gemini
            setOnErrorListener { _, _, _ ->
                launchGeminiWithTransition()
                true
            }
        }

        rootLayout.addView(videoView)
        setContentView(rootLayout)

        videoView.start()
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
