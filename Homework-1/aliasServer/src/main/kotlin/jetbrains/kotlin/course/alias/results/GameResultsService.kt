package jetbrains.kotlin.course.alias.results

import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Service

typealias GameResult = List<Team>

@Service
class GameResultsService {

    companion object {
        private val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult) {
        if (result.isEmpty()) {
            throw IllegalArgumentException("Game result cannot be empty.")
        }

        val validTeamIds = TeamService.teamsStorage.keys
        if (result.any { it.id !in validTeamIds }) {
            throw IllegalArgumentException("One or more teams in the result do not exist in TeamService.")
        }

        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> {
        return gameHistory.reversed()
    }
}