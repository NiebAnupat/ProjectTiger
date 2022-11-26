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
    private Spinner<Integer> nSpinner;

    @FXML
    private TimePicker startTimePicker;

    @FXML
    private TextField txtMember;

    @FXML
    private Label txtMember2;

    private Customer customer = null;


    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        for (RoomType roomType : RoomType.values()) {
            switch (roomType) {
                case SMALL -> cbRoom.getItems().add( "ห้องเล็ก" );
                case LARGE -> cbRoom.getItems().add( "ห้องใหญ่" );
                case INDIVIDUAL -> cbRoom.getItems().add( "ห้องเดี่ยว" );
            }
        }

        nSpinner.setValueFactory( new SpinnerValueFactory.IntegerSpinnerValueFactory( 1, 11, 1 ) );

        // Add listener txtMember to check if it is empty or change
        txtMember.textProperty().addListener( (observableValue, s, t1) -> {
            txtMember2.setText( null );
            customer = null;
        } );


    }


    @FXML
    void onbtnCheck (ActionEvent event) {
        txtMember2.setText( "เข้าร่วมสมาชิก!" );
        assert txtMember.getText() != "";
        customer = new Customer( txtMember.getText() );
        if ( customer.getMember() ) {
            txtMember2.setText( "พบข้อมูลสมาชิก!" );
            txtMember2.setStyle( "-fx-text-fill: green" );
        } else {
            txtMember2.setText( "ไม่พบข้อมูลสมาชิก สมัครเลย!" );
            txtMember2.setStyle( "-fx-text-fill: red" );
        }
    }

    @FXML
    void onbtnNext (ActionEvent event) throws IOException {

        // get start time
//        Date =

        if ( customer != null ) {
            Parent root = FXMLLoader.load( getClass().getResource( "receipt.fxml" ) );
            Scene scene = new Scene( root );
            Stage popup = new Stage();
            popup.initStyle( StageStyle.TRANSPARENT );
            popup.setScene( scene );
            popup.show();
        } else {
            Alert alert = new Alert( Alert.AlertType.ERROR );
            alert.setTitle( "แจ้งเตือนข้อผิดพลาด" );
            alert.setHeaderText( "ไม่พบลูกค้า!" );
            alert.setContentText( "กรุณาตรวจสอบข้อมูลลูกค้าอีกครั้ง!" );
            alert.showAndWait();
        }


    }
}