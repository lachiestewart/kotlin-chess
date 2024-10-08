package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position
import org.example.chess.util.Colour
import org.example.chess.util.Direction

class PawnMovement : MovementStrategy() {

    override fun getMoves(position: Position, boardState: BoardState): Array<Move> {
        val moves = ArrayList<Move>()

        val piece = boardState.pieceAt(position)!!

        val direction = when (piece.colour) {
            Colour.WHITE -> Direction.UP.position
            Colour.BLACK -> Direction.DOWN.position
        }

        val oneSquareForward = position + direction
        if (oneSquareForward.isValid() && boardState.pieceAt(oneSquareForward) == null) {
            moves.add(Move(boardState.copy(), oneSquareForward.copy()))
        }

        val pieceHasMoved = when (piece.colour) {
            Colour.WHITE -> piece.position.y != 1
            Colour.BLACK -> piece.position.y != 6
        }

        val twoSquaresForward = position + direction * 2
        if (!pieceHasMoved && twoSquaresForward.isValid() && boardState.pieceAt(twoSquaresForward) == null) {
            moves.add(Move(boardState.copy(), twoSquaresForward.copy()))
        }

        val captureDirections = arrayOf(
            Position(1, direction.y),
            Position(-1, direction.y)
        )

        for (captureDirection in captureDirections) {
            val capturePosition = position + captureDirection
            if (capturePosition.isValid()) {
                val targetPiece = boardState.pieceAt(capturePosition)

                if (targetPiece != null && targetPiece.colour != piece.colour) {
                    moves.add(Move(boardState.copy(), capturePosition.copy()))
                }
            }
        }

        return filterMoves(moves.toTypedArray())
    }

    override fun toString(): String {
        return "Pawn Movement"
    }
}
