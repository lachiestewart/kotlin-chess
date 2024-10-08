package org.example.chess.controller

import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Scene
import javafx.stage.Stage
import org.example.chess.Main
import org.example.chess.util.Page
import org.kordamp.bootstrapfx.BootstrapFX

abstract class Controller {

    /**
     * Changes the scene displayed on the current stage
     *
     * @param event - The fired by the current page, used to get the current global stage object
     * @param page - The page to change to
     */
    fun navigateTo(event: ActionEvent, page: Page) {
        val stage = (event.source as Node).scene.window as Stage
        val fxmlLoader = FXMLLoader(Main::class.java.getResource(page.getFileName()))
        val newScene = Scene(fxmlLoader.load())
        newScene.stylesheets.add(BootstrapFX.bootstrapFXStylesheet())
        stage.scene = newScene
    }
}