package ie.setu.utils

/*
 -------------------------
 |  MENU HELPERS            |
 |                          |  
 | readInt, readDouble      |
 | readString               |
 |                          |
 | Small utilities to get   |
 | simple user input        |
 -------------------------
 */

fun readInt(prompt: String): Int 
{
    while (true) {
        print(prompt)
        val input = readLine()
        val n = input?.toIntOrNull()
        if (n != null) return n
        println("Invalid integer, try again")
    }
}

fun readDouble(prompt: String): Double 
{
    while (true) {
        print(prompt)
        val input = readLine()
        val v = input?.toDoubleOrNull()
        if (v != null) return v
        println("Invalid number, try again")
    }
}

fun readString(prompt: String): String 
{
    while (true) {
        print(prompt)
        val input = readLine()
        if (!input.isNullOrBlank()) return input
        println("Input cannot be empty, please try again")
    }
}

// read boolean value (yes/no or true/false)
fun readBoolean(prompt: String): Boolean {
    while (true) {
        print(prompt)
        val input = readLine()?.trim()?.lowercase()
        if (input == "true" || input == "t" || input == "y" || input == "yes") return true
        if (input == "false" || input == "f" || input == "n" || input == "no") return false
        println("Please answer true/false or y/n")
    }
}

