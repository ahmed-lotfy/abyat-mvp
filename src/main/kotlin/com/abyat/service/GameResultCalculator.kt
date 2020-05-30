package com.abyat.service

import com.abyat.model.*

object GameResultCalculator {

    fun basketballGamePlayerAssessmentCalculator(gameSheet: GameSheet): MutableList<MVPCandidate> {
        val mvpCandidates = mutableListOf<MVPCandidate>()
        val winningTeam = findGameWinningTeam(gameSheet)
        // rules
        gameSheet.gameResult.forEach {
            val assessment = it as BasketballPlayerAssessment
            val mvpCandidate = MVPCandidate()
            when (assessment.player!!.position) {
                BasketballPositions.G -> {
                    mvpCandidate.apply {
                        this.nickname = assessment.player!!.nickname
                        this.ratingPoints =
                            (2 * assessment.scorePoints!!) + (3 * assessment.rebound!!) + (1 * assessment.assist!!)
                    }
                    if (assessment.player!!.team.equals(winningTeam, true)) {
                        mvpCandidate.ratingPoints += 10
                    }
                    mvpCandidates.add(mvpCandidate)
                }
                BasketballPositions.F -> {
                    mvpCandidate.apply {
                        this.nickname = assessment.player!!.nickname
                        this.ratingPoints =
                            (2 * assessment.scorePoints!!) + (2 * assessment.rebound!!) + (2 * assessment.assist!!)
                    }
                    if (assessment.player!!.team.equals(winningTeam, true)) {
                        mvpCandidate.ratingPoints += 10
                    }
                    mvpCandidates.add(mvpCandidate)
                }
                BasketballPositions.C -> {
                    mvpCandidate.apply {
                        this.nickname = assessment.player!!.nickname
                        this.ratingPoints =
                            (2 * assessment.scorePoints!!) + (1 * assessment.rebound!!) + (3 * assessment.assist!!)
                    }
                    if (assessment.player!!.team.equals(winningTeam, true)) {
                        mvpCandidate.ratingPoints += 10
                    }
                    mvpCandidates.add(mvpCandidate)
                }
            }
        }
        return mvpCandidates
    }


    fun handballGamePlayerAssessmentCalculator(gameSheet: GameSheet): MutableList<MVPCandidate> {
        val mvpCandidates = mutableListOf<MVPCandidate>()
        val winningTeam = findGameWinningTeam(gameSheet)
        // rules
        gameSheet.gameResult.forEach {
            val assessment = it as HandballPlayerAssessment
            val mvpCandidate = MVPCandidate()
            when (assessment.player!!.position) {
                HandballPositions.G -> {
                    mvpCandidate.ratingPoints = 50
                    mvpCandidate.apply {
                        this.nickname = assessment.player!!.nickname
                        this.ratingPoints =
                            (5 * assessment.goalsMade!!) + (-2 * assessment.goalsReceived!!)
                    }
                    if (assessment.player!!.team.equals(winningTeam, true)) {
                        mvpCandidate.ratingPoints += 10
                    }
                    mvpCandidates.add(mvpCandidate)
                }
                BasketballPositions.F -> {
                    mvpCandidate.ratingPoints = 20
                    mvpCandidate.apply {
                        this.nickname = assessment.player!!.nickname
                        this.ratingPoints =
                            (1 * assessment.goalsMade!!) + (-1 * assessment.goalsReceived!!)
                    }
                    if (assessment.player!!.team.equals(winningTeam, true)) {
                        mvpCandidate.ratingPoints += 10
                    }
                    mvpCandidates.add(mvpCandidate)
                }
            }
        }
        return mvpCandidates

    }

    private fun findGameWinningTeam(game: GameSheet): String {
        when (game.type) {
            Games.BASKETBALL -> {
                val teamAName = (game.gameResult[0] as BasketballPlayerAssessment).player!!.team
                var teamBName = ""
                var teamAScore = 0
                var teamBScore = 0
                game.gameResult.forEach {
                    val assessment = it as BasketballPlayerAssessment
                    if (assessment.player!!.team.equals(teamAName))
                        teamAScore += assessment.scorePoints!!
                    else {
                        teamBName = assessment.player!!.team!!
                        teamBScore += assessment.scorePoints!!
                    }
                }
                return if (teamAScore > teamBScore)
                    teamAName!!
                else
                    teamBName
            }
            Games.HANDBALL -> {
                val teamAName = (game.gameResult[0] as HandballPlayerAssessment).player!!.team
                var teamBName = ""
                var teamAScore = 0
                var teamBScore = 0
                game.gameResult.forEach {
                    val assessment = it as HandballPlayerAssessment
                    if (assessment.player!!.team.equals(teamAName))
                        teamAScore += assessment.goalsMade!!
                    else {
                        teamBName = assessment.player!!.team!!
                        teamBScore += assessment.goalsMade!!
                    }
                }
                return if (teamAScore > teamBScore)
                    teamAName!!
                else
                    teamBName
            }
        }
        return ""
    }
}
