package org.example.chess.service

import org.example.chess.entity.Piece
import org.example.chess.entity.Position
import org.example.chess.util.Colour
import org.example.chess.util.PieceType

/**
 * Handles piece related services
 */
class PieceService {

    /**
     * Creates a new piece
     *
     * @param position - the position of the piece
     * @param pieceType - the piece type to create
     * @return the new piece
     */
    fun createPiece(position: Position, pieceType: PieceType, pieceColour: Colour): Piece {
        return Piece(position, pieceType, pieceColour)
    }
}