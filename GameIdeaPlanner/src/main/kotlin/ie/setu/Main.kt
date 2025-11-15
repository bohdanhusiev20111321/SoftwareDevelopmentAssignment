package ie.setu

import ie.setu.controller.DeveloperManager
import ie.setu.controller.IdeaManager
import ie.setu.model.Developer
import ie.setu.model.Idea

fun main() {

    val devManager = DeveloperManager()
    val ideaManager = IdeaManager()

    // just testing some stuff here
    val dev1 = Developer(1, "Patrick", "developer", 50000.0, 2, false)
    val dev2 = Developer(2, "Dennis", "game designer", 48000.0, 3, false)

    println("Adding developers:")
    println(devManager.add(dev1))
    println(devManager.add(dev2))

    println("\n Show Developers:")
    println(devManager.getListDevelopers())

    val idea1 = Idea(
        1,
        "Small mobile puzzle game",
        2000.0,
        1,
        "puzzle",
        20,
        5,
        1500.0,
        3000.0,
        "new"
    )

    println("\n first idea:")
    println(ideaManager.addIdea(idea1))

    println("\n Ideas list:")
    println(ideaManager.getListIdeas())

    println("\n Find dev by id = 1:")
    println(devManager.findById(1))

    println("\n Find idea by id = 1:")
    println(ideaManager.findById(1))

    println("\n Finish.")
}
