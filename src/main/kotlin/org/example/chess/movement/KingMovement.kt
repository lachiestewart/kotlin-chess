package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Position

class KingMovement : MovementStrategy {
    override fun getMoves(position: Position, boardState: BoardState): List<BoardState> {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "King Movement"
    }
}