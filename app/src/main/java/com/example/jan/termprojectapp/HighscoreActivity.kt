package com.example.jan.termprojectapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_highscore.*
import java.util.*

class HighscoreActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var scoreList : MutableList<ScoreEntry>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        ref = FirebaseDatabase.getInstance().getReference("TermCalcScores")
        scoreList = mutableListOf()

        ref.addValueEventListener(object : ValueEventListener {
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
                    Collections.sort(scoreList, CustomComperatorNumber())
                    val adapter = ScoreAdapter(applicationContext, R.layout.scores, scoreList)
                    listView1.adapter = adapter
                }
            }

        })
    }

         class CustomComperatorNumber : Comparator<ScoreEntry>{
             override fun compare(o1: ScoreEntry?, o2: ScoreEntry?): Int {
                 return o2!!.score.compareTo(o1!!.score)
             }


         }
}


