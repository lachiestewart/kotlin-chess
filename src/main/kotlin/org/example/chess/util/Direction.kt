package org.example.chess.util

import org.example.chess.entity.Position

/**
 * A class to define constants for directions for piece movement
 *
 * @param position - The Position corresponding to the direction
 */
enum class Direction(val position: Position) {
    RIGHT(Position(1, 0)),
    LEFT(Position(-1, 0)),
    UP(Position(0, 1)),
    DOWN(Position(0, -1)),
    RIGHT_UP(Position(1, 1)),
    RIGHT_DOWN(Position(1, -1)),
    LEFT_DOWN(Position(-1, -1)),
    LEFT_UP(Position(-1, 1));

    /**
     * A companion object to the Direction class which provides ability to statically call the functions contained within it
     */
    companion object {

        /**
         * Gets the enum values of the straight directions for piece movement e.g. queens and rooks
         * @return an array of the 4 straight directions
         */
        fun straights(): Array<Direction> {
            return arrayOf(RIGHT, LEFT, UP, DOWN)
        }

        /**
         * Gets the enum values of diagonal directions for piece movement e.g. queens and bishops
         * @return an array of the 4 diagonal directions
         */
        fun diagonals(): Array<Direction> {
            return arrayOf(RIGHT_UP, RIGHT_DOWN, LEFT_DOWN, LEFT_UP)
        }
    }

    /**
     * Overrides the default to string method
     *
     * @return The enum value as a string
     */
    override fun toString(): String {
        return name
    }
}