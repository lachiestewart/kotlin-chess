package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position

class KnightMovement : MovementStrategy() {

    private val knightMoves = arrayOf(
        Position(2, 1),
        Position(2, -1),
        Position(-2, 1),
        Position(-2, -1),
        Position(1, 2),
        Position(1, -2),
        Position(-1, 2),
        Position(-1, -2)
    )

    override fun getMoves(position: Position, boardState: BoardState): Array<Move> {
        val moves = ArrayList<Move>()

        for (move in knightMoves) {
            val newPosition = position + move
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
        return "Knight Movement"
    }
}
