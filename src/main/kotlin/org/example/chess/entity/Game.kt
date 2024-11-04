package org.example.chess.entity

import org.example.chess.util.PieceType


class Game(val id: Int, val player1: User, val player2: User) {

    private val minNumberOfKings = 2

    private val moves = ArrayList<BoardState>()

    /**
     * Adds the given state to the list of past states/moves
     *
     * @param move - The state to add
     */
    fun addMove(move: BoardState) {
        moves.add(move)
    }

    /**
     * Gets the current state of the board for this game
     *
     * @return The state of the board if a move is present, otherwise null
     */
    fun currentState(): BoardState? {
        if (moves.isEmpty()) {
            return null
        }
        return moves.last()
    }

    /**
     * Checks for the existence of a last board state, then checks that at least 2 kings are present
     *
     * @return True if the game has finished, otherwise false
     */
    fun isFinished(): Boolean {
        val boardState = this.currentState() ?: return false

        val numberOfKings = boardState.pieces.count { it.pieceType === PieceType.KING }

        return numberOfKings < minNumberOfKings
    }
}