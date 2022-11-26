package com.tiger.Class;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class myAlert extends Alert {

    public myAlert (AlertType alertType) {
        super( alertType );
    }

    public myAlert (AlertType alertType, String s, ButtonType... buttonTypes) {
        super( alertType, s, buttonTypes );
    }

    public myAlert(){
        super(AlertType.NONE);
    }

    // show error alert
    public void showErrorAlert ( String content) {
        this.setAlertType( AlertType.ERROR );
        setTitle( "แจ้งเตือนข้อผิดพลาด" );
        setHeaderText( "ข้อผิดพลาด" );
        setContentText( content );
        showAndWait();
    }

    // show information alert
    public void showInformationAlert (String content) {
        this.setAlertType( AlertType.INFORMATION );
        setTitle( "แจ้งเตือน" );
        setHeaderText( "แจ้งเตือน" );
        setContentText( content );
        showAndWait();
    }
}