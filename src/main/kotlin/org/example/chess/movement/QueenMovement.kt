package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position
import org.example.chess.util.Direction

class QueenMovement : MovementStrategy() {
    override fun getUnfilteredMoves(position: Position, boardState: BoardState): Array<Move> {
        val moves = ArrayList<Move>()

        for (direction in Direction.entries) {
            var currentPosition = position

            while (true) {
                currentPosition += direction.position

                if (!currentPosition.isValid()) break

                val newBoardState = boardState.copy()
                newBoardState.turn = nextTurn(boardState.turn)
                val piece = newBoardState.pieceAt(position)!!

                val targetPiece = newBoardState.pieceAt(currentPosition)
                if (targetPiece == null) {
                    piece.position = currentPosition
                    moves.add(Move(newBoardState, currentPosition.copy()))
                    continue
                }

                if (targetPiece.colour != piece.colour) {
                    piece.position = currentPosition
                    newBoardState.removePiece(targetPiece)
                    moves.add(Move(newBoardState, currentPosition.copy()))
                }

                break
            }
        }

        return moves.toTypedArray()
    }

    override fun toString(): String {
        return "Queen Movement"
    }
}
