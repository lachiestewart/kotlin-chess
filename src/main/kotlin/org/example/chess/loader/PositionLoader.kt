package org.example.chess.loader

import org.example.chess.entity.BoardState
import org.example.chess.entity.Piece
import org.example.chess.entity.Position
import org.example.chess.util.Colour
import org.example.chess.util.PieceType

class PositionLoader {

    private val pieceMap: Map<Char, PieceType> = mapOf(
        'p' to PieceType.PAWN,
        'n' to PieceType.KNIGHT,
        'b' to PieceType.BISHOP,
        'r' to PieceType.ROOK,
        'q' to PieceType.QUEEN,
        'k' to PieceType.KING,
    )

    private val colourMap: Map<Boolean, Colour> = mapOf(
        true to Colour.WHITE,
        false to Colour.BLACK,
    )

    private val turnMap: Map<String, Colour> = mapOf(
        "w" to Colour.WHITE,
        "b" to Colour.BLACK,
    )

    /**
     * Loads a resource and turns it's contents into an array of strings
     *
     * @param fileName - a string of the file's location
     * @return An array of the file's contents, if the file is not found, an empty array is returned
     */
    private fun getFileContents(fileName: String): Array<String> {
        val inputStream = this::class.java.getResourceAsStream(fileName)
        if (inputStream == null) {
            return emptyArray()
        }
        return inputStream.bufferedReader().useLines { it.toList() }.toTypedArray()
    }

    /**
     * Loads a board state from a file in the board-states directory
     * file contents example:
     *      rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
     *
     * @param fileName - The name of the file to load the game from
     * @return The board state if the game file is found, else null
     */
    fun loadFEN(fileName: String): BoardState {
        val fenFile = getFileContents("/org/example/chess/game-states/$fileName.fen")
        val fenInfo = fenFile[0].split(" ")

        val pieces = ArrayList<Piece>()

        val rows = fenInfo[0].split("/").reversed()
        for (i in rows.indices) {
            var j = 0
            for (char in rows[i]) {
                if (char.digitToIntOrNull() != null) {
                    j += char.digitToInt()
                } else {
                    val position = Position(j, i)
                    val pieceType = pieceMap[char.lowercaseChar()]!!
                    val colour = colourMap[char.isUpperCase()]!!
                    val piece = Piece(position, pieceType, colour)
                    pieces.add(piece)
                    j += 1
                }
            }
        }

        val turn = turnMap[fenInfo[1]]!!
        val castlingRights = fenInfo[2]

        // not bothering with en passant or move count info atm
        val enPassantSquare = fenInfo[3]
        val halfMovesSinceActiveMove = fenInfo[4].toInt()
        val moveNumber = fenInfo[5].toInt()

        val board = BoardState(true, true, true, true, pieces.toTypedArray(), null, turn)

        return board
    }
}