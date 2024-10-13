package org.example.chess.entity

import org.example.chess.util.BoardInfo

/**
 * A class to represent the location of a square/piece on the chess board
 *
 * @param x - The x value of the Position
 * @param y - The y value of the Position
 */
class Position(val x: Int, val y: Int) {

    /**
     * Used to check if the Position is on the board
     *
     * @return A boolean true if the Position is on the board otherwise false
     */
    fun isValid(): Boolean {
        return x >= 0 && x < BoardInfo.WIDTH.value && y >= 0 && y < BoardInfo.HEIGHT.value
    }

    /**
     * Creates a deep copy of this Position
     *
     * @return A new Position instance with the same x and y values as this Position
     */
    fun copy(): Position {
        return Position(x, y)
    }

    /**
     * Implements addition between two Positions
     *
     * @param other - The Position to add
     * @return The addition of this Position and the other Position
     */
    operator fun plus(other: Position): Position {
        return Position(x + other.x, y + other.y)
    }

    /**
     * Implements multiplication between an integer and a Position
     *
     * @param other - The integer with which to multiply the Position
     * @return The multiplication of this Position's x and y values with the other integer
     */
    operator fun times(other: Int): Position {
        return Position(x * other, y * other)
    }

    /**
     * Overrides the equals method to check if two Position objects are equal
     *
     * @param other - The object to compare with
     * @return true if the other object is a Position with the same x and y values, otherwise false
     */
    override fun equals(other: Any?): Boolean {
        if (other !is Position) return false
        return x == other.x && y == other.y
    }

    /**
     * Overrides the hashCode method to ensure consistent hashing
     *
     * @return the hash code of the Position object
     */
    override fun hashCode(): Int {
        return 31 * x + y
    }

    /**
     * Creates a readable string representation of this class
     *
     * @return The string representation
     */
    override fun toString(): String {
        return "($x, $y)"
    }

}