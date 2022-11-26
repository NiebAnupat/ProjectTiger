package com.tiger.Controller;


import com.tiger.Class.Room.Customer;
import com.dlsc.gemsfx.TimePicker;
import com.tiger.Class.Room.RoomType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.util.Date;
import java.util.ResourceBundle;

public class RoomController implements Initializable {


    @FXML
    private Button btnCheck;

    @FXML
    private Button btnNext;

    @FXML
    private ComboBox<String> cbRoom;

    @FXML
    private TimePicker endTimePicker;

    @FXML
    private TimePicker startTimePicker;

    @FXML
    private TextField txtMember;

    @FXML
    private Label txtMember2;

    @FXML
    private Label txtTimeWarning;

    @FXML
    private Label priceLabel;

    @FXML
    private Label hourLabel;

    @FXML
    private Label minuteLabel;

    private Customer customer = null;

    private LocalTime startTime = null;
    private LocalTime endTime = null;

    private double hour = 0;
    private double minute = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (RoomType roomType : RoomType.values()) {
            switch (roomType) {
                case SMALL -> cbRoom.getItems().add("ห้องประชุมเล็ก");
                case LARGE -> cbRoom.getItems().add("ห้องประชุมใหญ่");
                case INDIVIDUAL -> cbRoom.getItems().add("รายบุคคล");
            }
        }

        cbRoom.getSelectionModel().selectFirst();

        // add combo box change listener
        cbRoom.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            switch (newValue) {
                case "ห้องประชุมเล็ก" -> priceLabel.setText("150");
                case "ห้องประชุมใหญ่" -> priceLabel.setText("200");
                case "รายบุคคล" -> priceLabel.setText("40");
            }
        });

        txtTimeWarning.setVisible(false);

        startTimePicker.setTime(null);
        endTimePicker.setTime(null);

        startTimePicker.setDisable(true);
        endTimePicker.setDisable(true);

        btnNext.setDisable(true);
        // add text change listener
        txtMember.textProperty().addListener((observableValue, oldValue, newValue) -> {

            // new value must be not empty
            if (newValue.isEmpty()) {
                txtMember2.setText("กรุณากรอกเบอร์โทรศัพท์มือถือ");
                txtMember2.setStyle("-fx-text-fill: red");
                txtMember2.setVisible(true);
                btnNext.setDisable(true);
                return;
            }

            // new value must be digits
            if (!newValue.matches("\\d*")) {
                txtMember.setText(newValue.replaceAll("[^\\d]", ""));
                return;
            }

            if (newValue.length() > 10) {
                txtMember.setText(oldValue);
                newValue = oldValue;
            }

            if (newValue.length() == 10 && newValue.matches("[0-9]+")) {
                if (Customer.checkMemberId(newValue)) {
                    txtMember2.setText("พบข้อมูลสมาชิก");
                    txtMember2.setStyle("-fx-text-fill: green");
                } else {
                    txtMember2.setText("ไม่พบข้อมูลสมาชิก สมัครเลย!");
                    txtMember2.setStyle("-fx-text-fill: orange");
                }
                txtMember2.setVisible(true);
                startTimePicker.setDisable(false);
            } else {
                txtMember2.setVisible(false);
                startTimePicker.setDisable(true);
            }
        });

        // add time change listener
        startTimePicker.timeProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                endTimePicker.setDisable(false);
                startTime = startTimePicker.getTime();

                if (endTime != null) {
                    if (endTime.isBefore(startTime)) {
                        endTimePicker.setTime(null);
                        endTime = null;
                        txtTimeWarning.setVisible(true);
                        hourLabel.setText("0");
                        minuteLabel.setText("0");
                        return;
                    }
                    this.calculateTime();
                    hourLabel.setText(String.format("%.0f", hour));
                    minuteLabel.setText(String.format("%.0f", minute));
                }

            } else {
                endTimePicker.setDisable(true);
            }
        });

        // add time change listener
        endTimePicker.timeProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                endTime = endTimePicker.getTime();
                // check end time must be after start time
                if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
                    endTimePicker.setTime(null);
                    endTime = null;
                    txtTimeWarning.setVisible(true);
                    hourLabel.setText("0");
                    minuteLabel.setText("0");
                    return;
                } else txtTimeWarning.setVisible(false);


                this.calculateTime();
                hourLabel.setText(String.format("%.0f", hour));
                minuteLabel.setText(String.format("%.0f", minute));

                btnNext.setDisable(false);
            } else {
                btnNext.setDisable(true);
            }


        });

    }

    private void calculateTime() {
        Duration duration = Duration.between(startTime, endTime);
        hour = duration.toHours();
        minute = duration.toMinutes() % 60.0;
    }


    @FXML
    void onbtnCheck(ActionEvent event) {
        txtMember2.setText("เข้าร่วมสมาชิก!");
        assert txtMember.getText() != "";
        customer = new Customer(txtMember.getText());
        if (customer.getMember()) {
            txtMember2.setText("พบข้อมูลสมาชิก!");
            txtMember2.setStyle("-fx-text-fill: green");
        } else {
            txtMember2.setText("ไม่พบข้อมูลสมาชิก สมัครเลย!");
            txtMember2.setStyle("-fx-text-fill: red");
        }
    }

    @FXML
    void onbtnNext(ActionEvent event) throws IOException {

        // get start time
//        Date =

        if (customer != null) {
            Parent root = FXMLLoader.load(getClass().getResource("receipt.fxml"));
            Scene scene = new Scene(root);
            Stage popup = new Stage();
            popup.initStyle(StageStyle.TRANSPARENT);
            popup.setScene(scene);
            popup.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("แจ้งเตือนข้อผิดพลาด");
            alert.setHeaderText("ไม่พบลูกค้า!");
            alert.setContentText("กรุณาตรวจสอบข้อมูลลูกค้าอีกครั้ง!");
            alert.showAndWait();
        }


    }
}