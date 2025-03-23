package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.Identifier
import org.springframework.stereotype.Service

@Service
class TeamService {

    // Create new teams ids
    private val identifierFactory = IdentifierFactory()

    // Store teams
    companion object {
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
    }

    // Generate list of teams for one round
    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val teams = mutableListOf<Team>()

        for (i in 1..teamsNumber) {
            val teamId = identifierFactory.uniqueIdentifier()
            val team = Team(id = teamId)
            teamsStorage[teamId] = team
            teams.add(team)
        }

        return teams
    }
}