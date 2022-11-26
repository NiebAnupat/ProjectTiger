package com.tiger.CustomControl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class LineReceiptControl extends HBox {

    @FXML
    private Label ListName;

    @FXML
    private Label ListPrice;

    @FXML
    private Label ListUnt;

    public LineReceiptControl() throws RuntimeException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lineRec.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("LineReceiptControl: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setListName(String name) {
        ListName.setText(name);
    }

    public void setListUnt(int unt) {
        ListUnt.setText(String.valueOf(unt));
    }

    public void setListPrice(int price) {
        ListPrice.setText(String.valueOf(price));
    }

    public String getListName() {
        return ListName.getText();
    }

    public String getListUnt() {
        return ListUnt.getText();
    }

    public String getListPrice() {
        return ListPrice.getText();
    }


}
