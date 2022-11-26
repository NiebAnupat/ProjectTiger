package com.tiger.Controller;

import com.tiger.Class.Cafe.Invoice_Cafe;
import com.tiger.Class.Cafe.LineItem;
import com.tiger.Class.Cafe.Menu;
import com.tiger.CustomControl.LineItemControl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

public class CafeController implements Initializable {

    @FXML
    private Button btnNext;

    @FXML
    private ListView<HBox> menuList;

    @FXML
    private VBox checkBoxGroup;

    @FXML
    private Label subTotal;

    int subTotalValue = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Statement...
        subTotal.setVisible(false);
        btnNext.setDisable(true);
    }


    public void setMenuEvent(ActionEvent event) throws IOException {
        Object obj = event.getSource();
        if (obj instanceof CheckBox) {
            CheckBox cb = (CheckBox) obj;
            String menuName = cb.getText();
            int menuPrice;
            switch (menuName) {
                case "นมร้อน", "บราวนี่" -> menuPrice = 25;
                case "นมเย็น", "กาแฟร้อน", "น้ำผลไม้" -> menuPrice = 30;
                case "นมปั่น", "กาแฟเย็น", "เค้ก" -> menuPrice = 35;
                default -> menuPrice = 0;
            }
            if (cb.isSelected()) {
                addMenu(menuName, menuPrice);
            } else {
                removeMenu(menuName, menuPrice);
            }
            if (subTotalValue > 0) {
                subTotal.setText(String.valueOf(subTotalValue));
                subTotal.setVisible(true);
                btnNext.setDisable(false);
            } else {
                subTotal.setVisible(false);
                btnNext.setDisable(true);
            }
        } else {
            throw new RuntimeException("Not CheckBox");
        }

    }

    @FXML
    void onbtnNext(ActionEvent event) throws IOException {

        // create array of LineItem from menuList
        ObservableList<HBox> menuListItems = menuList.getItems();
        LineItem lineItems[] = new LineItem[menuListItems.size()];
        for (int i = 0; i < menuListItems.size(); i++) {
            HBox line = menuListItems.get(i);
            Label menuName = (Label) line.getChildren().get(0);
            Label menuPrice = (Label) line.getChildren().get(1);
            Menu menu = new Menu(menuName.getText(), Integer.parseInt(menuPrice.getText()));
            Spinner<Integer> menuSpinner = (Spinner<Integer>) line.getChildren().get(2);
            int menuAmount = menuSpinner.getValue();
            lineItems[i] = new LineItem(menu, menuAmount, Integer.parseInt(menuPrice.getText()));
        }
        Invoice_Cafe invoice = new Invoice_Cafe( new Date(), lineItems);

        // load next page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("receipt.fxml"));

        ReceiptController receiptController = new ReceiptController(invoice);
        loader.setController(receiptController);
        Scene scene = new Scene(loader.load());
        Stage popup = new Stage();
        popup.initStyle(StageStyle.TRANSPARENT);
        popup.setScene(scene);
        popup.show();

        // clear menuList
        menuList.getItems().clear();
        subTotalValue = 0;
        subTotal.setVisible(false);
        btnNext.setDisable(true);

        // clear all checkbox
        ObservableList<Node> checkBoxes = checkBoxGroup.getChildren();
        for (Node checkBox : checkBoxes) {
            HBox hBox = (HBox) checkBox;
            CheckBox cb = (CheckBox) hBox.getChildren().get(0);
            cb.setSelected(false);
        }

    }

    private void addMenu(String name, int price) throws IOException {
        menuList.getItems().add(new LineItemControl(name, price).getLineItem());
        subTotalValue += price;
        // add listener to latest spinner
        ObservableList<HBox> menuListItems = menuList.getItems();
        HBox lastItem = menuListItems.get(menuListItems.size() - 1);
        Spinner<Integer> spinner = (Spinner<Integer>) lastItem.getChildren().get(2);
        spinner.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            subTotalValue += (newValue - oldValue) * price;
            subTotal.setText(String.valueOf(subTotalValue));
            if (subTotalValue > 0) {
                subTotal.setVisible(true);
                btnNext.setDisable(false);
            } else {
                subTotal.setVisible(false);
                btnNext.setDisable(true);
            }
        });
    }

    private void removeMenu(String name, int price) throws IOException {
        ObservableList<HBox> items = menuList.getItems();
        // remove item from list where name match
        for (HBox item : items) {
            if (item.getChildren().get(0).toString().contains(name)) {
                // subtract price from subtotal
                var amount = price * ((Spinner<Integer>) item.getChildren().get(2)).getValue();
                System.out.println(amount);
                subTotalValue -= amount;
                items.remove(item);
                System.out.println("Removed : " + name);
                break;
            }
        }
    }


}