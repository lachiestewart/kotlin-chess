package org.example.chess.entity

data class Position(val x: Int, val y: Int) {

    override fun toString(): String {
        return "($x, $y)"
    }
}