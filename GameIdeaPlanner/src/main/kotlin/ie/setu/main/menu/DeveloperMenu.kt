package ie.setu.main.menu

import ie.setu.controller.DeveloperManager
import ie.setu.controller.IdeaManager
import java.util.Scanner


fun developersMenu(
    devManager: DeveloperManager,
    ideaManager: IdeaManager,
    input: Scanner
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
                print("Enter id: "); val id = input.nextInt()
                val dev = devManager.findById(id)
                if (dev != null) println(dev) else println("Not found.")
            }
            4 -> {
                print("Enter id to update: ")
                val id = input.nextInt()
                print("Enter new name: "); val name = input.next()
                print("Enter new jobTitle: "); val jobTitle = input.next()
                print("Enter new salary: "); val salary = input.nextDouble()
                print("Enter new yearsExperience: "); val years = input.nextInt()
                print("Is retired (true/false): "); val retired = input.nextBoolean()
                val dev = ie.setu.model.Developer(id, name, jobTitle, salary, years, retired)
                val ok = devManager.update(id, dev)
                if (ok) println("Updated!") else println("Nothing changed or bad data.")
            }
            5 -> {
                print("Enter id to delete: "); val id = input.nextInt()
                if (devManager.remove(id)) println("Deleted!") else println("Not found.")
            }
            6 -> {
                print("Enter jobTitle: "); val jobTitle = input.next()
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
