module com.tiger.tiger {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires com.dlsc.gemsfx;
    requires se.alipsa.ymp;


    opens com.tiger.Controller to javafx.fxml;
    opens com.tiger.CustomControl to javafx.fxml;
    exports com.tiger.Controller;
    exports com.tiger.CustomControl;
}