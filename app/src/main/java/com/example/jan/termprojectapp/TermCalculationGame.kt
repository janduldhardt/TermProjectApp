package com.example.jan.termprojectapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_term_calculation_game.*
import java.util.*
import kotlin.concurrent.timer
import android.support.v4.os.HandlerCompat.postDelayed



const val EXTRA_Message = "com.example.jan.termprojectapp.Score"
const val EXTRA_Message2 = "com.example.jan.termprojectapp.NumberQuestions"


class TermCalculationGame : AppCompatActivity() {

    var answers = ArrayList<Int>()
    var score = 0;
    var numberOfQuestions = 0;
    var locationCorrectAnswer = 0;
    var sumAnswer = 0;
    var timerFinished : Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_calculation_game)
        calcGame_btn_1.setTag(0)
        calcGame_btn_2.setTag(1)
        calcGame_btn_3.setTag(2)
        calcGame_btn_4.setTag(3)


        calcGame_tv_score.setText("$score / $numberOfQuestions")
        gameTimer()
        generateQuestion()



    }

    fun chooseAnswer(v : View) {
        if (!timerFinished) {
            if (v.getTag().toString().equals(locationCorrectAnswer.toString())) {
                generateQuestion()
                score++
                numberOfQuestions++
                calcGame_tv_score.setText("$score / $numberOfQuestions")
//            textView2.setText("Correct")
//            textView2.setTextColor(Color.parseColor("#2ac075"))

            } else {
                generateQuestion()
                numberOfQuestions++
                calcGame_tv_score.setText("$score / $numberOfQuestions")
//            textView2.setText("Wrong")
//            textView2.setTextColor(Color.parseColor("#f06261"))
            }
        }


    }

    fun generateQuestion() {
        answers.clear()
        val rand = Random()
        var operationNo = rand.nextInt(4)
        if (operationNo == 0) {
            additionQuestion()
        }
        else if (operationNo == 1) {
            subtractionQuestion()
        }
        else if (operationNo == 2) {
            multiQuestion()
        }
        else if (operationNo == 3) {
            divideQuestion()
        }

    }

    fun gameTimer() {
        object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                timerFinished = true
                calcGame_tv_Question.setText("Finish")
                startResultActivity()
            }
        }.start()
    }

    fun startResultActivity() {
        intent = Intent(this@TermCalculationGame, TermCalculationGameResult::class.java)
                .apply {
                    putExtra(EXTRA_Message, score.toString())
                }
        startActivity(intent)
    }


    /**********************Functions to get the Questions************************/

    fun additionQuestion() {
        val rand = Random()

        var a = rand.nextInt(21)
        var b = rand.nextInt(21)

        sumAnswer = a + b
        calcGame_tv_Question.setText("$a + $b")
        locationCorrectAnswer = rand.nextInt(4)

        for (i in 0..3) {
            if (i == locationCorrectAnswer) {
                answers.add(sumAnswer)
            } else {
                var incorrectAnswer = rand.nextInt(41)
                while (incorrectAnswer == sumAnswer) {
                    incorrectAnswer = rand.nextInt(41)

                }
                answers.add(incorrectAnswer)
            }

        }
        calcGame_btn_1.setText(Integer.toString(answers.get(0)))
        calcGame_btn_2.setText(Integer.toString(answers.get(1)))
        calcGame_btn_3.setText(Integer.toString(answers.get(2)))
        calcGame_btn_4.setText(Integer.toString(answers.get(3)))
    }

    fun subtractionQuestion() {
        val rand = Random()

        var a = rand.nextInt(21)
        var b = rand.nextInt(21)

        sumAnswer = a - b
        calcGame_tv_Question.setText("$a - $b")
        locationCorrectAnswer = rand.nextInt(4)

        for (i in 0..3) {
            if (i == locationCorrectAnswer) {
                answers.add(sumAnswer)
            } else {
                var incorrectAnswer = rand.nextInt(81) - 40
                while (incorrectAnswer == sumAnswer) {
                    incorrectAnswer = rand.nextInt(81) -40

                }
                answers.add(incorrectAnswer)
            }
        }
        calcGame_btn_1.setText(Integer.toString(answers.get(0)))
        calcGame_btn_2.setText(Integer.toString(answers.get(1)))
        calcGame_btn_3.setText(Integer.toString(answers.get(2)))
        calcGame_btn_4.setText(Integer.toString(answers.get(3)))
    }

    fun multiQuestion() {
        val rand = Random()

        var a = rand.nextInt(11)
        var b = rand.nextInt(11)

        sumAnswer = a * b
        calcGame_tv_Question.setText("$a * $b")
        locationCorrectAnswer = rand.nextInt(4)

        for (i in 0..3) {
            if (i == locationCorrectAnswer) {
                answers.add(sumAnswer)
            } else {
                var incorrectAnswer = rand.nextInt(101)
                while (incorrectAnswer == sumAnswer) {
                    incorrectAnswer = rand.nextInt(101)

                }
                answers.add(incorrectAnswer)
            }

        }
        calcGame_btn_1.setText(Integer.toString(answers.get(0)))
        calcGame_btn_2.setText(Integer.toString(answers.get(1)))
        calcGame_btn_3.setText(Integer.toString(answers.get(2)))
        calcGame_btn_4.setText(Integer.toString(answers.get(3)))
    }

    fun divideQuestion() {
        val rand = Random()

        var a = rand.nextInt(11)
        var b = rand.nextInt(11)
        var c = a*b




        sumAnswer = a * b
        calcGame_tv_Question.setText("$c / $b")
        locationCorrectAnswer = rand.nextInt(4)

        for (i in 0..3) {
            if (i == locationCorrectAnswer) {
                answers.add(a)
            } else {
                var incorrectAnswer = rand.nextInt(11)
                while (incorrectAnswer == a) {
                    incorrectAnswer = rand.nextInt(11)

                }
                answers.add(incorrectAnswer)
            }

        }
        calcGame_btn_1.setText(Integer.toString(answers.get(0)))
        calcGame_btn_2.setText(Integer.toString(answers.get(1)))
        calcGame_btn_3.setText(Integer.toString(answers.get(2)))
        calcGame_btn_4.setText(Integer.toString(answers.get(3)))
    }


}
