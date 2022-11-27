package com.tiger.Controller;


import com.tiger.Class.DB_Connector;
import com.tiger.Class.Room.Customer;
import com.dlsc.gemsfx.TimePicker;
import com.tiger.Class.Room.Invoice_Room;
import com.tiger.Class.Room.Room;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.Date;
import java.util.ResourceBundle;

public class RoomController implements Initializable {




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

    @FXML
    private Label roomFullLabel;

    private Customer customer = null;

    private LocalTime startTime = null;
    private LocalTime endTime = null;

    private double hour = 0;
    private double minute = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setRoomAvailableNow();
        roomFullLabel.setVisible(false);
        for (RoomType roomType : RoomType.values()) {
            switch (roomType) {
                case SMALL -> cbRoom.getItems().add("ห้องประชุมเล็ก");
                case LARGE -> cbRoom.getItems().add("ห้องประชุมใหญ่");
                case INDIVIDUAL -> cbRoom.getItems().add("รายบุคคล");
            }
        }


        cbRoom.getSelectionModel().selectFirst();

        this.ifRoomFull(RoomType.SMALL);


        // add combo box change listener
        cbRoom.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            switch (newValue) {
                case "ห้องประชุมเล็ก" -> {
                    priceLabel.setText("150");
                    this.ifRoomFull(RoomType.SMALL);
                }
                case "ห้องประชุมใหญ่" -> {
                    priceLabel.setText("250");
                    this.ifRoomFull(RoomType.LARGE);
                }
                case "รายบุคคล" -> {
                    priceLabel.setText("40");
                    this.ifRoomFull(RoomType.INDIVIDUAL);
                }
            }
        });

        txtTimeWarning.setVisible(false);

//        startTimePicker.setTime(null);
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
                if (newValue.isBefore(LocalTime.now())) {
                    startTimePicker.setTime(LocalTime.now());
                    btnNext.setDisable(true);
                    return;
                }
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

    private void ifRoomFull(RoomType roomType) {
        if (Room.isRoomFull(roomType)) {
            roomFullLabel.setVisible(true);
            txtMember.setDisable(true);
            txtMember2.setDisable(true);
        } else {
            roomFullLabel.setVisible(false);
            txtMember.setDisable(false);
            txtMember2.setDisable(false);
        }
    }

    private void setRoomAvailableNow(){

        DB_Connector db = new DB_Connector();

        // select room last_reserved_time that before now
        String sql = "SELECT * FROM room WHERE last_reserved_end < NOW()";

        try {
            ResultSet rs = db.getResultSet(sql);
            while (rs.next()){
                int r_id = rs.getInt("r_id");
                // set room available
                sql = "UPDATE room SET reserved = 0,last_reserved_end = null WHERE r_id = " +r_id;
                if (!db.execute(sql)) return;
                System.out.println("Room " + r_id + " is available now");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML
    void onbtnNext(ActionEvent event) throws IOException {

        // create new customer
        customer = new Customer(txtMember.getText());

        // get room type
        RoomType roomType = null;
        switch (cbRoom.getSelectionModel().getSelectedItem()) {
            case "ห้องประชุมเล็ก" -> roomType = RoomType.SMALL;
            case "ห้องประชุมใหญ่" -> roomType = RoomType.LARGE;
            case "รายบุคคล" -> roomType = RoomType.INDIVIDUAL;
        }

        // create new reservation
        Room room = new Room(roomType, Integer.parseInt(priceLabel.getText()));

        double amount = hour + (minute / 60.0);
        amount = Math.ceil(amount/3);


        // end time to LocalDateTime
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), this.endTime);

        // end time to Timestamp
        Timestamp timestamp = Timestamp.valueOf(endTime);

        // create new invoice room
        Invoice_Room invoiceRoom = new Invoice_Room(new Date(),customer,room,amount,timestamp);

        // load next page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("receipt.fxml"));
        ReceiptController receiptController = new ReceiptController(invoiceRoom);
        loader.setController(receiptController);
        Scene scene = new Scene(loader.load());
        Stage popup = new Stage();
        popup.initStyle(StageStyle.TRANSPARENT);
        popup.setScene(scene);
        popup.show();

        // clear all
        txtMember.setText("");
        txtMember2.setVisible(false);
        startTimePicker.setTime(null);
        endTimePicker.setTime(null);
        cbRoom.getSelectionModel().selectFirst();
        btnNext.setDisable(true);
        hourLabel.setText("0");
        minuteLabel.setText("0");
        txtTimeWarning.setVisible(false);
    }
}