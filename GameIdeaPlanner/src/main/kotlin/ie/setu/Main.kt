package ie.setu

import ie.setu.controller.DeveloperManager
import ie.setu.controller.IdeaManager
import ie.setu.model.Developer
import ie.setu.model.Idea
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
         2-> ideasMenu(ideaManager, input)
         0 ->
             {
                 println("Exit"); return
             }

            else -> println("Option incorrect !")
        }
}
    }



fun developersMenu(
    devManager: DeveloperManager,
    ideaManager: IdeaManager,
    input: Scanner)
    {
        while (true) {
            println("\n--- Developers Menu ---")
            println("1. Add developer")
            println("2. List all developers")
            println("3. Find developer by ID")
            println("4. Update developer")
            println("5. Delete developer")
            println("6. List by jobTitle")
            println("0. Back to main menu")
            print("Choose option: ")
            when (input.nextInt()) {
                1 -> {
                    print("Enter id: "); val id = input.nextInt()
                    print("Enter name: "); val name = input.next()
                    print("Enter jobTitle: "); val jobTitle = input.next()
                    print("Enter salary: "); val salary = input.nextDouble()
                    print("Enter yearsExperience: "); val years = input.nextInt()
                    print("Is retired (true/false): "); val retired = input.nextBoolean()
                    val dev = ie.setu.model.Developer(id, name, jobTitle, salary, years, retired)
                    if (devManager.add(dev))
                        println("Added!")
                    else println("Invalid jobTitle!")
                }
                2 -> {
                    var list = devManager.getListDevelopers()
                    if (list.isEmpty()) {
                        println("No developers.")
                    } else {

                        for (d in list) {
                            println(d)
                        }
                    }
                }
                3 -> {
                    print("Enter id: "); val id = input.nextInt()
                    val dev = devManager.findById(id)
                    if (dev != null) println(dev) else println("Not found.")
                }
                4 -> {
                    print("Enter id to update: ")
                    val id = input.nextInt()
                    print("Enter new name: ")
                    val name = input.next()
                    print("Enter new jobTitle: ")
                    val jobTitle = input.next()
                    print("Enter new salary: ")
                    val salary = input.nextDouble()
                    print("Enter new yearsExperience: ")
                    val years = input.nextInt()
                    print("Is retired (true/false): ")
                    val retired = input.nextBoolean()
                    val dev = ie.setu.model.Developer(id, name, jobTitle, salary, years, retired)
                    val ok = devManager.update(id, dev)
                    if (ok) {
                        println("Updated!")
                    } else {
                        println("Nothing changed or bad data.")
                    }
                }
                5 -> {
                    print("Enter id to delete: "); val id = input.nextInt()
                    if (devManager.remove(id))
                        println("Deleted!")
                    else
                        println("Not found.")
                }
                6 -> {
                    print("Enter jobTitle: ")
                    val jobTitle = input.next()
                    val result = ArrayList<ie.setu.model.Developer>()
                    val all = devManager.getListDevelopers()

                    for (d in all) {
                        if (d.jobTitle.equals(jobTitle, true)) {
                            result.add(d)
                        }
                    }
                    if (result.size == 0) {
                        println("No developers with this jobTitle.")
                    } else {
                        for (r in result) println(r)
                    }
                }
                0 -> return
                else -> println("Wrong option!")
            }
        }
    }


fun ideasMenu(
    ideaManager: IdeaManager,
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
                print("Enter id: ");
                val id = input.nextInt()
                print("Enter description: ");
                val desc = input.next()
                print("Enter minBudget: ");
                val minBudget = input.nextDouble()
                print("Enter developerId: ");
                val devId = input.nextInt()
                print("Enter genre: ");
                val genre = input.next()
                print("Enter projectEffortHours: ");
                val effort = input.nextInt()
                print("Enter actualEffortHours: ");
                val actual = input.nextInt()
                print("Enter projectedCost: ");
                val cost = input.nextDouble()
                print("Enter projectedSales: ");
                val sales = input.nextDouble()
                print("Enter status: ");
                val status = input.next()
                val idea = ie.setu.model.Idea(id, desc, minBudget, devId, genre, effort, actual, cost, sales, status)
                if (ideaManager.addIdea(idea)) println("Added!") else println("Invalid genre or status!")
            }
            2 -> {
                val list = ideaManager.getListIdeas()
                if (list.isEmpty()) {
                    println("No ideas.")
                } else {
                    for (i in list) println(i)
                }
            }
            3 -> {
                print("Enter id: "); val id = input.nextInt()
                val idea = ideaManager.findById(id)
                if (idea != null) println(idea) else println("Not found.")
            }
            4 -> {
                print("Enter id to update: ");
                val id = input.nextInt()

                print("Enter new description: ");
                val desc = input.next()

                print("Enter new minBudget: ");
                val minBudget = input.nextDouble()

                print("Enter new developerId: ");
                val devId = input.nextInt()

                print("Enter new genre: ");
                val genre = input.next()
                print("Enter new projectEffortHours: ");
                val effort = input.nextInt()

                print("Enter new actualEffortHours: ");
                val actual = input.nextInt()

                print("Enter new projectedCost: ");
                val cost = input.nextDouble()

                print("Enter new projectedSales: ");
                val sales = input.nextDouble()

                print("Enter new status: ");
                val status = input.next()

                val idea = ie.setu.model.Idea(id, desc, minBudget, devId, genre, effort, actual, cost, sales, status)
                val ok = ideaManager.update(id, idea)

                if (ok)
                    println("Updated!")
                else
                    println("Not found or bad data.")
            }
            5 -> {
                print("Enter id to delete: "); val id = input.nextInt()
                if (ideaManager.remove(id)) println("Deleted!") else println("Not found.")
            }
            6 -> {
                print("Enter genre: ")
                val genre = input.next()
                val result = ArrayList<ie.setu.model.Idea>()
                val all = ideaManager.getListIdeas()

                for (i in all) {
                    if (i.genre.equals(genre, true)) {
                        result.add(i)
                    }
                }
                if (result.size == 0) {
                    println("No ideas with this genre.")
                } else {
                    for (r in result) println(r)
                }
            }
            7 -> {
                print("Enter developerId: ")
                val devId = input.nextInt()
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

                println("Report for retired devs: (not implemented, need devManager)")
            }
            0 -> return
            else -> println("Wrong option!")
        }
    }
}

fun readInt(prompt: String): Int {
    print(prompt)
    val input = readLine()
    if (input != null && input.isNotEmpty()) {
        return try {
            input.toInt()
        } catch (e: NumberFormatException) {
            println("Invalid number, using 0.")
            0
        }
    }
    return 0
}


fun readDouble(prompt: String): Double {
    print(prompt)
    val input = readLine()

    return try {
        if (input != null && input.isNotEmpty()) {
            input.toDouble()
        } else {
            0.0
        }
    } catch (e: Exception) {
        println("Invalid number, using 0.0.")
        0.0
    }
}


fun readString(prompt: String): String {
    print(prompt)
    val input = readLine()
    if (input != null && input.isNotEmpty()) {
        return input
    }
    return ""
}


