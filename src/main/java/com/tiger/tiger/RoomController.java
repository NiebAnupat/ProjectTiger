package com.tiger.tiger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RoomController {

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnNext;

    @FXML
    private ChoiceBox<?> chRoom;

    @FXML
    private TextField txtBook;

    @FXML
    private TextField txtMember;

    @FXML
    private Label txtMember2;

    @FXML
    private TextField txtReturn;

    @FXML
    void onbtnCheck(ActionEvent event) {
        txtMember2.setText("เข้าร่วมสมาชิก!");
    }

    @FXML
    void onbtnNext(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("receipt.fxml"));
        Scene scene = new Scene(root);

        Stage popup = new Stage();
        popup.initStyle(StageStyle.TRANSPARENT);
        popup.setScene(scene);
        popup.show();
    }
}
