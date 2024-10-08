package org.example.chess.entity

import org.example.chess.util.BoardInfo
import org.example.chess.util.Colour

class BoardState {

    val whiteCanCastleKing: Boolean
    val whiteCanCastleQueen: Boolean
    val blackCanCastleKing: Boolean
    val blackCanCastleQueen: Boolean
    val pieces: Array<Piece>
    var enPassantSquare: Position?

    constructor() {
        whiteCanCastleKing = true
        whiteCanCastleQueen = true
        blackCanCastleKing = true
        blackCanCastleQueen = true
        pieces = emptyArray()
        enPassantSquare = null
    }

    constructor(
        whiteCanCastleKing: Boolean,
        whiteCanCastleQueen: Boolean,
        blackCanCastleKing: Boolean,
        blackCanCastleQueen: Boolean,
        pieces: Array<Piece>,
        enPassantSquare: Position?
    ) {
        this.whiteCanCastleKing = whiteCanCastleKing
        this.whiteCanCastleQueen = whiteCanCastleQueen
        this.blackCanCastleKing = blackCanCastleKing
        this.blackCanCastleQueen = blackCanCastleQueen
        this.pieces = pieces
        this.enPassantSquare = null
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
     * Copies this instance of the board state
     */
    fun copy(): BoardState {
        val piecesCopy = pieces.map { it.copy() }.toTypedArray()
        return BoardState(
            whiteCanCastleKing,
            whiteCanCastleQueen,
            blackCanCastleKing,
            blackCanCastleQueen,
            piecesCopy,
            enPassantSquare
        )
    }

    /**
     * May be redundant
     * Changes this instance of the board state moves the piece at the "target" position to the "destination" position,
     * if no piece at the "target" position, nothing will change
     * @param target - the position of the piece to move
     * @param destination - the position to move the target piece to
     */
    fun movePiece(target: Position, destination: Position) {
        val piece = pieceAt(target)
        if (piece != null) {
            piece.position = destination
        }
    }

    /**
     * Prints out this BoardState in a human-readable format
     */
    fun printBoard() {
        for (i in BoardInfo.HEIGHT.value - 1 downTo 0) {
            print("${i + 1} ")

            for (j in 0 until BoardInfo.WIDTH.value) {
                val piece = pieceAt(Position(j, i))
                val displayChar = when {
                    piece != null -> {
                        when (piece.colour) {
                            Colour.WHITE -> piece.pieceType.name.first()
                            Colour.BLACK -> piece.pieceType.name.first().lowercase()
                        }
                    }

                    else -> "."
                }
                print("$displayChar ")
            }
            println()
        }

        print("  ")
        for (j in 0 until BoardInfo.WIDTH.value) {
            print("${'a' + j} ")
        }
        println()
    }

    override fun toString(): String {
        val pieceString = pieces.joinToString(separator = ",\n") { it.toString() }
        return """
            BoardState: {
                pieces: [
                    $pieceString
                ]
            }
        """.trimIndent()
    }

}