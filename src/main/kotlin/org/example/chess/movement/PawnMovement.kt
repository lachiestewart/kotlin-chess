package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position
import org.example.chess.util.Colour
import org.example.chess.util.Direction

class PawnMovement : MovementStrategy() {

    override fun getUnfilteredMoves(position: Position, boardState: BoardState): Array<Move> {
        val moves = ArrayList<Move>()

        val piece = boardState.pieceAt(position)!!

        val direction = when (piece.colour) {
            Colour.WHITE -> Direction.UP.position
            Colour.BLACK -> Direction.DOWN.position
        }

        val oneSquareForward = position + direction

        val pieceOneSquareInfront = boardState.pieceAt(oneSquareForward) != null
        if (oneSquareForward.isValid() && !pieceOneSquareInfront) {
            val newBoardStateOne = boardState.copy()
            newBoardStateOne.pieceAt(position)!!.position = oneSquareForward
            newBoardStateOne.turn = nextTurn(boardState.turn)
            moves.add(Move(newBoardStateOne, oneSquareForward.copy()))
        }

        val pawnHasMoved = when (piece.colour) {
            Colour.WHITE -> piece.position.y != 1
            Colour.BLACK -> piece.position.y != 6
        }

        val twoSquaresForward = position + direction * 2
        val pieceTwoSquaresInfront = boardState.pieceAt(twoSquaresForward) != null
        if (twoSquaresForward.isValid() && !pawnHasMoved && !pieceOneSquareInfront && !pieceTwoSquaresInfront) {
            val newBoardStateTwo = boardState.copy()
            newBoardStateTwo.pieceAt(position)!!.position = twoSquaresForward
            newBoardStateTwo.turn = nextTurn(boardState.turn)
            moves.add(Move(newBoardStateTwo, twoSquaresForward.copy()))
        }

        val captureDirections = arrayOf(
            Position(1, direction.y),
            Position(-1, direction.y)
        )

        for (captureDirection in captureDirections) {
            val capturePosition = position + captureDirection

            val newBoardState = boardState.copy()
            newBoardState.turn = nextTurn(boardState.turn)

            if (!capturePosition.isValid()) continue

            val targetPiece = newBoardState.pieceAt(capturePosition)

            if (targetPiece != null && targetPiece.colour != piece.colour) {
                newBoardState.removePiece(targetPiece)
                newBoardState.pieceAt(position)!!.position = capturePosition
                moves.add(Move(newBoardState, capturePosition.copy()))
            }
        }

        // TODO implement en passant

        return moves.toTypedArray()
    }

    override fun toString(): String {
        return "Pawn Movement"
    }
}
