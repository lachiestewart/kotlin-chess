package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position
import org.example.chess.util.Direction

class RookMovement : MovementStrategy() {
    override fun getMoves(position: Position, boardState: BoardState): Array<Move> {
        val moves = ArrayList<Move>()

        for (direction in Direction.straights()) {
            var newPosition = position.copy()

            while (true) {
                newPosition += direction.position

                if (!newPosition.isValid()) break

                val newBoardState = boardState.copy()
                val piece = newBoardState.pieceAt(position)!!

                val targetPiece = newBoardState.pieceAt(newPosition)
                if (targetPiece == null) {
                    piece.position = newPosition
                    moves.add(Move(newBoardState, newPosition.copy()))
                } else if (targetPiece.colour != piece.colour) {
                    piece.position = newPosition
                    moves.add(Move(newBoardState, newPosition.copy()))
                    break
                } else {
                    break
                }
            }
        }


        return filterMoves(moves.toTypedArray())
    }

    override fun toString(): String {
        return "Rook Movement"
    }
}
