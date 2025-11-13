package ie.setu.utils

object ValidationUtils
{
    private val jobTitles = listOf(
        "associate",
        "junior programmer",
        "developer",
        "senior developer",
        "game designer",
        "level designer",
        "3d artist",
        "2d artist",
        "animator",
        "qa tester",
        "producer"
    )
    private val genres = listOf(
        "rpg", "strategy", "shooter", "puzzle", "casual",
        "platformer", "sandbox", "survival", "horror",
        "sports", "racing", "fighting", "simulation",
        "adventure", "open world", "mmorpg", "roguelike"
    )
    private val statuses = listOf(
        "new", "planned", "prototype", "indevelopment",
        "testing", "released", "on hold", "cancelled", "completed"
    )

    fun validateStatus(status: String): Boolean
    {
        return statuses.contains(status.lowercase())
    }

    fun validateJobTitle(title: String) : Boolean
    {
        return jobTitles.contains(title.lowercase())
    }

    fun validateGenre(genre: String) : Boolean{
        return genres.contains(genre.lowercase())
    }
}