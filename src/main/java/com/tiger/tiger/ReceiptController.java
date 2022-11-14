package com.tiger.tiger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ReceiptController {
    @FXML
    private Button btnClose;

    @FXML
    private Label btnBack;

    @FXML
    private Label btnDone;

    @FXML
    private Label txtDate;

    @FXML
    private Label txtReceiptID;

    @FXML
    private Label txtTime;

    @FXML
    void onbtnDone(MouseEvent event) {

    }

    @FXML
    void onbtnClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
