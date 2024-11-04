package org.example.chess.controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import org.example.chess.service.UserService
import org.example.chess.util.Page

class LoginController : Controller() {

    val userService = UserService()

    lateinit var usernameField: TextField
    lateinit var passwordField: PasswordField

    @FXML
    fun handleLoginButtonAction(event: ActionEvent) {
        val username = usernameField.text
        val password = passwordField.text

        println("$username:$password")

        val user = userService.getUserByUsernameAndPassword(username, password)

        if (user != null) {
            navigateTo(event, Page.MAIN)
        }
    }
}