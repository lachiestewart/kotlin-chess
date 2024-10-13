package org.example.chess.entity

import org.example.chess.movement.MovementStrategy
import org.example.chess.util.Colour
import org.example.chess.util.PieceType

/**
 * A class to represent a Piece on the chess board
 *
 * @param position - The Position of the Piece
 * @param pieceType - The type of this piece
 * @param colour - The colour of the Piece
 */
class Piece(var position: Position, val pieceType: PieceType, val colour: Colour) {

    private val movementStrategy: MovementStrategy = pieceType.movementStrategy.constructors.first().call()

    /**
     * Gets an array of possible moves for this piece in the given board state
     *
     * @param boardState - The state of the board
     * @return The possible moves
     */
    fun getMoves(boardState: BoardState): Array<Move> {
        return movementStrategy.getMoves(position, boardState)
    }

    /**
     * Overrides the default to string method
     *
     * @return The string representation of an instance of Piece
     */
    override fun toString(): String {
        return """
            Piece: {
                pieceType: $pieceType,
                colour: $colour,
                position: $position
            }
        """.trimIndent()
    }

    /**
     * Creates a deep copy of this Piece
     *
     * @return A new Piece object with the same values as this class
     */
    fun copy(): Piece {
        return Piece(position.copy(), pieceType, colour)
    }

    /**
     * Gets the name of the file for this piece
     *
     * @return The file path to the piece's image as a string
     */
    fun getImageFileName(): String {
        return "/org/example/chess/piece-images/$colour/$pieceType.png"
    }
}
