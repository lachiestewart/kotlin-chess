package org.example.chess.service

import org.example.chess.Main
import org.example.chess.entity.User

class UserService {

    val userFile = Main::class.java.getResource("/org/example/chess/app-state/users.json")?.readText()
    val allUsers = emptyList<User>() // Change this to be the list of users from users.json

    fun getUserByUsernameAndPassword(username: String, password: String): User? {
        println(userFile)

        return null
    }
}