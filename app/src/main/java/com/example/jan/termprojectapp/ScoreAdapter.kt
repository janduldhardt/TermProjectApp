package com.example.jan.termprojectapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ScoreAdapter(val mContext : Context, val layoutResID : Int, val scoreList : List<ScoreEntry>)
    : ArrayAdapter<ScoreEntry>(mContext, layoutResID, scoreList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(layoutResID, null)

        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewScore = view.findViewById<TextView>(R.id.textViewScore)

        val entry = scoreList[position]

        textViewName.text = entry.name
        textViewScore.text = entry.score.toString()

        return view
    }

    override fun getCount(): Int {
        return 5
    }
}