package org.example.chess.unit

import org.example.chess.entity.Position
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PositionTest {

    private fun getObjectHash(testObject: Any): String {
        return Integer.toHexString(System.identityHashCode(testObject))
    }

    @Test
    fun positionsOnBoardIsValidReturnsTrue() {
        val positionsList = ArrayList<Position>()
        for (i in 0..7) {
            for (j in 0..7) {
                positionsList.add(Position(i, j))
            }
        }
        for (position in positionsList) {
            assertTrue(position.isValid())
        }
    }

    @Test
    fun positionsHaveSameXAndYEqualsReturnsTrue() {
        val positionsList1 = ArrayList<Position>()
        for (i in 0..7) {
            for (j in 0..7) {
                positionsList1.add(Position(i, j))
            }
        }
        
        val positionsList2 = ArrayList<Position>()
        for (i in 0..7) {
            for (j in 0..7) {
                positionsList2.add(Position(i, j))
            }
        }

        for (i in positionsList1.indices) {
            assertEquals(positionsList1[i], positionsList2[i])
            assertNotEquals(getObjectHash(positionsList1[i]), getObjectHash(positionsList2[i]))
        }
    }

    @Test
    fun positionsOffBoardIsValidReturnsFalse() {
        val positionsList = ArrayList<Position>()
        positionsList.add(Position(-1, -1))
        positionsList.add(Position(8, 8))
        positionsList.add(Position(Int.MAX_VALUE, 0))
        for (position in positionsList) {
            assertFalse(position.isValid())
        }
    }

    @Test
    fun copyOfPositionReturnsNewInstance() {
        val position = Position(0, 0)
        val newPosition = position.copy()
        assertNotEquals(getObjectHash(position), getObjectHash(newPosition))
        assertEquals(position, newPosition)
    }

    @Test
    fun addPositionsResultIsCorrectAndNewInstance() {
        val position1 = Position(0, 0)
        val position2 = Position(1, 1)
        val newPosition = position1 + position2
        assertEquals(Position(1, 1), newPosition)
        assertNotEquals(getObjectHash(position1), getObjectHash(newPosition))
        assertNotEquals(getObjectHash(position2), getObjectHash(newPosition))
    }

    @Test
    fun multiplePositionByIntResultIsCorrectAndNewInstance() {
        val position1 = Position(1, 1)
        val newPosition = position1 * 3
        val expectedPosition = Position(3, 3)
        assertEquals(newPosition, expectedPosition)
        assertNotEquals(getObjectHash(position1), getObjectHash(newPosition))
    }
}