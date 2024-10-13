package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position
import org.example.chess.util.Direction

class BishopMovement : MovementStrategy() {
    override fun getUnfilteredMoves(position: Position, boardState: BoardState): Array<Move> {
        val moves = ArrayList<Move>()

        for (direction in Direction.diagonals()) {
            var newPosition = position.copy()

            while (true) {
                newPosition += direction.position

                if (!newPosition.isValid()) break

                val newBoardState = boardState.copy()
                newBoardState.turn = nextTurn(boardState.turn)
                val piece = newBoardState.pieceAt(position)!!

                val targetPiece = newBoardState.pieceAt(newPosition)
                if (targetPiece == null) {
                    piece.position = newPosition
                    moves.add(Move(newBoardState, newPosition.copy()))
                    continue
                }

                if (targetPiece.colour != piece.colour) {
                    newBoardState.removePiece(targetPiece)
                    piece.position = newPosition
                    moves.add(Move(newBoardState, newPosition.copy()))
                }

                break
            }
        }

        return moves.toTypedArray()
    }

    override fun toString(): String {
        return "Bishop Movement"
    }
}
