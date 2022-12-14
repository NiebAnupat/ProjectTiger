package com.tiger.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class TigerCWS extends Application {
    @Override
    public void start (Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader( TigerCWS.class.getResource( "main.fxml" ) );
        Scene scene = new Scene( fxmlLoader.load() );
        stage.setTitle( "Tiger Co-Working Space" );
        stage.setScene( scene );
        stage.show();
    }

    public static void main (String[] args) {
        launch();
    }
}