package org.example.chess.util

/**
 * An enum to handle names of fxml pages
 */
enum class Page(private val pageName: String) {
    MAIN("main"),
    GAME("game"),
    LOGIN("login"),
    PROFILE("profile");

    /**
     * Returns the path to the fxml file for the page
     *
     * @return The enum value as a string
     */
    fun getFileName(): String {
        return "/org/example/chess/pages/$pageName.fxml"
    }
}