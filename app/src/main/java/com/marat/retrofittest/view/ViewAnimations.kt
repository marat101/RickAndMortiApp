package com.marat.retrofittest.view

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import androidx.cardview.widget.CardView

object ViewAnimations {

    fun cardCorners(corners: Boolean, view: CardView) {
        if (corners) {
            val cornersOn =
                ObjectAnimator.ofFloat(view, "radius", 50.0F)
            cornersOn.duration = 200
            cornersOn.start()
        } else {
            val cornersOff =
                ObjectAnimator.ofFloat(view, "radius", 0F)
            cornersOff.duration = 200
            cornersOff.start()
        }
    }

    fun blackout(blackout: Boolean, view: View) {

        if (blackout) {
            val anim1 = ObjectAnimator.ofFloat(view, "alpha", 0.5F)
            anim1.duration = 200
            anim1.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    view.apply {
                        visibility = View.VISIBLE
                        isClickable = true
                        isFocusable = true
                        isEnabled = true
                    }
                }
            })
            anim1.start()

        } else {
            val anim2 = ObjectAnimator.ofFloat(view, "alpha", 0F)
            anim2.duration = 150
            anim2.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    view.apply {
                        isClickable = false
                        isFocusable = false
                        isEnabled = false
                    }
                }

                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    view.visibility = View.GONE
                }
            })
            anim2.start()
        }
    }

    fun showFilterView(view: View) {
        view.animate()
            .setDuration(150)
            .setListener(object : AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    view.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: Animator) {
                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }

            })
            .translationY(view.height.toFloat() + 12F).start()
    }

    fun hideFilterView(view: View) {
        view.animate()
            .setDuration(150)
            .setListener(object : AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                }

                override fun onAnimationEnd(animation: Animator) {
                    view.visibility = View.INVISIBLE
                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            .translationY(-view.height.toFloat() + 12F).start()
    }

    fun showListAnim(view: View) {
        val anim1 = ObjectAnimator.ofFloat(view, "alpha", 1F)
        anim1.duration = 350
        anim1.start()
    }
}