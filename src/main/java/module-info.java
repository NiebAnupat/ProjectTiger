module com.tiger.tiger {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.tiger.contoller to javafx.fxml;
    exports com.tiger.contoller;
}