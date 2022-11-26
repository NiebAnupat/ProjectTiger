package com.tiger.Controller;


import com.tiger.Class.Room.Customer;
import com.tiger.Class.Room.Invoice_Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class RegController implements Initializable {


    @FXML
    private Button btnNext;

    @FXML
    private TextField txtMember;

    @FXML
    private Label txtMember2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnNext.setDisable(true);
        txtMember2.setVisible(false);

        // add text change listener
        txtMember.textProperty().addListener((observableValue, oldValue, newValue) -> {

            // new value must be digits
            if (!newValue.matches("\\d*")) txtMember.setText(newValue.replaceAll("[^\\d]", ""));

            if (newValue.length() > 10) {
                txtMember.setText(oldValue);
                newValue = oldValue;
            }

            if (newValue.length() == 10 && newValue.matches("[0-9]+")) {
                if (Customer.checkMemberId(newValue)) {
                    txtMember2.setText("มีรหัสสมาชิกนี้อยู่แล้ว");
                    txtMember2.setStyle("-fx-text-fill: red");
                    txtMember2.setVisible(true);
                    btnNext.setDisable(true);
                } else {
                    txtMember2.setText("สามารถใช้รหัสสมาชิกนี้ได้");
                    txtMember2.setStyle("-fx-text-fill: green");
                    txtMember2.setVisible(true);
                    btnNext.setDisable(false);
                }
            } else {
                txtMember2.setVisible(false);
                btnNext.setDisable(true);
            }
        });
    }

    @FXML
    void onbtnNext(ActionEvent event) throws IOException {
        Customer customer = new Customer(txtMember.getText());
        Invoice_Member invoice = new Invoice_Member(new Date(), customer);

        // load new scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("receipt.fxml"));

        ReceiptController receiptController = new ReceiptController(invoice);
        loader.setController(receiptController);

        Scene scene = new Scene(loader.load());
        Stage popup = new Stage();
        popup.initStyle(StageStyle.TRANSPARENT);
        popup.setScene(scene);
        popup.show();

        // close current scene
        this.onbtnClose(event);
    }

    @FXML
    void onbtnClose(ActionEvent event) {
        Stage stage = (Stage) btnNext.getScene().getWindow();
        stage.close();
    }

}
