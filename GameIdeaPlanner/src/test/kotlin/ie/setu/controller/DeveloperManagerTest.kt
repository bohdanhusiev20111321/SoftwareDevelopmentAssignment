package ie.setu.controller

import ie.setu.model.Developer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DeveloperManagerTest {

    private lateinit var emptyManager: DeveloperManager
    private lateinit var populatedManager: DeveloperManager

<<<<<<< Updated upstream
    private val dev1 = Developer(1, "Alice", "developer", 50000.0, 2, false)
    private val dev2 = Developer(2, "Bob", "developer", 48000.0, 1, false)
    private val dev3 = Developer(3, "Chris", "developer", 60000.0, 4, false)
=======
    private lateinit var dev1: Developer
    private lateinit var dev2: Developer
    private lateinit var dev3: Developer
>>>>>>> Stashed changes

    @BeforeEach
    fun setup() {
        emptyManager = DeveloperManager()
        populatedManager = DeveloperManager()

<<<<<<< Updated upstream
=======
        // just simple sample developers
        dev1 = Developer(1, "Siobhan", "developer", 50000.0, 2, false)
        dev2 = Developer(2, "Bohdan", "developer", 48000.0, 3, false)
        dev3 = Developer(3, "Patrick", "developer", 52000.0, 4, false)

>>>>>>> Stashed changes
        populatedManager.add(dev1)
        populatedManager.add(dev2)
        populatedManager.add(dev3)
    }

<<<<<<< Updated upstream

    @Nested
    inner class AddDevelopers {

        @Test
        fun `adding developer increases list size`() {
            val newDev = Developer(4, "Patrick", "developer", 45000.0, 1, false)

            assertEquals(3, populatedManager.numberOfDevelopersForTest())
            assertTrue(populatedManager.add(newDev))
            assertEquals(4, populatedManager.numberOfDevelopersForTest())
        }

        @Test
        fun `cannot add duplicate id`() {
            assertFalse(populatedManager.add(dev1)) // same ID
        }
    }


    @Nested
    inner class ListAndFindDevelopers {

        @Test
        fun `listing developers when empty returns message`() {
            val result = emptyManager.listDevelopersForTest().lowercase()
            assertTrue(result.contains("no developers"))
        }

        @Test
        fun `listing developers when populated returns formatted text`() {
            val result = populatedManager.listDevelopersForTest().lowercase()

            assertTrue(result.contains("alice"))
            assertTrue(result.contains("bob"))
            assertTrue(result.contains("chris"))
=======
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
>>>>>>> Stashed changes
        }

        @Test
        fun `findById returns correct developer`() {
<<<<<<< Updated upstream
            assertEquals(dev2, populatedManager.findById(2))
        }

        @Test
        fun `findById returns null for invalid id`() {
            assertNull(populatedManager.findById(999))
=======
            val found = populatedManager.findById(2)
            assertEquals(dev2, found)
>>>>>>> Stashed changes
        }
    }
}
