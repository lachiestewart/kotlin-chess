package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position
import org.example.chess.util.Direction

class KingMovement : MovementStrategy() {
    override fun getUnfilteredMoves(position: Position, boardState: BoardState): Array<Move> {
        val moves = ArrayList<Move>()

        for (direction in Direction.entries) {
            val newPosition = position + direction.position

            if (!newPosition.isValid()) continue

            val newBoardState = boardState.copy()
            newBoardState.turn = nextTurn(boardState.turn)
            val piece = newBoardState.pieceAt(position)!!

            val targetPiece = newBoardState.pieceAt(newPosition)
            if (targetPiece == null || targetPiece.colour != piece.colour) {
                piece.position = newPosition
                if (targetPiece != null) {
                    newBoardState.removePiece(targetPiece)
                }
                moves.add(Move(newBoardState, newPosition.copy()))
            }
        }
        return moves.toTypedArray()
    }

    override fun toString(): String {
        return "King Movement"
    }
}
