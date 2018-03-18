package io.mbition.github.ui.activities

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import io.mbition.github.R
import io.mbition.github.component
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by Tohamy on 3/15/2018.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setStatusBarColor()
        startAnimation()
    }

    private fun startAnimation() {
        val set = AnimatorSet()
        val animator5 = ObjectAnimator.ofFloat(logo, "alpha", 0f, 1f)
        animator5.repeatCount = 0
        animator5.duration = 1000
        val animator6 = ObjectAnimator.ofFloat(logo, "scaleX", 0f, 1f)
        animator6.repeatCount = 0
        animator6.duration = 1000
        val animator7 = ObjectAnimator.ofFloat(logo, "scaleY", 0f, 1f)
        animator7.repeatCount = 0
        animator7.duration = 1000
        set.playTogether(animator5, animator6, animator7)
        set.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                component.navigationControl.navigateToHome(this@SplashActivity)
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
        set.start()
    }

    private fun setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this@SplashActivity, R.color.colorPrimaryDark)
        }
    }

}


