package org.example.chess.entity

import org.example.chess.movement.MovementStrategy
import org.example.chess.util.PieceColour

class Piece(var position: Position, val movementStrategy: MovementStrategy, val pieceColour: PieceColour) {

    override fun toString(): String {
        return """Piece: {
    position: ${position},
    movementStrategy: ${movementStrategy},
    pieceColour: ${pieceColour},
}"""
    }
}
