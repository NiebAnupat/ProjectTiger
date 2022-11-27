package com.tiger.Controller;

import com.tiger.Class.DB_Connector;
import com.tiger.Class.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import se.alipsa.ymp.YearMonthPickerCombo;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML
    private Button btnPrint;

    @FXML
    private TableColumn<Report, String > columnDate;

    @FXML
    private TableColumn<Report, String> columnList;

    @FXML
    private TableColumn<Report, String > columnPrice;

    @FXML
    private TableColumn<Report, String > columnQty;

    @FXML
    private TableView<Report> reportTable;

    @FXML
    private YearMonthPickerCombo yearMonthPicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // set no content in table text
        reportTable.setPlaceholder(new Label("ไม่พบข้อมูล"));

        YearMonth selectedYearMonth = yearMonthPicker.getValue();

        // set column value
        columnDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        columnList.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        columnQty.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        columnPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
    }

    public void setCafeReport() {

        System.out.println("cafe report");

        YearMonth selectedYearMonth = yearMonthPicker.getValue();

        // YearMonth to sql date
        LocalDate localDate = selectedYearMonth.atDay(1);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        // Get data from database (cafe_report View)
        DB_Connector db = new DB_Connector();
        System.out.println(sqlDate);
        try {
            String query = String.format("SET @year = '%s';", sqlDate);
            Statement St = db.getSt();
            System.out.println(query);
            St.execute(query);
            // Get data from database (cafe_report View)
            query = "SELECT * FROM cafe_report;";
            System.out.println(query);
            ResultSet rs = St.executeQuery(query);
            ObservableList<Report> data = FXCollections.observableArrayList();
            while (rs.next()) {
                // insert data to table view
                String  date = rs.getString(1);
                String list = rs.getString(2);
                String  qty = rs.getString(3);
                String  price = rs.getString(4);
                data.add(new Report(date, list, qty, price));
                System.out.println(date);
            }
            reportTable.setItems(data);
            reportTable.setVisible(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCoWorkingReport(){

        System.out.println("CoWorking Report");
        YearMonth selectedYearMonth = yearMonthPicker.getValue();

        // YearMonth to sql date
        LocalDate localDate = selectedYearMonth.atDay(1);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        // Get data from database (cafe_report View)
        DB_Connector db = new DB_Connector();
        System.out.println(sqlDate);
        try {
            String query = String.format("SET @year = '%s';", sqlDate);
            Statement St = db.getSt();
            St.executeUpdate(query);
            // Get data from database (cafe_report View)
            query = "SELECT * FROM co_working_report;";
            ResultSet rs = St.executeQuery(query);
            ObservableList<Report> data = FXCollections.observableArrayList();
            while (rs.next()) {
                // insert data to table view
                String  date = rs.getString(1);
                String list = rs.getString(2);
                String  qty = rs.getString(3);
                String  price = rs.getString(4);
                data.add(new Report(date, list, qty, price));
                System.out.println(date);
            }
            reportTable.setItems(data);
            reportTable.setVisible(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
