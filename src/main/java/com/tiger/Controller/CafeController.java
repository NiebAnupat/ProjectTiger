package com.tiger.Controller;

import com.tiger.CustomControl.LineItemControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CafeController implements Initializable {

    @FXML
    private Button btnNext;

    @FXML
    private ScrollPane txtMenu;

    @FXML
    private ScrollPane txtOrder;

    @FXML
    private Spinner<Integer> mySpinner;

    @FXML
    private CheckBox cake;

    @FXML
    private CheckBox coldCoffee;

    @FXML
    private CheckBox coldMilk;

    @FXML
    private CheckBox fabpeMilkss;

    @FXML
    private CheckBox hotCoffee;

    @FXML
    private CheckBox hotMilk;

    @FXML
    private CheckBox juice;

    @FXML
    private CheckBox brownie;

    @FXML
    private VBox menuList;

    private String menuName;
    private double menuPrice;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {

//        menuList = new HBox();

        mySpinner.setValueFactory( new SpinnerValueFactory.IntegerSpinnerValueFactory( 1, 15, 1 ) );

        hotMilk.setOnAction( event -> {
            if ( hotMilk.isSelected() ) {
                menuName = hotMilk.getText();
                menuPrice = 25.00;
                try {
                    addMenu( menuName, menuPrice );
                } catch (IOException e) {
                    throw new RuntimeException( e );
                }
            }
        } );
    }

    @FXML
    void onbtnNext (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load( getClass().getResource( "receipt.fxml" ) );
        Scene scene = new Scene( root );

        Stage popup = new Stage();
        popup.initStyle( StageStyle.TRANSPARENT );
        popup.setScene( scene );
        popup.show();
    }

    public void addMenu (String name, double price) throws IOException {
//        LineItemControl lineItem = new LineItemControl( name,price );
        menuList.getChildren().add( new LineItemControl( name,price ).getLineItem());
//        menuList.getChildren().add( lineItem );
    }


}