package com.tiger.contoller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RegController {

    @FXML
    private Button btnNext;

    @FXML
    private Button btnClose;

    @FXML
    private TextField txtMember;

    @FXML
    private Label txtMemberId;

    @FXML
    void onbtnNext(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("receipt.fxml"));
        Scene scene = new Scene(root);

        Stage popup = new Stage();
        popup.initStyle(StageStyle.TRANSPARENT);
        popup.setScene(scene);
        popup.show();
    }

    @FXML
    void onbtnClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
