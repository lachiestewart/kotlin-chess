package org.example.chess.service

import org.example.chess.entity.Piece
import org.example.chess.entity.Position
import org.example.chess.util.PieceColour
import org.example.chess.util.PieceInfo

/**
 * Handles piece related services
 */
class PieceService {

    /**
     * Creates a new piece
     *
     * @param x - the x position of the piece
     * @param y - the y position of the piece
     * @param pieceInfo - the piece type to create
     * @return the new piece
     */
    fun createPiece(x: Int, y: Int, pieceInfo: PieceInfo, pieceColour: PieceColour): Piece {
        val position = Position(x, y)
        val movementStrategy = pieceInfo.movementStrategyClass.constructors.first().call()
        val piece = Piece(position, movementStrategy, pieceColour)
        return piece
    }
}