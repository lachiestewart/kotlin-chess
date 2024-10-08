package org.example.chess.util

/**
 * An enum to handle the colours of pieces/squares
 */
enum class Colour {
    WHITE,
    BLACK;

    /**
     * Overrides the default to string method
     *
     * @return The enum value as a string
     */
    override fun toString(): String {
        return name.lowercase()
    }
}