package org.example.chess.entity

import org.example.chess.util.PieceColour


class BoardState(var whiteCanCastle: Boolean, var blackCanCastle: Boolean, private var pieces: Array<Piece>) {

    fun isWinning(): PieceColour? {
        val colourValueMap = pieces.groupingBy { it.pieceColour }.eachCount()
        return colourValueMap.maxByOrNull { it.value }?.key
    }

}