package org.example.chess.controller

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import org.example.chess.entity.BoardState
import org.example.chess.entity.Piece
import org.example.chess.entity.Position
import org.example.chess.loader.PositionLoader
import org.example.chess.util.BoardInfo

class GameController : Controller() {

    private val squareSize = 50.0

    @FXML
    lateinit var chessBoard: GridPane

    private val colourMap = mapOf(
        0 to "white",
        1 to "gray"
    )

    private var squareArray: Array<Array<Pane?>> =
        Array(BoardInfo.HEIGHT.value) { arrayOfNulls(BoardInfo.WIDTH.value) }

    private lateinit var boardState: BoardState

    private var selectedPiece: Piece? = null

    /**
     * Initialises the board with pieces
     */
    @FXML
    fun initialize() {

        val loader = PositionLoader()
        boardState = loader.loadFEN("default-board-state")

        for (row in 0 until BoardInfo.HEIGHT.value) {
            for (col in 0 until BoardInfo.WIDTH.value) {
                val position = Position(row, col)

                val square = Pane()

                square.style = "-fx-background-color: ${colourMap[(row + col) % 2]};"
                square.minWidth = squareSize
                square.minHeight = squareSize
                square.onMouseClicked = EventHandler { handleSquareClickAction(position) }

                squareArray[row][col] = square

                val piece = boardState.pieceAt(position)
                if (piece != null) {
                    val inputStream = this::class.java.getResourceAsStream(piece.getImageFileName())
                    val image = Image(inputStream)
                    val imageView = ImageView(image)

                    imageView.fitWidth = squareSize
                    imageView.fitHeight = squareSize
                    imageView.isPreserveRatio = true

                    square.children.add(imageView)
                }

                chessBoard.add(square, col, row)
            }
        }
    }

    private fun deselectAllSquares() {
        squareArray.forEachIndexed { i, row ->
            row.forEachIndexed { j, square ->
                square!!.style = "-fx-background-color: ${colourMap[(i + j) % 2]};"
            }
        }
    }

    private fun selectSquare(position: Position) {
        squareArray[position.x][position.y]!!.style = "-fx-background-color: blue;"
    }

    /**
     * Handles the event of a click of a square on the board
     *
     * @param position - The Position of the square clicked
     */
    private fun handleSquareClickAction(position: Position) {
        println(position)
        deselectAllSquares()
        val piece = boardState.pieceAt(position)
        if (piece != null) {
            for (move in piece.getMoves(boardState)) {
                println(move.targetPosition)
                selectSquare(move.targetPosition)
            }
        }
    }
}
