package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position

class RookMovement : MovementStrategy() {
    override fun getMoves(positoin: Position, boardState: BoardState): Array<Move> {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Rook Movement"
    }
}