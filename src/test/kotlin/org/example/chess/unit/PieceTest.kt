package org.example.chess.unit

import org.example.chess.entity.BoardState
import org.example.chess.entity.Piece
import org.example.chess.entity.Position
import org.example.chess.loader.PositionLoader
import org.example.chess.util.Colour
import org.example.chess.util.PieceType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class PieceTest {

    val loader = PositionLoader()

    private fun getDefaultBoardState(): BoardState {
        return loader.loadFEN("test1")
    }

    private fun getObjectHash(testObject: Any): String {
        return Integer.toHexString(System.identityHashCode(testObject))
    }

    @Test
    fun copyOfPieceReturnsNewInstance() {
        val position = Position(0, 0)
        val piece = Piece(position, PieceType.ROOK, Colour.WHITE)
        val copiedPiece = piece.copy()
        assertNotEquals(getObjectHash(piece), getObjectHash(copiedPiece))
        assertEquals(piece.pieceType, copiedPiece.pieceType)
        assertEquals(piece.colour, copiedPiece.colour)
        assertNotEquals(getObjectHash(piece.position), getObjectHash(copiedPiece.position))
        assertEquals(piece.position, copiedPiece.position)
    }

    @Test
    fun getPieceMovesOnDefaultBoard() {
        val boardState = BoardState()
        val piece = Piece(Position(0, 0), PieceType.ROOK, Colour.WHITE)
        boardState.addPiece(piece)
        val pieceMoves = piece.getMoves(boardState)
        assertEquals(14, pieceMoves.size)
    }
}