package ie.setu.main.menu

import ie.setu.controller.DeveloperManager
import ie.setu.controller.IdeaManager
import java.util.Scanner

fun main() {

    val devManager = DeveloperManager()
    val ideaManager = IdeaManager()

    val input = Scanner(System.`in`)

    while (true)
    {
        println("Hi ! Choose your option :")
        println("1. Developers menu")
        println("2. Ideas menu")
        println("0. Exit")

        when(input.nextInt())
        {
         1-> developersMenu(devManager, ideaManager, input)
         2-> ideasMenu(ideaManager, devManager, input)
         0 ->
             {
                 println("Exit"); return
             }

            else -> println("Option incorrect !")
        }
}
    }

