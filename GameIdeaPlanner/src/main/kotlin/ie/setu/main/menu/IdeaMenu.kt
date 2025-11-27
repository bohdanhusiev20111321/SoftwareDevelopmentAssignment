package ie.setu.main.menu

/*
 -------------------------------------------------
 |  GAME IDEA PLANNER - IDEAS MENU               |
 |                                               |
 | 1) Add idea                                   |
 | 2) List all ideas                             |
 | 3) Find idea by ID                            |
 | 4) Update idea                                |
 | 5) Delete idea                                |
 | 6) List by genre                              |
 | 7) Report: count & sales by developer         |
 | 8) Report: retired devs completed ideas       |
 | 0) Back to main menu                          |
 -------------------------------------------------
 */

import ie.setu.controller.IdeaManager
import ie.setu.controller.DeveloperManager
import ie.setu.utils.readInt
import ie.setu.utils.readDouble
import ie.setu.utils.readString
import ie.setu.utils.ValidationUtils

fun ideasMenu(
    ideaManager: IdeaManager,
    devManager: DeveloperManager
) {
    while (true) {
        println("\n--- Ideas Menu ---")
        println("1. Add idea")
        println("2. List all ideas")
        println("3. Find idea by ID")
        println("4. Update idea")
        println("5. Delete idea")
        println("6. List by genre")
        println("7. Report: count & sales by developer")
        println("8. Report: retired devs completed ideas")
        println("0. Back to main menu")
        when (readInt("Choose option: ")) {
            1 -> {
                var id: Int
                while (true) {
                    id = readInt("Enter id: ")
                    if (ideaManager.findById(id) != null) {
                        println("Idea id $id already exists. Please use a different id.")
                        continue
                    }
                    break
                }
                val desc = readString("Enter description: ")
                val minBudget = readDouble("Enter minBudget: ")
                var devId = readInt("Enter developerId: ")
                // check developer exists (re-prompt until valid)
                while (devManager.findById(devId) == null) {
                    println("Developer with id $devId not found. Please enter an existing developerId.")
                    devId = readInt("Enter developerId: ")
                }
                var genre = readString("Enter genre: ")
                while (!ValidationUtils.validateGenre(genre)) {
                    println("Invalid genre. Please enter a valid genre.")
                    genre = readString("Enter genre: ")
                }
                val effort = readInt("Enter projectEffortHours: ")
                val actual = readInt("Enter actualEffortHours: ")
                val cost = readDouble("Enter projectedCost: ")
                val sales = readDouble("Enter projectedSales: ")
                var status = readString("Enter status: ")
                while (!ValidationUtils.validateStatus(status)) {
                    println("Invalid status. Please enter a valid status.")
                    status = readString("Enter status: ")
                }
                val idea = ie.setu.model.Idea(id, desc, minBudget, devId, genre, effort, actual, cost, sales, status)
                if (ideaManager.addIdea(idea)) println("Added!") else println("Invalid genre or status!")
            }
            2 -> {
                val list = ideaManager.getListIdeas()
                if (list.isEmpty()) println("No ideas.") else list.forEach { println(it) }
            }
            3 -> {
                val id = readInt("Enter id: ")
                val idea = ideaManager.findById(id)
                if (idea != null) println(idea) else println("Not found.")
            }
            4 -> {
                var id = readInt("Enter id to update: ")
                while (ideaManager.findById(id) == null) {
                    println("Idea with id $id not found. Enter a valid idea id.")
                    id = readInt("Enter id to update: ")
                }
                val desc = readString("Enter new description: ")
                val minBudget = readDouble("Enter new minBudget: ")
                var devId = readInt("Enter new developerId: ")
                while (devManager.findById(devId) == null) {
                    println("Developer with id $devId not found. Please enter an existing developerId.")
                    devId = readInt("Enter new developerId: ")
                }
                var genre = readString("Enter new genre: ")
                while (!ValidationUtils.validateGenre(genre)) {
                    println("Invalid genre. Please enter a valid genre.")
                    genre = readString("Enter new genre: ")
                }
                val effort = readInt("Enter new projectEffortHours: ")
                val actual = readInt("Enter new actualEffortHours: ")
                val cost = readDouble("Enter new projectedCost: ")
                val sales = readDouble("Enter new projectedSales: ")
                var status = readString("Enter new status: ")
                while (!ValidationUtils.validateStatus(status)) {
                    println("Invalid status. Please enter a valid status.")
                    status = readString("Enter new status: ")
                }
                val idea = ie.setu.model.Idea(id, desc, minBudget, devId, genre, effort, actual, cost, sales, status)
                val ok = ideaManager.update(id, idea)
                if (ok) println("Updated!") else println("Not found or bad data.")
            }
            5 -> {
                val id = readInt("Enter id to delete: ")
                if (ideaManager.remove(id)) println("Deleted!") else println("Not found.")
            }
            6 -> {
                val genre = readString("Enter genre: ")
                val result = arrayListOf<ie.setu.model.Idea>()
                val all = ideaManager.getListIdeas()
                for (i in all) if (i.genre.equals(genre, true)) result.add(i)
                if (result.isEmpty()) println("No ideas with this genre.") else result.forEach { println(it) }
            }
            7 -> {
                val devId = readInt("Enter developerId: ")
                val all = ideaManager.getListIdeas()
                var count = 0
                var sumEffort = 0
                var sumSales = 0.0
                for (i in all) {
                    if (i.developerId == devId) {
                        count++
                        sumEffort += i.projectEffortHours
                        sumSales += i.projectedSales
                    }
                }
                println("Ideas: $count, Total effort: $sumEffort, Total sales: $sumSales")
            }
            8 -> {
   
                val devs = devManager.getListDevelopers()
                val retired = devs.filter { it.isRetired }
                var totalCompleted = 0
                var totalActualEffort = 0

                val allIdeas = ideaManager.getListIdeas()
                for (d in retired) {
                    for (i in allIdeas) {
                        if (i.developerId == d.developerId && i.status.equals("completed", true)) {
                            totalCompleted++
                            totalActualEffort += i.actualEffortHours
                        }
                    }
                }
                println("Retired developers: ${retired.size}, Completed ideas: $totalCompleted, Total actual effort: $totalActualEffort")
            }
            0 -> return
            else -> println("Wrong option!")
        }
    }
}
