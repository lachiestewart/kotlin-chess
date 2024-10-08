package org.example.chess.entity

data class Move(val boardState: BoardState, val targetPosition: Position) {

    override fun toString(): String {
        return """
            $boardState
            $targetPosition
        """.trimIndent()
    }
}