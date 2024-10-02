package org.example.chess.controller

import javafx.fxml.FXML
import javafx.scene.text.Text

class HelloController {

    @FXML
    lateinit var actionTarget: Text

    @FXML
    fun handleSubmitButtonAction() {
        actionTarget.text = "Sign in button pressed"
    }
}