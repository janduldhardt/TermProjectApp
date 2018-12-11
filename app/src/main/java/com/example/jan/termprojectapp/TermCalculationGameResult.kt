package com.example.jan.termprojectapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_term_calculation_game_result.*

class TermCalculationGameResult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_calculation_game_result)

        val score = intent.getStringExtra(EXTRA_Message)

        calcResult_tv_Score.setText(score.toString())

        calcResult_btn_playAgain.setOnClickListener {
            val intent = Intent(this@TermCalculationGameResult, TermCalculationGame::class.java)
            startActivity(intent)
        }

        calcResult_btn_MainMenu.setOnClickListener {
            val intent = Intent(this@TermCalculationGameResult, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
