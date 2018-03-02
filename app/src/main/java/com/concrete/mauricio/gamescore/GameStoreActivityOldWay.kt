package com.concrete.mauricio.gamescore

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class GameStoreActivityOldWay : AppCompatActivity() {

    private var scoreA: Int = 0
    private var scoreB: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actv_game)

        configureTeam(GameStoreViewModel.TEAM_A, scoreA)
        configureTeam(GameStoreViewModel.TEAM_B, scoreB)

        findViewById<Button>(R.id.btn_reset).setOnClickListener {
            scoreA = 0
            scoreB = 0
            getTeamView(GameStoreViewModel.TEAM_A).findViewById<TextView>(R.id.txt_score).text = "0"
            getTeamView(GameStoreViewModel.TEAM_B).findViewById<TextView>(R.id.txt_score).text = "0"
        }
    }

    private fun configureTeam(team: Char, initialScore: Int) {

        val rootView = getTeamView(team)

        rootView.findViewById<TextView>(R.id.txt_team).setText(R.string.team_a)

        rootView.findViewById<Button>(R.id.btn_three_points).setOnClickListener {
            updateScore(team, 3)
        }

        rootView.findViewById<Button>(R.id.btn_two_points).setOnClickListener {
            updateScore(team, 2)
        }

        rootView.findViewById<Button>(R.id.btn_free_throw).setOnClickListener {
            updateScore(team, 1)
        }

        getTeamView(team).findViewById<TextView>(R.id.txt_score).text = initialScore.toString()

    }

    private fun getTeamView(team: Char): View {
        val viewId = if (team == GameStoreViewModel.TEAM_A) R.id.teamA else R.id.teamB
        return findViewById<ConstraintLayout>(viewId)
    }

    private fun updateScore(team: Char, points: Int) {
        if (team == GameStoreViewModel.TEAM_A) {
            scoreA += points
            getTeamView(GameStoreViewModel.TEAM_A).findViewById<TextView>(R.id.txt_score).text = scoreA.toString()
        } else {
            scoreB += points
            getTeamView(GameStoreViewModel.TEAM_B).findViewById<TextView>(R.id.txt_score).text = scoreB.toString()
        }
    }
}