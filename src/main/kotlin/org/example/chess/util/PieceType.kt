package org.example.chess.util

import org.example.chess.movement.*
import kotlin.Int.Companion.MAX_VALUE
import kotlin.reflect.KClass

/**
 * An enum to store info about value and movement strategies for pieces
 *
 * @param value - the relative value of the piece
 * @param movementStrategy - the class to instantiate for the movement strategy
 */
enum class PieceType(val value: Int, val movementStrategy: KClass<out MovementStrategy>, val charRepr: Char) {
    PAWN(1, PawnMovement::class, 'p'),
    KNIGHT(3, KnightMovement::class, 'n'),
    BISHOP(3, BishopMovement::class, 'b'),
    ROOK(5, RookMovement::class, 'r'),
    QUEEN(9, QueenMovement::class, 'q'),
    KING(MAX_VALUE, KingMovement::class, 'k');

    /**
     * Overrides the default to string method
     *
     * @return The enum value as a string
     */
    override fun toString(): String {
        return name.lowercase()
    }
}