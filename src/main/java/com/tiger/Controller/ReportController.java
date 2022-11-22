package com.tiger.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class ReportController {

    @FXML
    private Button btnPrint;

    @FXML
    private TableColumn<?, ?> columnDate;

    @FXML
    private TableColumn<?, ?> columnList;

    @FXML
    private TableColumn<?, ?> columnPrice;

    @FXML
    void onbtnPrint(ActionEvent event) {

    }

    @FXML
    private Button btnClose;

    @FXML
    void onbtnClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
