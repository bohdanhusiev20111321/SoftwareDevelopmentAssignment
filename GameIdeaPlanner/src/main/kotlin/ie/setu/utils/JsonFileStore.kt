package ie.setu.utils

/*
 -----------------------------------------
 |  FILE STORE                              |
 |                                          |  
 | - saveText(path, text)                   |
 | - loadText(path): String?                |
 |                                          |
 | Used by the app to persist               |
 | small pieces of text data to disk.       |
 -----------------------------------------  
 */

import java.io.File


/**
 * Save and load text from a file.
 *
 * This store is small and easy to use. It is used to save JSON strings to disk
 * and read them back. Methods return true on success or null on failure.
 */
class JsonFileStore {


    fun saveText(path: String, text: String): Boolean {
        return try {
            val file = File(path)


            if (file.parentFile != null && !file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }

            file.writeText(text)
            true
        } catch (e: Exception) {

            println("Error while saving file: ${e.message}")
            false
        }
    }


    fun loadText(path: String): String? {
        return try {
            val file = File(path)
            if (!file.exists()) return null
            file.readText()
        } catch (e: Exception) {
            println("Error while loading file: ${e.message}")
            null
        }
    }

}
