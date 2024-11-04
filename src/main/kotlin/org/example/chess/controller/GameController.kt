package org.example.chess.controller

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import org.example.chess.Main
import org.example.chess.entity.*
import org.example.chess.loader.PositionLoader
import org.example.chess.util.BoardInfo

class GameController : Controller() {

    private val squareSize = 50.0

    @FXML
    lateinit var chessBoard: GridPane

    private val colourMap = mapOf(
        1 to "white",
        0 to "gray"
    )

    private lateinit var defaultBoardState: BoardState

    private var squareArray: Array<Array<Pane?>> =
        Array(BoardInfo.HEIGHT.value) { arrayOfNulls(BoardInfo.WIDTH.value) }

    private val player1 = User(1, "Jeff", 1000)
    private val player2 = User(2, "John", 1000)

    private var gameId = 1

    private var game = Game(gameId, player1, player2)

    private var selectedPiece: Piece? = null

    /**
     * Initialises the board with pieces and prepares for rendering
     */
    @FXML
    fun initialize() {

        for (row in 0 until BoardInfo.HEIGHT.value) {
            for (col in 0 until BoardInfo.WIDTH.value) {
                val position = Position(row, col)
                val square = Pane()

                square.style = "-fx-background-color: ${colourMap[(row + col) % 2]};"
                square.minWidth = squareSize
                square.minHeight = squareSize
                square.onMouseClicked = EventHandler { handleSquareClickAction(position) }

                squareArray[row][col] = square
                chessBoard.add(square, row, BoardInfo.WIDTH.value - col)
            }
        }

        val loader = PositionLoader()
        val boardState = loader.loadFEN("default")

        defaultBoardState = boardState.copy()

        game.addMove(boardState)

        render()
    }

    /**
     * Renders the board based on the current boardState.
     */
    private fun render() {
        for (row in 0 until BoardInfo.HEIGHT.value) {
            for (col in 0 until BoardInfo.WIDTH.value) {
                val position = Position(row, col)
                val square = squareArray[row][col]!!

                square.children.clear()

                val piece = game.currentState()!!.pieceAt(position) ?: continue

                val inputStream = Main::class.java.getResourceAsStream(piece.getImageFileName())
                val image = Image(inputStream)
                val imageView = ImageView(image)

                imageView.fitWidth = squareSize
                imageView.fitHeight = squareSize
                imageView.isPreserveRatio = true

                square.children.add(imageView)

            }
        }
    }

    /**
     * Deselects all squares on the grid, returning them to their default colours, gray and white
     */
    private fun deselectAllSquares() {
        squareArray.forEachIndexed { i, row ->
            row.forEachIndexed { j, square ->
                square!!.style = "-fx-background-color: ${colourMap[(i + j) % 2]};"
            }
        }
    }

    /**
     * Selects the square at the given position
     *
     * @param position - The Position of the square to highlight
     */
    private fun selectSquare(position: Position) {
        squareArray[position.x][position.y]!!.style = "-fx-background-color: blue;"
    }

    /**
     * Handles the event of a click of a square on the board
     *
     * @param position - The Position of the square clicked
     */
    private fun handleSquareClickAction(position: Position) {
        deselectAllSquares()

        val boardState = game.currentState()!!

        if (selectedPiece != null) {
            for (move in selectedPiece!!.getMoves(boardState)) {
                if (move.targetPosition == position) {
                    game.addMove(move.boardState)
                    if (game.isFinished()) {
                        println("game over")
                        game = Game(++gameId, player1, player2)
                        game.addMove(defaultBoardState.copy())
                    }
                    selectedPiece = null
                    render()
                    return
                }
            }
        }

        val piece = boardState.pieceAt(position)

        if (piece != null && piece.colour == boardState.turn) {
            for (move in piece.getMoves(boardState)) {
                selectSquare(move.targetPosition)
            }
        }

        selectedPiece = piece
    }
}
