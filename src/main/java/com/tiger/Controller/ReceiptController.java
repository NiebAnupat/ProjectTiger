package com.tiger.Controller;

import com.tiger.Class.Cafe.Invoice_Cafe;
import com.tiger.Class.Cafe.LineItem;
import com.tiger.Class.Invoice;
import com.tiger.Class.InvoiceType;
import com.tiger.Class.myAlert;
import com.tiger.CustomControl.LineReceiptControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ReceiptController implements Initializable {
    @FXML
    private ListView<LineReceiptControl> List;

    @FXML
    private Label RecDate;

    @FXML
    private Label RecName;

    @FXML
    private Label RecTime;

    @FXML
    private Label ReceiptID;

    @FXML
    private Label Tax;

    @FXML
    private Label Total;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnSubmit;

    @FXML
    private Label subTotal;

    private Invoice invoice;

    private InvoiceType invoiceType;

    public ReceiptController(Invoice invoice) {
        this.invoice = invoice;
        this.invoiceType = invoice.getInvoiceType();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Statement...
        this.setAll();
    }


    private void setSubTotal() {
        double subTotalValue = invoice.getSubTotal();
        subTotal.setText(String.format("%.2f", subTotalValue));
    }

    private void setTotal() {
        double totalValue = invoice.getTotal();
        Total.setText(String.format("%.2f", totalValue));
    }

    private void setTax() {
        double taxValue = invoice.getTax();
        Tax.setText(String.format("%.2f", taxValue));
    }

    private void setReceiptID() {
        ReceiptID.setText(invoice.getInvoiceID());
    }

    private void setRecDate() {
        // set Date format
        RecDate.setText(invoice.getDateWithFormat());
    }

    private void setRecTime() {
        // set Time format
        RecTime.setText(invoice.getTimeWithFormat());
    }

    private void setRecName() {
        switch (invoiceType) {
            case CAFE -> RecName.setText("ใบเสร็จรับเงินร้านคาเฟ่");
            case ROOM -> RecName.setText("ใบเสร็จรับเงิน Co-Working Space");
            case MEMBER -> RecName.setText("ใบเสร็จรับเงินรสมัครสมาชิก");
        }
    }

    private void setList() {
        try {
            switch (invoiceType) {
                case CAFE -> {
                    Invoice_Cafe invoice_cafe = (Invoice_Cafe) invoice;
                    for (LineItem li : invoice_cafe.getItem()
                    ) {
                        LineReceiptControl lineReceiptControl = new LineReceiptControl();
                        lineReceiptControl.setListName(li.getMenu().getName());
                        lineReceiptControl.setListUnt(li.getQuantity());
                        lineReceiptControl.setListPrice(li.getSubtotal());
                        List.getItems().add(lineReceiptControl);
                    }
                }
                case ROOM -> {
                }
                case MEMBER -> {
                }
            }
        } catch (Exception e) {
            System.out.println("ReceiptController.setList() : " + e.getMessage());
        }
    }

    private void setAll() {
        setSubTotal();
        setTotal();
        setTax();
        setReceiptID();
        setRecDate();
        setRecTime();
        setRecName();
        setList();
    }

    public void submitReceipt(ActionEvent actionEvent) {
        if (this.invoice.submit()) new myAlert().showInformationAlert("ส่งใบเสร็จเรียบร้อยแล้ว");
        else new myAlert().showErrorAlert("ไม่สามารถส่งใบเสร็จได้");
        this.closeReceipt(null);
    }

    public void closeReceipt(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


}
