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

    override fun getUnfilteredMoves(position: Position, boardState: BoardState): Array<Move> {
        val moves = ArrayList<Move>()

        for (move in knightMoves) {
            val newPosition = position + move

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
        return "Knight Movement"
    }
}
