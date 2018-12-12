package com.example.jan.termprojectapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_color_game_result.*
import kotlinx.android.synthetic.main.activity_highscore.*
import java.util.*

class colorGameResultActivity : AppCompatActivity() {

    var isSaved = false
    lateinit var ref : DatabaseReference
    lateinit var scoreList : MutableList<ScoreEntry>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_game_result)

        val score = intent.getStringExtra(EXTRA_Message3)
        colorGameResult_tv_Score.setText(score.toString())

        ref = FirebaseDatabase.getInstance().getReference("ColorGameScores")

    scoreList = mutableListOf()
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    scoreList.clear()
                    for (h in p0.children) {
                        val entry = h.getValue(ScoreEntry::class.java)
                        scoreList.add(entry!!)

                    }
                    Collections.sort(scoreList, HighscoreActivity.CustomComperatorNumber())
                    val adapter = ScoreAdapter(applicationContext, R.layout.scores, scoreList)
                    listView2.adapter = adapter
                }
            }

        })

        colorGameResult_btn_playAgain.setOnClickListener {
            val intent = Intent(this, ColorGame::class.java)
            startActivity(intent)
        }

       colorGameResult_btn_MainMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        colorGameResult_btn_save.setOnClickListener {
            if (!isSaved) {
                saveScore()
            } else {
                Toast.makeText(this, "Already saved!", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun saveScore() {
        val name = colorGameResult_etxt_name.text.toString()
        val score = intent.getStringExtra(EXTRA_Message3)

        if (name.isEmpty()){
            colorGameResult_etxt_name.error = ("Please enter name")
            return
        }

        ref = FirebaseDatabase.getInstance().getReference("ColorGameScores")
        val entryID = ref.push().key

        val entry = ScoreEntry(entryID!!, name, score.toInt())

        ref.child(entryID).setValue(entry).addOnCompleteListener {
            Toast.makeText(this, "Saved!!", Toast.LENGTH_LONG).show()
//            isSaved = true
        }    }

    override fun onBackPressed() {
        val intent = Intent(this@colorGameResultActivity, MainActivity::class.java)
        startActivity(intent)
        val name = colorGameResult_etxt_name.text.toString()
        if (name.isEmpty()) {
            Toast.makeText(this, "Enter a name", Toast.LENGTH_SHORT).show()
            return
        }
    }
}
