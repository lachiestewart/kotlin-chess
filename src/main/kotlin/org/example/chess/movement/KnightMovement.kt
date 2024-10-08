package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position

class KnightMovement : MovementStrategy() {
    override fun getMoves(position: Position, boardState: BoardState): Array<Move> {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Knight Movement"
    }
}