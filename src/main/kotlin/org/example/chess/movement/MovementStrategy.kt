package org.example.chess.movement

import org.example.chess.entity.BoardState
import org.example.chess.entity.Position

interface MovementStrategy {
    fun getMoves(position: Position, boardState: BoardState): List<BoardState>
}