package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Move
import org.example.chess.entity.Position

abstract class MovementStrategy {
    abstract fun getMoves(position: Position, boardState: BoardState): Array<Move>

    fun filterMoves(moves: Array<Move>): Array<Move> {

        val validMoves = ArrayList<Move>()

        for (move in moves) {
            val isValidBoard = move.boardState.pieces.all { piece -> piece.position.isValid() }
            if (isValidBoard) {
                validMoves.add(move)
            }
        }

        return validMoves.toTypedArray()
    }
}