package com.nitroxis.app.quranresearch

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.coroutines.delay
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val moveDown = ObjectAnimator.ofFloat(marketing_container, "translationY", 0f, 50f)
        val moveUp = ObjectAnimator.ofFloat(marketing_container, "translationY", 50f, 0f)
        val fadeDown = ObjectAnimator.ofFloat(marketing_container, "alpha", 1f, 0f)
        val fadeUp = ObjectAnimator.ofFloat(marketing_container, "alpha", 0f, 1f)
        val animDelay = 1000.toLong()
        go_to_next.onClick {
            startActivity<MainActivity>()
            finish()
        }
        AnimatorSet().apply {
            startDelay = animDelay
            playTogether(moveUp, fadeUp)
            start()
            doOnEnd {
                AnimatorSet().apply {
                    startDelay = animDelay
                    playTogether(moveDown, fadeDown)
                    start()
                    doOnEnd {
                        marketing_line_1.text = "from more than"
                        marketing_line_2.text = "100 EDITIONS"
                        AnimatorSet().apply {
                            playTogether(moveUp, fadeUp, ObjectAnimator.ofFloat(
                                go_to_next,
                                "alpha",
                                0f,
                                1f
                            ))
                            start()
                            doOnEnd {
                                AnimatorSet().apply {
                                    startDelay = animDelay
                                    playTogether(moveDown, fadeDown)
                                    start()
                                    doOnEnd {
                                        marketing_line_1.text = "using more than"
                                        marketing_line_2.text = "8 FILTERS"
                                        AnimatorSet().apply {
                                            playTogether(moveUp, fadeUp)
                                            start()
//                                            doOnEnd {
//                                                AnimatorSet().apply {
//                                                    play(
//
//                                                    )
//                                                    start()
//                                                }
//                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


    }
}
