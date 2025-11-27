package ie.setu.controller

import ie.setu.model.Idea
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class IdeaManagerTest {

    private lateinit var emptyManager: IdeaManager
    private lateinit var populatedManager: IdeaManager

    private lateinit var idea1: Idea
    private lateinit var idea2: Idea
    private lateinit var idea3: Idea

    @BeforeEach
    fun setup() {
        emptyManager = IdeaManager()
        populatedManager = IdeaManager()

        idea1 = Idea(
            1, "RPG Quest", 1500.0, 1,
            "rpg", 20, 5, 1000.0, 1500.0, "new"
        )

        idea2 = Idea(
            2, "Shooter prototype", 2500.0, 2,
            "shooter", 40, 10, 2000.0, 3000.0, "prototype"
        )

        idea3 = Idea(
            3, "Puzzle game", 800.0, 1,
            "puzzle", 10, 2, 500.0, 900.0, "planned"
        )

        populatedManager.addIdea(idea1)
        populatedManager.addIdea(idea2)
        populatedManager.addIdea(idea3)
    }

    
}
