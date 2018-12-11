package com.example.jan.termprojectapp

import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_color_game.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ObjectAnimator.ofFloat(MainActivity_tv1, "translationY", -700f).apply {
            duration = 3000
            start()
        }
        Glide.with(this).load(R.drawable.oneplusone).into(MainActivity_imageViewOnePlusOne)
        Glide.with(this).load(R.drawable.redornot).into(MainActivity_imageViewColorGame)
        MainActivity_imageViewOnePlusOne.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_animation))
        MainActivity_imageViewColorGame.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_animation))

        MainActivity_imageViewOnePlusOne.setOnClickListener {
            val intent = Intent(this, TermCalculationGame::class.java)
            startActivity(intent)
        }

        MainActivity_imageViewColorGame.setOnClickListener {
            val intent = Intent(this, ColorGame::class.java)
            startActivity(intent)
        }

    }
}
