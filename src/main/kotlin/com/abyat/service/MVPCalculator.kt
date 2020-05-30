package com.abyat.service

import com.abyat.loader.GamesSheetsLoader
import com.abyat.model.Games
import com.abyat.model.MVPCandidate

object MVPCalculator {

    fun findMVP(gamesDirectory: String): String {
        val games = GamesSheetsLoader.loadGamesResult(gamesDirectory)
        val unifiedMVPList = mutableListOf<MVPCandidate>()
        games.forEach { game ->
            var mvpCandidateList = mutableListOf<MVPCandidate>()
            when (game.type) {
                Games.BASKETBALL -> {
                    mvpCandidateList = GameResultCalculator.basketballGamePlayerAssessmentCalculator(game)
                }
                Games.HANDBALL -> {
                    mvpCandidateList = GameResultCalculator.handballGamePlayerAssessmentCalculator(game)
                }
            }
            mvpCandidateList.forEach { candidate ->
                if (unifiedMVPList.contains(candidate)) {
                    unifiedMVPList.filter { x -> x.nickname.equals(candidate.nickname) }.get(0)
                        .apply { this.ratingPoints += candidate.ratingPoints }
                } else {
                    unifiedMVPList.add(candidate)
                }
            }
        }
        unifiedMVPList.sortByDescending { x -> x.ratingPoints }
        return unifiedMVPList[0].nickname!!
    }
}
