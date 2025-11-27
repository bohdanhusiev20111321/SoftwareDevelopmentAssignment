package ie.setu.controller

/*
 ---------------------------------
 |  DeveloperManager                |
 |                                  |
 | - holds an ArrayList of Developer|    
 | - provides add / remove / update |
 | - find and list helpers          |
 ---------------------------------
 */

import ie.setu.utils.ValidationUtils
import ie.setu.utils.JsonFileStore
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import ie.setu.model.Developer

class DeveloperManager
{
    //For store developers
    private val developers = ArrayList<Developer>()

    fun add(dev:Developer) : Boolean
    {
        // don't allow duplicate ids
        for (d in developers) {
            if (d.developerId == dev.developerId) return false
        }
        if(!ValidationUtils.validateJobTitle(dev.jobTitle))
        {
            return false
        }
        developers.add(dev)
        return true
    }

    fun remove(id :Int) : Boolean
    {
        return developers.removeIf { it.developerId == id }
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

    // Save developers to json file using JsonFileStore
    fun saveToFile(path: String): Boolean {
        val json = Json { prettyPrint = true }
        val text = json.encodeToString(ListSerializer(Developer.serializer()), developers)
        val store = JsonFileStore()
        return store.saveText(path, text)
    }

    // Load developers from json file, replace current list
    fun loadFromFile(path: String): Boolean {
        val store = JsonFileStore()
        val text = store.loadText(path) ?: return false
        return try {
            val json = Json { ignoreUnknownKeys = true }
            val list = json.decodeFromString(ListSerializer(Developer.serializer()), text)
            developers.clear()
            developers.addAll(list)
            true
        } catch (e: Exception) {
            println("Error loading developers: ${e.message}")
            false
        }
    }

}