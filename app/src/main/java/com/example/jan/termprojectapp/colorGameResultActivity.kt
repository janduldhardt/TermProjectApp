package com.example.jan.termprojectapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_color_game_result.*

class colorGameResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_game_result)

        val score = intent.getStringExtra(EXTRA_Message3)

        colorGameResult_tv_Score.setText(score.toString())

        colorGameResult_btn_playAgain.setOnClickListener {
            val intent = Intent(this, ColorGame::class.java)
            startActivity(intent)
        }

       colorGameResult_btn_MainMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
    override fun onBackPressed() {
        val intent = Intent(this@colorGameResultActivity, MainActivity::class.java)
        startActivity(intent)
    }
}
