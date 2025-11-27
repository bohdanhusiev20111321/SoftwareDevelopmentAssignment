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
}