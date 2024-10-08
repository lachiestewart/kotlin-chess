package org.example.chess.entity


class Game(val id: Int, val player1: User, val player2: User) {

    val moves = ArrayList<BoardState>()

    fun addMove(move: BoardState) {
        moves.add(move)
    }
}