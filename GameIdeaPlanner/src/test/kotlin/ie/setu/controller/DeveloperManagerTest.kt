package ie.setu.controller

import ie.setu.model.Developer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DeveloperManagerTest {

    private lateinit var emptyManager: DeveloperManager
    private lateinit var populatedManager: DeveloperManager

    private lateinit var dev1: Developer
    private lateinit var dev2: Developer
    private lateinit var dev3: Developer

    @BeforeEach
    fun setup() {
        emptyManager = DeveloperManager()
        populatedManager = DeveloperManager()

        // just simple sample developers
        dev1 = Developer(1, "Siobhan", "developer", 50000.0, 2, false)
        dev2 = Developer(2, "Bohdan", "developer", 48000.0, 3, false)
        dev3 = Developer(3, "Patrick", "developer", 52000.0, 4, false)

        populatedManager.add(dev1)
        populatedManager.add(dev2)
        populatedManager.add(dev3)
    }

    @Nested
    inner class AddTests {

        @Test
        fun `adding one developer adds to the list`() {
            // list empty at start
            assertEquals(0, emptyManager.getListDevelopers().size)

            val added = emptyManager.add(dev1)
            assertTrue(added)

            assertEquals(1, emptyManager.getListDevelopers().size)
        }
    }

    @Nested
    inner class ListAndFindTests {

        @Test
        fun `empty list returns message`() {
            val result = emptyManager.listDevelopersForTest()
            assertTrue(result.contains("no developers", ignoreCase = true))
        }

        @Test
        fun `populated list returns all names`() {
            val result = populatedManager.listDevelopersForTest().lowercase()

            assertTrue(result.contains("siobhan"))
            assertTrue(result.contains("bohdan"))
            assertTrue(result.contains("patrick"))
        }

        @Test
        fun `findById returns correct developer`() {
            val found = populatedManager.findById(2)
            assertEquals(dev2, found)
        }
    }
}
