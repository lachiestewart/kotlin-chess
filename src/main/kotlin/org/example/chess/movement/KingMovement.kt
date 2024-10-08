package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position
import org.example.chess.util.Direction

class KingMovement : MovementStrategy() {
    override fun getMoves(position: Position, boardState: BoardState): Array<Move> {
        val moves = ArrayList<Move>()

        for (direction in Direction.entries) {
            val newPosition = position + direction.position

            if (!newPosition.isValid()) continue

            val boardStateCopy = boardState.copy()
            val piece = boardStateCopy.pieceAt(position)!!

            val targetPiece = boardStateCopy.pieceAt(newPosition)
            if (targetPiece == null || targetPiece.colour != piece.colour) {
                piece.position = newPosition
                moves.add(Move(boardStateCopy, newPosition.copy()))
            }
        }
        return filterMoves(moves.toTypedArray())
    }

    override fun toString(): String {
        return "King Movement"
    }
}
