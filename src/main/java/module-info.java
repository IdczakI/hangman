module NewHangman {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports pl.idczak.newHangman.main to javafx.graphics;
    opens pl.idczak.newHangman.controller to javafx.fxml;
}