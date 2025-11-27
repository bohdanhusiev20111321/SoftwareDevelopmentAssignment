package ie.setu.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json


class JsonFileStore<T>(private val listSerializer: KSerializer<List<T>>) {

    private val json = Json { prettyPrint = true; ignoreUnknownKeys = true }

    fun load(path: String): List<T>? 
    {
        return try 
        {
            val f = java.io.File(path)
            if (!f.exists()) return null
            val text = f.readText()
            json.decodeFromString(listSerializer, text)
        } catch (e: Exception) 
        {
            e.printStackTrace(); null
        }
    }

    fun save(path: String, data: List<T>): Boolean {
        return try 
        {
            val file = File(path)


            if (file.parentFile != null && !file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }

            val text = json.encodeToString(listSerializer, data)
            file.writeText(text)
            true
            
        } catch (e: Exception) 
        {
            println("Error while saving JSON: ${e.message}")
            false
        }
    }
}
