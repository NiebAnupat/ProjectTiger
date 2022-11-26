module com.tiger.tiger {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires com.dlsc.gemsfx;


    opens com.tiger.Controller to javafx.fxml;
    exports com.tiger.Controller;
}