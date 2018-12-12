package com.example.jan.termprojectapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_color_game_result.*
import kotlinx.android.synthetic.main.activity_term_calculation_game_result.*
import java.util.*

class TermCalculationGameResult : AppCompatActivity() {


    lateinit var editTextName : EditText
    lateinit var database : DatabaseReference

    val ref = FirebaseDatabase.getInstance().getReference("TermCalcScores")
    lateinit var scoreList : MutableList<ScoreEntry>


    var isSaved : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_calculation_game_result)

        val score = intent.getStringExtra(EXTRA_Message)

        editTextName = calcResult_etxt_name

        calcResult_tv_Score.setText(score.toString())

        scoreList = mutableListOf()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    scoreList.clear()

                    for (h in p0.children) {
                        val entry = h.getValue(ScoreEntry::class.java)
                        scoreList.add(entry!!)

                    }
                    Collections.sort(scoreList, HighscoreActivity.CustomComperatorNumber())
                    val adapter = ScoreAdapter(applicationContext, R.layout.scores, scoreList)
                    listView3.adapter = adapter
                }
            }

        })

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
