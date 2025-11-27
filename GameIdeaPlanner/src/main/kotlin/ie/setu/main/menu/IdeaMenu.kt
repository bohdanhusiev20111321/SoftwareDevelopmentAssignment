package ie.setu.main.menu

import ie.setu.controller.IdeaManager
import ie.setu.controller.DeveloperManager
import java.util.Scanner

fun ideasMenu(
    ideaManager: IdeaManager,
    devManager: DeveloperManager,
    input: Scanner
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
        print("Choose option: ")

        when (input.nextInt()) {
            1 -> {
                print("Enter id: "); val id = input.nextInt()
                print("Enter description: "); val desc = input.next()
                print("Enter minBudget: "); val minBudget = input.nextDouble()
                print("Enter developerId: "); val devId = input.nextInt()
                print("Enter genre: "); val genre = input.next()
                print("Enter projectEffortHours: "); val effort = input.nextInt()
                print("Enter actualEffortHours: "); val actual = input.nextInt()
                print("Enter projectedCost: "); val cost = input.nextDouble()
                print("Enter projectedSales: "); val sales = input.nextDouble()
                print("Enter status: "); val status = input.next()
                val idea = ie.setu.model.Idea(id, desc, minBudget, devId, genre, effort, actual, cost, sales, status)
                if (ideaManager.addIdea(idea)) println("Added!") else println("Invalid genre or status!")
            }
            2 -> {
                val list = ideaManager.getListIdeas()
                if (list.isEmpty()) println("No ideas.") else for (i in list) println(i)
            }
            3 -> {
                print("Enter id: "); val id = input.nextInt()
                val idea = ideaManager.findById(id)
                if (idea != null) println(idea) else println("Not found.")
            }
            4 -> {
                print("Enter id to update: "); val id = input.nextInt()
                print("Enter new description: "); val desc = input.next()
                print("Enter new minBudget: "); val minBudget = input.nextDouble()
                print("Enter new developerId: "); val devId = input.nextInt()
                print("Enter new genre: "); val genre = input.next()
                print("Enter new projectEffortHours: "); val effort = input.nextInt()
                print("Enter new actualEffortHours: "); val actual = input.nextInt()
                print("Enter new projectedCost: "); val cost = input.nextDouble()
                print("Enter new projectedSales: "); val sales = input.nextDouble()
                print("Enter new status: "); val status = input.next()
                val idea = ie.setu.model.Idea(id, desc, minBudget, devId, genre, effort, actual, cost, sales, status)
                val ok = ideaManager.update(id, idea)
                if (ok) println("Updated!") else println("Not found or bad data.")
            }
            5 -> {
                print("Enter id to delete: "); val id = input.nextInt()
                if (ideaManager.remove(id)) println("Deleted!") else println("Not found.")
            }
            6 -> {
                print("Enter genre: "); val genre = input.next()
                val result = ArrayList<ie.setu.model.Idea>()
                val all = ideaManager.getListIdeas()
                for (i in all) if (i.genre.equals(genre, true)) result.add(i)
                if (result.isEmpty()) println("No ideas with this genre.") else for (r in result) println(r)
            }
            7 -> {
                print("Enter developerId: "); val devId = input.nextInt()
                val all = ideaManager.getListIdeas()
                var count = 0; var sumEffort = 0; var sumSales = 0.0
                for (i in all) {
                    if (i.developerId == devId) {
                        count++; sumEffort += i.projectEffortHours; sumSales += i.projectedSales
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
