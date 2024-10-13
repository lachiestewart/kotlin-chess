package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position
import org.example.chess.util.Colour
import org.example.chess.util.PieceType

/**
 * A base class to compose piece movement classes with shared functionality
 */
abstract class MovementStrategy {

    private val turnOrderMap = mapOf(
        Colour.WHITE to Colour.BLACK,
        Colour.BLACK to Colour.WHITE
    )

    protected abstract fun getUnfilteredMoves(position: Position, boardState: BoardState): Array<Move>

    /**
     *
     */
    fun getMoves(position: Position, boardState: BoardState): Array<Move> {
        return filterMoves(getUnfilteredMoves(position, boardState))
    }

    /**
     * Filters illegal moves out of the given moves.
     * Can be used to catch out of bounds moving pieces and also moves that would result in the king being in check
     *
     * @param moves - An array of moves to filter
     * @return The filtered array of moves
     */
    fun filterMoves(moves: Array<Move>): Array<Move> {

        val validMoves = ArrayList<Move>()

        for (move in moves) {
            if (!inCheck(move)) {
                validMoves.add(move)
            }
        }

        return validMoves.toTypedArray()
    }

    /**
     * Checks if a move exists for the next player in which they could take the current player's King
     *
     * @param move - The Move being checked
     * @return A Boolean, true if the king is in check otherwise false
     */
    private fun inCheck(move: Move): Boolean {
        val boardState = move.boardState.copy()
        // change to use previous turn
        val currentTurn = nextTurn(boardState.turn)
        val king = boardState.pieces.find { it.colour == currentTurn && it.pieceType == PieceType.KING } ?: return false
        val oppositionsPieces = boardState.pieces.filter { it.colour == boardState.turn }

//        for (piece in oppositionsPieces) {
//            val opponentMoves = getUnfilteredMoves(piece.position, boardState)
//            println("piece ${piece.pieceType} ${piece.position}")
//
//            for (opponentsMove in opponentMoves) {
//                println("   ${opponentsMove.targetPosition}")
////                if (opponentsMove.targetPosition == king.position) {
////                    return true
////                }
//                println("   ${opponentsMove.targetPosition == king.position}")
//            }
//        }

        return false
    }

    /**
     * Finds whose turn it is next
     *
     * @param colour - The Colour of the current player
     * @return The Colour of the player whose turn is next
     */
    fun nextTurn(colour: Colour): Colour {
        return turnOrderMap[colour]!!
    }
}