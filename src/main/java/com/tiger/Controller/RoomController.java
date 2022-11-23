package com.tiger.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> Room =
                FXCollections.observableArrayList("Small", "Large", "Individual");
        chRoom.setItems(Room);
    }

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnNext;

    @FXML
    private ChoiceBox<String> chRoom;

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
