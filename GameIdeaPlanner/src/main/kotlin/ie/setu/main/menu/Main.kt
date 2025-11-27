package ie.setu.main.menu

/*
 -----------------------------
 |  GAME IDEA PLANNER - MAIN |
 |                           |
 | 1) Developers menu        |
 | 2) Ideas menu             |
 | 0) Exit                   |
 -----------------------------
 */

import ie.setu.controller.DeveloperManager
import ie.setu.controller.IdeaManager
import ie.setu.utils.readInt

fun main() {

    val devManager = DeveloperManager()
    val ideaManager = IdeaManager()

    // simple helpers for user input
    while (true)
    {
        println("Hi ! Choose your option :")
        println("1. Developers menu")
        println("2. Ideas menu")
        println("0. Exit")

        when(readInt("Choose option: "))
        {
         1-> developersMenu(devManager, ideaManager)
         2-> ideasMenu(ideaManager, devManager)
         0 ->
             {
                 println("Exit"); return
             }

            else -> println("Option incorrect !")
        }
}
    }

