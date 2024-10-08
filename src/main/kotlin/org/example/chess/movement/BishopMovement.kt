package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position
import org.example.chess.util.Direction

class BishopMovement : MovementStrategy() {
    override fun getMoves(position: Position, boardState: BoardState): Array<Move> {
        val moves = ArrayList<Move>()

        for (direction in Direction.diagonals()) {
            var newPosition = position.copy()

            while (true) {
                newPosition += direction.position

                if (!newPosition.isValid()) break

                val boardStateCopy = boardState.copy()
                val piece = boardStateCopy.pieceAt(position)!!

                val targetPiece = boardStateCopy.pieceAt(newPosition)
                if (targetPiece == null) {
                    piece.position = newPosition
                    moves.add(Move(boardStateCopy, newPosition.copy()))
                } else if (targetPiece.colour != piece.colour) {
                    piece.position = newPosition
                    moves.add(Move(boardStateCopy, newPosition.copy()))
                    break
                } else {
                    break
                }
            }
        }

        return filterMoves(moves.toTypedArray())
    }

    override fun toString(): String {
        return "Bishop Movement"
    }
}
