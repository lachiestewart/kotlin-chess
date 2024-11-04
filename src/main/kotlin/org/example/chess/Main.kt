package org.example.chess

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import org.example.chess.util.Page
import org.kordamp.bootstrapfx.BootstrapFX

class Main : Application() {

    /**
     * Shows the initial stage
     */
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(Main::class.java.getResource(Page.MAIN.getFileName()))
        val scene = Scene(fxmlLoader.load())
        scene.stylesheets.add(BootstrapFX.bootstrapFXStylesheet())
        stage.title = "Kotlin Chess"
        val inputStream = Main::class.java.getResourceAsStream("/org/example/chess/images/logo.png")
        val image = Image(inputStream)
        stage.icons.add(image)
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}