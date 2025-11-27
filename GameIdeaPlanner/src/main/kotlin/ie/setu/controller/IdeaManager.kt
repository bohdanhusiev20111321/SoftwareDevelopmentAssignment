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
        for (i in ideas) {
            if (i.ideaId == idea.ideaId) return false
        }
        if(!ValidationUtils.validateGenre(idea.genre) ||
            !ValidationUtils.validateStatus(idea.status))
        {
            return false
        }
        ideas.add(idea)
        return true
    }

    fun remove(id : Int) : Boolean
    {
        return ideas.removeIf { it.ideaId == id }
    }

    fun update(id : Int, idea : Idea) : Boolean
    {
        for(i in ideas)
        {
            if(i.ideaId == id)
            {
                // validate genre and status for updates
                if(!ValidationUtils.validateGenre(idea.genre) || !ValidationUtils.validateStatus(idea.status)) {
                    return false
                }
                i.genre = idea.genre
                i.description = idea.description
                i.developerId = idea.developerId
                i.minimumBudget= idea.minimumBudget
                i.actualEffortHours = idea.actualEffortHours
                i.projectedCost = idea.projectedCost
                i.projectedSales = idea.projectedSales
                return true
            }
        }
        //iD not found
        return false;
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