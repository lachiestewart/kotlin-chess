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
     * @param o - The Position to add
     * @return The addition of this Position and the Position o
     */
    operator fun plus(o: Position): Position {
        return Position(x + o.x, y + o.y)
    }

    /**
     * Implements subtraction between two Positions
     *
     * @param o - The Position to subtract
     * @return The result of this position minus the Position o
     */
    operator fun minus(o: Position): Position {
        return Position(x - o.x, y - o.y)
    }

    /**
     * Implements multiplication between an integer and a Position
     *
     * @param o - The integer with which to multiply the Position
     * @return The multiplication of this Position's x and y values with the integer o
     */
    operator fun times(o: Int): Position {
        return Position(x * o, y * o)
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