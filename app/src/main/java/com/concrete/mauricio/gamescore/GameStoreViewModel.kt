package com.concrete.mauricio.gamescore

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by mauriciobarbosa on 3/2/18.
 */
class GameStoreViewModel : ViewModel() {

    companion object {
        val TEAM_A : Char = 'A'
        val TEAM_B : Char = 'B'
    }

    private val scoreA: MutableLiveData<Int> = MutableLiveData()
    private val scoreB: MutableLiveData<Int> = MutableLiveData()

    init {
        scoreA.value = 0
        scoreB.value = 0
    }

    fun updateScore(team: Char, points: Int) {
        val score = if (team == 'A') scoreA else scoreB
        val total = score.value!! + points
        score.value = total
    }

    fun resetScore() {
        scoreA.value = 0
        scoreB.value = 0
    }

    fun getScore(team: Char) = if (team == TEAM_A) scoreA else scoreB

}