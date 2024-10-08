package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position
import org.example.chess.util.Direction

class KingMovement : MovementStrategy() {
    override fun getMoves(position: Position, boardState: BoardState): Array<Move> {
        val moves = ArrayList<Move>()
        for (direction in Direction.entries) {
            val boardStateCopy = boardState.copy()
            val piece = boardStateCopy.pieceAt(position)!!
            piece.position += direction.position
            val newMove = Move(boardStateCopy, piece.position.copy())
            moves.add(newMove)
        }
        return filterMoves(moves.toTypedArray())
    }

    override fun toString(): String {
        return "King Movement"
    }
}