package com.example.paperproject.billing;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.paperproject.JDBC.MySQLconnector;
import com.example.paperproject.datedifference.DateDifference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class BillingController {

    Connection con;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker Datedoe;

    @FXML
    private DatePicker Datedos;

    @FXML
    private TextField TextBillAmount;

    @FXML
    private TextField TextDays;

    @FXML
    private TextField TextLessDays;

    @FXML
    private TextField TextMobile;

    @FXML
    private TextField TextName;

    @FXML
    private TextField TextTotalPrice;

    @FXML
    void Fetch(ActionEvent event) {
        String mobile = TextMobile.getText();

//        if (mobile.isEmpty()) {
//            lblchange.setText("Enter mobile");
//            return;
//        }
//        mobile varchar(15) primary key,cname varchar(50),emailid varchar(50),address varchar(100),dos date,area varchar(50),hawker varchar(50),papers varchar(100),prices varchar(50),status int default 1
        try {
            String query = "select cname, dos, papers, prices from customers where mobile = ? and status = 1";
            var pst = con.prepareStatement(query);
            pst.setString(1, mobile);
            var rs = pst.executeQuery();

            if (rs.next()) {
                TextName.setText(rs.getString("cname"));
                Datedos.setValue(rs.getDate("dos").toLocalDate());

                // Sum prices
                String prices = rs.getString("prices");
                float total = 0;
                if (prices != null && !prices.isEmpty()) {
                    for (String p : prices.split(",")) {
                        total += Float.parseFloat(p);
                    }
                }
                TextTotalPrice.setText(String.valueOf(total));
//                lblchange.setText("Customer found");
            } else {
//                lblchange.setText("No active customer ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void GenerateBill(ActionEvent event) {
        try {
            int totalDays=Integer.parseInt(TextDays.getText());
            int  lessDays = Integer.parseInt(TextLessDays.getText());
            int billableDays = totalDays - lessDays;


            float dailyPrice = Float.parseFloat(TextTotalPrice.getText());
            float bill = billableDays * dailyPrice;

            TextBillAmount.setText(String.valueOf(bill));
//            lblchange.setText("Bill generated for");

        } catch (Exception e) {
            e.printStackTrace();
//            lblchange.setText("Error .");
        }
    }

    @FXML
    void SaveBill(ActionEvent event) {
        try {
//            mobilenumber varchar(10),dos date,doe date,totaldays int,lessdays int,bill float,status int
            String query = "insert into billing values (null,?, ?, ?, ?, ?, ?, 1)";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, TextMobile.getText());
            pst.setDate(2, Date.valueOf(Datedos.getValue()));
            pst.setDate(3, Date.valueOf(Datedoe.getValue()));
            pst.setInt(4, Integer.parseInt(TextDays.getText()));
            pst.setInt(5, Integer.parseInt(TextLessDays.getText()));
            pst.setFloat(6,Float.parseFloat(TextBillAmount.getText()));

            int bill = pst.executeUpdate();

            if (bill > 0) {
//                lblchange.setText("Bill saved s.");
                doupdate();
            } else {
                System.out.println("Not saved");
//                lblchange.setText("Failed to save bill.");
            }

        } catch (Exception e) {
            e.printStackTrace();
//            lblchange.setText("Error ");
        }

    }

    @FXML
    void setDays(ActionEvent event) {
        LocalDate dos=Datedos.getValue();
        LocalDate doe=Datedoe.getValue();
        int totalDays= (int)DateDifference.getDaysBetween(dos,doe);
        TextDays.setText(String.valueOf(totalDays+1));
    }

    @FXML
    void initialize() {
        con= MySQLconnector.getMySQLDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }

    }


    void doupdate() {
        String query = "update customers set dos =? where mobile =? ";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            LocalDate doe = Datedoe.getValue();
            Date dt = Date.valueOf(doe.plusDays(1));

            pst.setDate(1, dt);
            pst.setString(2, TextMobile.getText());
            pst.executeUpdate();
            System.out.println("Date updated in customer table");
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
//            showerrmsg(exp.getMessage());

        }
    }
}
