package ie.setu.main.menu

/*
 -------------------------------------------------
 |  GAME IDEA PLANNER - DEVELOPERS MENU          |
 |                                               |
 | 1) Add developer                              |
 | 2) List all developers                        |
 | 3) Find developer by ID                       |
 | 4) Update developer                           |
 | 5) Delete developer                           |
 | 6) List by jobTitle                           |
 | 0) Back to main menu                          |
 -------------------------------------------------
 */

import ie.setu.controller.DeveloperManager
import ie.setu.controller.IdeaManager
import ie.setu.utils.readInt
import ie.setu.utils.readDouble
import ie.setu.utils.readString


fun developersMenu(
    devManager: DeveloperManager,
    ideaManager: IdeaManager
) {
   
    while (true) {
        println("\n--- Developers Menu ---")
        println("1. Add developer")
        println("2. List all developers")
        println("3. Find developer by ID")
        println("4. Update developer")
        println("5. Delete developer")
        println("6. List by jobTitle")
        println("0. Back to main menu")
        when (readInt("Choose option: ")) {
            1 -> {
                val id = readInt("Enter id: ")
                val name = readString("Enter name: ")
                val jobTitle = readString("Enter jobTitle: ")
                val salary = readDouble("Enter salary: ")
                val years = readInt("Enter yearsExperience: ")
                val retired = readString("Is retired (true/false): ").lowercase() == "true"
                val dev = ie.setu.model.Developer(id, name, jobTitle, salary, years, retired)
                if (devManager.add(dev)) println("Added!") else println("Invalid jobTitle!")
            }
            2 -> {
                val list = devManager.getListDevelopers()
                if (list.isEmpty()) {
                    println("No developers.")
                } else {
                    for (d in list) println(d)
                }
            }
            3 -> {
                val id = readInt("Enter id: ")
                val dev = devManager.findById(id)
                if (dev != null) println(dev) else println("Not found.")
            }
            4 -> {
                val id = readInt("Enter id to update: ")
                val name = readString("Enter new name: ")
                val jobTitle = readString("Enter new jobTitle: ")
                val salary = readDouble("Enter new salary: ")
                val years = readInt("Enter new yearsExperience: ")
                val retired = readString("Is retired (true/false): ").lowercase() == "true"
                val dev = ie.setu.model.Developer(id, name, jobTitle, salary, years, retired)
                val ok = devManager.update(id, dev)
                if (ok) println("Updated!") else println("Nothing changed or bad data.")
            }
            5 -> {
                val id = readInt("Enter id to delete: ")
                if (devManager.remove(id)) println("Deleted!") else println("Not found.")
            }
            6 -> {
                val jobTitle = readString("Enter jobTitle: ")
                val result = ArrayList<ie.setu.model.Developer>()
                val all = devManager.getListDevelopers()
                for (d in all) if (d.jobTitle.equals(jobTitle, true)) result.add(d)
                if (result.isEmpty()) println("No developers with this jobTitle.") else for (r in result) println(r)
            }
            0 -> return
            else -> println("Wrong option!")
        }
    }
}
