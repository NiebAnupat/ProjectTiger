package com.tiger.CustomControl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class LineItemControl {

    private String itemName;
    private int itemPrice;

    public LineItemControl(String itemName, int itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    private HBox generateLineItem() {

        HBox lineItem = new HBox();

        Label lblItemName = new Label(itemName);
        lblItemName.setPrefWidth(140);
        lblItemName.setPrefHeight(42);
        lblItemName.setFont( new Font("Noto Sans Thai Regular", 14));
        lblItemName.setPadding( new javafx.geometry.Insets(0, 0, 0, 20));

        Label lblItemPrice = new Label(String.valueOf(itemPrice));
        lblItemPrice.setPrefWidth(55);
        lblItemPrice.setPrefHeight(42);
        lblItemPrice.setFont( new Font("Noto Sans Thai Regular", 14));
        lblItemPrice.setPadding( new javafx.geometry.Insets(0, 0, 0, 10));

        Spinner<Integer> mySpinner = new Spinner<Integer>();
        mySpinner.setPrefWidth(62);
        mySpinner.setPrefHeight(27);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 1);
        mySpinner.setValueFactory(valueFactory);
        mySpinner.setEditable(true);
        mySpinner.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                valueFactory.setValue(0);
                return;
            }
            try {
                valueFactory.setValue(Integer.parseInt(newValue));
            } catch (NumberFormatException ex) {
                mySpinner.getEditor().setText(oldValue);
            }
        });
        mySpinner.getStylesheets().add( getClass().getResource("CSS/Spinner.css").toExternalForm());
        lineItem.getChildren().addAll(lblItemName, lblItemPrice, mySpinner);
        return lineItem;

    }

    public HBox getLineItem() {
        return generateLineItem();
    }

}