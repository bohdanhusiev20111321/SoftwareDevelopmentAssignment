package ie.setu.model

import kotlinx.serialization.Serializable

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