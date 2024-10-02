package org.example.chess.util

import org.example.chess.movement.*
import kotlin.Int.Companion.MAX_VALUE
import kotlin.reflect.KClass

/**
 * An enum to store info about value and movement strategies for pieces
 *
 * @param value - the relative value of the piece
 * @param movementStrategyClass - the class to instantiate for the movement strategy
 */
enum class PieceInfo(val value: Int, val movementStrategyClass: KClass<out MovementStrategy>) {
    PAWN(1, PawnMovement::class),
    KNIGHT(3, KnightMovement::class),
    BISHOP(3, BishopMovement::class),
    ROOK(5, RookMovement::class),
    QUEEN(9, QueenMovement::class),
    KING(MAX_VALUE, KingMovement::class);
}