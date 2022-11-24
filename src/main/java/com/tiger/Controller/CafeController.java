package com.tiger.Controller;

import com.tiger.CustomControl.LineItemControl;
import javafx.collections.ObservableList;
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
    private CheckBox brownie;

    @FXML
    private Button btnNext;

    @FXML
    private CheckBox cake;

    @FXML
    private CheckBox coldCoffee;

    @FXML
    private CheckBox coldMilk;

    @FXML
    private CheckBox fabpeMilk;

    @FXML
    private CheckBox hotCoffee;

    @FXML
    private HBox hotMilk;

    @FXML
    private CheckBox juice;

    @FXML
    private ListView<HBox> menuList;

    private String menuName;
    private int menuPrice;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        //        menuList = new HBox();
        //        hotMilk.setOnAction( event -> {
        //            setMenuEvent(event);
        //        } );
    }

    public void setMenuEvent (ActionEvent event) throws IOException {
        Object obj = event.getSource();
        if ( obj instanceof CheckBox ) {
            CheckBox cb = (CheckBox) obj;
            menuName = cb.getText();
            switch (menuName){
                case "นมร้อน" -> menuPrice = 25;
                case "นมเย็น" -> menuPrice = 30;
                case "นมปั่น" -> menuPrice = 35;
                case "กาแฟร้อน" -> menuPrice = 30;
                case "กาแฟเย็น" -> menuPrice = 35;
                case "น้ำผลไม้" -> menuPrice = 30;
                case "เค้ก" -> menuPrice = 35;
                case "บราวนี่" -> menuPrice = 25;
                default -> menuPrice = 0;
            }
            if ( cb.isSelected() ) {
                addMenu( menuName, menuPrice );
            } else {
                removeMenu( menuName, menuPrice );
            }
        } else {
            throw new RuntimeException( "Not CheckBox" );
        }

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

    private void addMenu (String name, int price) throws IOException {
        menuList.getItems().add( new LineItemControl( name, price ).getLineItem() );
    }

    private void removeMenu (String name, int price) throws IOException {
        ObservableList<HBox> items = menuList.getItems();
        // remove item from list where name match
        for ( HBox item : items ) {
            if ( item.getChildren().get( 0 ).toString().contains( name ) ) {
                items.remove( item );
                System.out.println( "Removed : " + name );
                break;
            }
        }
    }

}