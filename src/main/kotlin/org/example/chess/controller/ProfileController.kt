package org.example.chess.controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.text.Text
import org.example.chess.util.Page

class ProfileController : Controller() {

    @FXML
    lateinit var actionTarget: Text

    @FXML
    fun handleLoginButtonAction(event: ActionEvent) {
        actionTarget.text = "Sign in button pressed on profile page"
        navigateTo(event, Page.MAIN)
    }
}