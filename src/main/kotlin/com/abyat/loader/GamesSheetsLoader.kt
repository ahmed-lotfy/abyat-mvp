package com.abyat.loader

import com.abyat.model.*
import java.io.File

object GamesSheetsLoader {

    fun loadGamesResult(dir: String): List<GameSheet> {
        val gamesResult = mutableListOf<GameSheet>()
        File(dir).walk().forEach {
            if (!it.isDirectory)
                gamesResult.add(loadGame(it.absolutePath))
        }
        return gamesResult
    }

    private fun loadGame(path: String): GameSheet {
        try {
            val file = File(path)
            val gameSheet = GameSheet()
            val lines = file.readLines()
            // first line means game type.
            gameSheet.type = Games.valueOf(lines[0])
            when (gameSheet.type) {
                Games.BASKETBALL -> {
                    lines.forEach { line ->
                        val fields = line.split(";")
                        if (fields.size != 1) {
                            // add player info
                            val player = Player().apply {
                                name = fields[0]
                                nickname = fields[1]
                                number = fields[2].toInt()
                                team = fields[3]
                                position = BasketballPositions.valueOf(fields[4])
                            }
                            val basketballPlayerAssessment = BasketballPlayerAssessment().apply {
                                this.player = player
                                this.scorePoints = fields[5].toInt()
                                this.rebound = fields[6].toInt()
                                this.assist = fields[7].toInt()
                            }
                            gameSheet.gameResult.add(basketballPlayerAssessment)
                        }
                    }
                }
                Games.HANDBALL -> {
                    lines.forEach { line ->
                        val fields = line.split(";")
                        if (fields.size != 1) {
                            // add player info
                            val player = Player().apply {
                                name = fields[0]
                                nickname = fields[1]
                                number = fields[2].toInt()
                                team = fields[3]
                                position = HandballPositions.valueOf(fields[4])
                            }
                            val handballPlayerAssessment = HandballPlayerAssessment().apply {
                                this.player = player
                                this.goalsMade = fields[5].toInt()
                                this.goalsReceived = fields[6].toInt()
                            }
                            gameSheet.gameResult.add(handballPlayerAssessment)
                        }
                    }
                }
            }
            return gameSheet
        } catch (e: Exception) {
            throw e
        }
    }
}
