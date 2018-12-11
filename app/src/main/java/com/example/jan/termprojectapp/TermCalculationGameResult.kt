package com.example.jan.termprojectapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_term_calculation_game_result.*

class TermCalculationGameResult : AppCompatActivity() {


    lateinit var editTextName : EditText
    lateinit var database : DatabaseReference

    var isSaved : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_calculation_game_result)

        val score = intent.getStringExtra(EXTRA_Message)

        editTextName = calcResult_etxt_name

        calcResult_tv_Score.setText(score.toString())

        calcResult_btn_playAgain.setOnClickListener {
            val intent = Intent(this@TermCalculationGameResult, TermCalculationGame::class.java)
            startActivity(intent)
        }

        calcResult_btn_MainMenu.setOnClickListener {
            val intent = Intent(this@TermCalculationGameResult, MainActivity::class.java)
            startActivity(intent)
        }

        calcResult_btn_save.setOnClickListener {
            if (!isSaved) {
                saveScore()
            } else {
                Toast.makeText(this, "Already saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun writeNewUser(userId: String, name: String, email: String?) {
//        val user = User(name, email)
//        database.child("users").child(userId).setValue(user)
//    }

    private fun saveScore() {
        val name = editTextName.text.toString()
        val score = intent.getStringExtra(EXTRA_Message)

        if (name.isEmpty()){
            editTextName.error = ("Please enter name")
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("TermCalcScores")
        val entryID = ref.push().key

        val entry = ScoreEntry(entryID!!, name, score.toInt())

        ref.child(entryID).setValue(entry).addOnCompleteListener {
            Toast.makeText(this, "Saved!!", Toast.LENGTH_LONG).show()
            isSaved = true
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this@TermCalculationGameResult, MainActivity::class.java)
        startActivity(intent)
    }
}
