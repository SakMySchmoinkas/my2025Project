package jetbrains.kotlin.course.alias.results

import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Service

// Type alias for game results
typealias GameResult = List<Team>

@Service
class GameResultsService {

    companion object {
        // Mutable list to store game results
        private val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult) {
        // Validate that the result is not empty
        if (result.isEmpty()) {
            throw IllegalArgumentException("Game result cannot be empty.")
        }

        // Validate that all team IDs exist in TeamService
        val validTeamIds = TeamService.teamsStorage.keys
        if (result.any { it.id !in validTeamIds }) {
            throw IllegalArgumentException("One or more teams in the result do not exist in TeamService.")
        }

        // Add the result to game history
        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> {
        // Return game history in reversed order
        return gameHistory.reversed()
    }
}