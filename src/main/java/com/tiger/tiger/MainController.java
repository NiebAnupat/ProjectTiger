package com.tiger.tiger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("room.fxml"));
        }catch (IOException e) {

        }
        bpMain.setCenter(parent);
    }

    @FXML
    private BorderPane bpMain;

    @FXML
    private Button btnCafe;

    @FXML
    private Button btnRep;

    @FXML
    private Button btnRg;

    @FXML
    private Button btnRoom;

    @FXML
    void onbtnCafe(ActionEvent event) {
        loadPage("cafe");
    }

    @FXML
    void onbtnReg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(root);

        Stage popup = new Stage();
        popup.initStyle(StageStyle.TRANSPARENT);
        popup.setScene(scene);
        popup.show();
    }

    @FXML
    void onbtnRep(ActionEvent event) {
        loadPage("report");
    }

    @FXML
    void onbtnRoom(ActionEvent event) {
        loadPage("room");
    }

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        }catch (IOException e) {

        }
        bpMain.setCenter(root);
    }
}
