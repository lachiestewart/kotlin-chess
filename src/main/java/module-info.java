module org.example.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires kotlin.reflect;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.chess.controller to javafx.fxml;
    opens org.example.chess.movement to kotlin.reflect;
    exports org.example.chess;
}