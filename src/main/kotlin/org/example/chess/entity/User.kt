package org.example.chess.entity

class User(val id: Int, val name: String, val elo: Int) {
    val games = ArrayList<Game>()
}