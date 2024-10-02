package org.example.chess

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import org.example.chess.entity.BoardState
import org.example.chess.service.PieceService
import org.example.chess.util.PieceColour
import org.example.chess.util.PieceInfo
import org.kordamp.bootstrapfx.BootstrapFX

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        scene.stylesheets.add(BootstrapFX.bootstrapFXStylesheet())
        stage.title = "Chess"
        stage.scene = scene
        stage.show()

        val pieceService = PieceService()

        val piece = pieceService.createPiece(0, 0, PieceInfo.PAWN, PieceColour.WHITE)

        val pieces = arrayOf(piece)

        println(piece)

        val boardState = BoardState(true, true, pieces)

        println(boardState.isWinning())
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}