package ie.setu.model

import kotlinx.serialization.Serializable

/**
 * Idea - a short description of a game project.
 *
 * Simple fields:
 * - ideaId: unique id
 * - description: short text about the idea
 * - minimumBudget: minimum money needed
 * - developerId: id of the developer who works on it
 * - genre: game genre
 * - projectEffortHours: planned hours
 * - actualEffortHours: real hours used
 * - projectedCost: cost estimate
 * - projectedSales: sales estimate
 * - status: current state (e.g. new, planned)
 */
@Serializable
data class Idea(
    val ideaId: Int,
    var description: String,
    var minimumBudget: Double,
    var developerId: Int,
    var genre: String,
    var projectEffortHours: Int,
    var actualEffortHours: Int,
    var projectedCost: Double,
    var projectedSales: Double,
    var status : String
)