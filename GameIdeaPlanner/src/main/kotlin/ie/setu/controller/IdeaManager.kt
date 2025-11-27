package ie.setu.controller

/*
 ---------------------------------
 |  IdeaManager                    |
 |                                 |
 | - keeps ArrayList of Idea       |
 | - add / remove / update / find  |
 | - small reports and listings    |
 ---------------------------------
 */

import ie.setu.model.Idea
import ie.setu.utils.ValidationUtils
import ie.setu.utils.JsonFileStore
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class IdeaManager
{
    //For store ideas
    private val ideas = arrayListOf<Idea>()

    fun addIdea(idea: Idea) : Boolean
    {
        // do not allow duplicate ids
        if (ideas.any { it.ideaId == idea.ideaId }) return false
        if (!ValidationUtils.validateGenre(idea.genre) || !ValidationUtils.validateStatus(idea.status)) return false
        ideas.add(idea)
        return true
    }

    fun remove(id : Int) : Boolean
    {
        return ideas.removeIf { it.ideaId == id }
    }

    fun update(id: Int, idea: Idea): Boolean {
        val found = ideas.find { it.ideaId == id } ?: return false
        if (!ValidationUtils.validateGenre(idea.genre) || !ValidationUtils.validateStatus(idea.status)) return false
        found.genre = idea.genre
        found.description = idea.description
        found.developerId = idea.developerId
        found.minimumBudget = idea.minimumBudget
        found.actualEffortHours = idea.actualEffortHours
        found.projectedCost = idea.projectedCost
        found.projectedSales = idea.projectedSales
        return true
    }

    fun findById(id: Int): Idea?
    {
        for (idea in ideas) {
            if (idea.ideaId == id) {
                return idea
            }
        }
        return null
    }


    fun getListIdeas() : List<Idea> = ideas

    // check if developer has any ideas
    fun hasIdeasForDeveloper(developerId: Int): Boolean = ideas.any { it.developerId == developerId }

    // remove all ideas for a developer, return count removed
    fun removeByDeveloper(developerId: Int): Int {
        val count = ideas.count { it.developerId == developerId }
        ideas.removeAll { it.developerId == developerId }
        return count
    }

    // Save ideas list to JSON using JsonFileStore
    fun saveToFile(path: String): Boolean {
        val json = Json { prettyPrint = true }
        val text = json.encodeToString(ListSerializer(Idea.serializer()), ideas)
        val store = JsonFileStore()
        return store.saveText(path, text)
    }

    // Load ideas list from JSON file
    fun loadFromFile(path: String): Boolean {
        val store = JsonFileStore()
        val text = store.loadText(path) ?: return false
        return try {
            val json = Json { ignoreUnknownKeys = true }
            val list = json.decodeFromString(ListSerializer(Idea.serializer()), text)
            ideas.clear()
            ideas.addAll(list)
            true
        } catch (e: Exception) {
            println("Error loading ideas: ${e.message}")
            false
        }
    }
}