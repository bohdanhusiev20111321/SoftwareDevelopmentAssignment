package ie.setu.model

data class Idea(
    val ideaId: Int,
    var description: String,
    var minimumBudget: Double,
    var developerId: Int,
    var genre: String,
    var projectEffortHours: Int,
    var actualEffortHours: Int,
    var projectedCost: Double,
    var projectedSales: Double
)
