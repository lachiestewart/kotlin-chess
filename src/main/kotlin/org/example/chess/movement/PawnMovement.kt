package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position
import org.example.chess.util.Colour
import org.example.chess.util.Direction

class PawnMovement : MovementStrategy() {
    override fun getMoves(position: Position, boardState: BoardState): Array<Move> {
        val newBoardState = boardState.copy()
        val piece = newBoardState.pieceAt(position)!!

        val direction = when (piece.colour) {
            Colour.WHITE -> Direction.UP.position
            Colour.BLACK -> Direction.DOWN.position
        }

        piece.position += direction
        val move = Move(newBoardState, piece.position.copy())
        return filterMoves(arrayOf(move))
    }

    override fun toString(): String {
        return "Pawn Movement"
    }
}