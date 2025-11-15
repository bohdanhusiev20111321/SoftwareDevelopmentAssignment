package ie.setu.controller

import ie.setu.utils.ValidationUtils
import ie.setu.model.Developer

class DeveloperManager
{
    //For store developers
    private val developers = ArrayList<Developer>()

    fun add(dev:Developer) : Boolean
    {
        if(!ValidationUtils.validateJobTitle(dev.jobTitle))
        {
            return false
        }
        developers.add(dev)
        return true
    }

    fun remove(id :Int) : Boolean
    {
        for(dev in developers)
        {
            if(dev.developerId == id)
            {
                developers.remove(dev)
                return true
            }
        }
        return false
    }

    fun update(id: Int, dev: Developer) : Boolean
    {
        for (d in developers)
        {
            //find developer if exist
            if (d.developerId == id) {
                // check job title
                if (!ValidationUtils.validateJobTitle(dev.jobTitle)) {
                    return false
                }

                d.name = dev.name
                d.jobTitle = dev.jobTitle
                d.salary = dev.salary
                d.yearsExperience = dev.yearsExperience
                d.isRetired = dev.isRetired
                return true
            }
        }
        //iD not found
        return false;
    }
    // find a developer by id from a list
    fun findById(id: Int): Developer? = developers.find { it.developerId == id }

    // get all developers
    fun getListDevelopers(): List<Developer> = developers

}