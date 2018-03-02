package com.concrete.mauricio.gamescore

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class GameStoreActivity : AppCompatActivity() {

    private lateinit var scoreViewModel: GameStoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actv_game)

        scoreViewModel = ViewModelProviders.of(this).get(GameStoreViewModel::class.java)

        configureTeam(GameStoreViewModel.TEAM_A)
        configureTeam(GameStoreViewModel.TEAM_B)

        findViewById<Button>(R.id.btn_reset).setOnClickListener {
            scoreViewModel.resetScore()
        }
    }

    private fun configureTeam(team: Char) {

        val rootView = getTeamView(team)

        rootView.findViewById<TextView>(R.id.txt_team).setText(R.string.team_a)

        rootView.findViewById<Button>(R.id.btn_three_points).setOnClickListener {
            scoreViewModel.updateScore(team, 3)
        }

        rootView.findViewById<Button>(R.id.btn_two_points).setOnClickListener {
            scoreViewModel.updateScore(team, 2)
        }

        rootView.findViewById<Button>(R.id.btn_free_throw).setOnClickListener {
            scoreViewModel.updateScore(team, 1)
        }

        scoreViewModel.getScore(team).observe(this, Observer { score ->
            rootView.findViewById<TextView>(R.id.txt_score).text = score.toString()
        })

    }

    private fun getTeamView(team: Char): View {
        val viewId = if (team == GameStoreViewModel.TEAM_A) R.id.teamA else R.id.teamB
        return findViewById<ConstraintLayout>(viewId)
    }
}
