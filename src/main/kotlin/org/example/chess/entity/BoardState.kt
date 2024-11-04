package org.example.chess.entity

import org.example.chess.util.BoardInfo
import org.example.chess.util.Colour

class BoardState {

    val whiteCanCastleKing: Boolean
    val whiteCanCastleQueen: Boolean
    val blackCanCastleKing: Boolean
    val blackCanCastleQueen: Boolean
    var pieces: Array<Piece>
    var enPassantSquare: Position?
    var turn: Colour

    constructor() {
        whiteCanCastleKing = true
        whiteCanCastleQueen = true
        blackCanCastleKing = true
        blackCanCastleQueen = true
        pieces = emptyArray()
        enPassantSquare = null
        turn = Colour.WHITE
    }

    constructor(
        whiteCanCastleKing: Boolean,
        whiteCanCastleQueen: Boolean,
        blackCanCastleKing: Boolean,
        blackCanCastleQueen: Boolean,
        pieces: Array<Piece>,
        enPassantSquare: Position?,
        turn: Colour
    ) {
        this.whiteCanCastleKing = whiteCanCastleKing
        this.whiteCanCastleQueen = whiteCanCastleQueen
        this.blackCanCastleKing = blackCanCastleKing
        this.blackCanCastleQueen = blackCanCastleQueen
        this.pieces = pieces
        this.enPassantSquare = null
        this.turn = turn
    }

    /**
     * A naive method for calculating who is winning, currently just totals the number of pieces for each colour
     */
    fun isWinning(): Colour? {
        val colourValueMap = pieces.groupingBy { it.colour }.eachCount()
        return colourValueMap.maxByOrNull { it.value }?.key
    }

    /**
     * Finds the piece at the given "target" position
     * @param target - The positions of the piece
     * @return The piece found at the given "target" position, or null if no piece at the "target" position
     */
    fun pieceAt(target: Position): Piece? {
        return pieces.find { it.position.x == target.x && it.position.y == target.y }
    }

    /**
     * Removes the given Piece from the pieces on the board
     *
     * @param piece - The Piece to remove
     */
    fun removePiece(piece: Piece) {
        pieces = pieces.filter { it != piece }.toTypedArray()
    }

    /**
     * Adds the given piece to the pieces on the board
     *
     * @param piece - The Piece to add
     */
    fun addPiece(piece: Piece) {
        pieces += piece
    }

    /**
     * Copies this instance of the board state
     *
     * @return The copied board state
     */
    fun copy(): BoardState {
        val piecesCopy = pieces.map { it.copy() }.toTypedArray()
        return BoardState(
            whiteCanCastleKing,
            whiteCanCastleQueen,
            blackCanCastleKing,
            blackCanCastleQueen,
            piecesCopy,
            enPassantSquare,
            turn
        )
    }

    /**
     * Returns this BoardState as a string in a human-readable format
     *
     * @return A string representation of the board
     */
    override fun toString(): String {
        val builder = StringBuilder()

        for (i in BoardInfo.HEIGHT.value - 1 downTo 0) {
            builder.append("${i + 1} ")

            for (j in 0 until BoardInfo.WIDTH.value) {
                val piece = pieceAt(Position(j, i))
                val displayChar = when {
                    piece != null -> {
                        when (piece.colour) {
                            Colour.WHITE -> piece.pieceType.charRepr.uppercase()
                            Colour.BLACK -> piece.pieceType.charRepr
                        }
                    }

                    else -> "."
                }
                builder.append("$displayChar ")
            }
            builder.append("\n")
        }

        builder.append("  ")
        for (j in 0 until BoardInfo.WIDTH.value) {
            builder.append("${'a' + j} ")
        }

        return builder.toString()

    }

}