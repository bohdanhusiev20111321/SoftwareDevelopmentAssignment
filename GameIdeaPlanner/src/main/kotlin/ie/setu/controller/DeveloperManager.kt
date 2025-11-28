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

/**
 * Simple manager for developers.
 *
 * Keeps a list in memory. You can add, remove, update and find developers.
 * It can also save the list to a file and load it back.
 */
class DeveloperManager
{
    //For store developers
    private val developers = ArrayList<Developer>()

    fun add(dev: Developer): Boolean {
        // don't allow duplicate ids
        if (developers.any { it.developerId == dev.developerId }) return false
        if (!ValidationUtils.validateJobTitle(dev.jobTitle)) return false
        developers.add(dev)
        return true
    }

    fun remove(id :Int) : Boolean
    {
        return developers.removeIf { it.developerId == id }
    }

    fun update(id: Int, dev: Developer): Boolean {
        val found = developers.find { it.developerId == id } ?: return false
        if (!ValidationUtils.validateJobTitle(dev.jobTitle)) return false
        found.name = dev.name
        found.jobTitle = dev.jobTitle
        found.salary = dev.salary
        found.yearsExperience = dev.yearsExperience
        found.isRetired = dev.isRetired
        return true
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

    //  For tests
    fun numberOfDevelopersForTest(): Int = developers.size


    fun listDevelopersForTest(): String {
        if (developers.isEmpty()) {
            return "No developers"
        }

        var output = ""

        for (dev in developers) {
            output += dev.developerId.toString() + ": " + dev.name + " - " + dev.jobTitle + "\n"

            for (d in developers) {
                output += d.name + "\n"

            }

            return output
        }

        return output
    }
}