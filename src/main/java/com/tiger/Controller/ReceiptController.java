package com.tiger.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ReceiptController {
    @FXML
    private ListView<HBox> List;

    @FXML
    private Label ListName;

    @FXML
    private Label ListPrice;

    @FXML
    private Label RecDate;

    @FXML
    private Label RecName;

    @FXML
    private Label RecTime;

    @FXML
    private Label ReceiptID;

    @FXML
    private Label Tax;

    @FXML
    private Label Total;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnSubmit;

    @FXML
    private Label subTotal;

    @FXML
    void onbtnCancel(ActionEvent event) {

    }

    @FXML
    void onbtnSubmit(ActionEvent event) {

    }

    @FXML
    void onbtnClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
