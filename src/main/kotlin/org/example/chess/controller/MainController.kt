package org.example.chess.controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.text.Text
import org.example.chess.util.Page

class MainController : Controller() {

    @FXML
    lateinit var actionTarget: Text

    @FXML
    fun initialize() {
        actionTarget.text = "Welcome to the Chess Game!"
    }

    @FXML
    fun handleSubmitButtonAction(event: ActionEvent) {
        navigateTo(event, Page.PROFILE)
    }

    @FXML
    fun handlePlayButtonAction(event: ActionEvent) {
        navigateTo(event, Page.GAME)
    }

    @FXML
    fun handleLoginButtonAction(event: ActionEvent) {
        navigateTo(event, Page.LOGIN)
    }

}