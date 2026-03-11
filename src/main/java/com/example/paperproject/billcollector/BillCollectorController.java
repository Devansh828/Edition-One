package com.example.paperproject.billcollector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.example.paperproject.JDBC.MySQLconnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillCollectorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TextMobile;

    @FXML
    private TextField TextTotalPending;

    @FXML
    private TableView<UserBillBean> tblPendingBills;

    @FXML
    void Paid(ActionEvent event) {
        try{
            PreparedStatement pm= con.prepareStatement("update billing set status=0 where mobilenumber=?");
            pm.setString(1,TextMobile.getText());
            int count=pm.executeUpdate();
            if(count==0){
                System.out.println("not updated");
            }
            else {
                System.out.println("updated");
            }
        }
        catch (Exception exp){
            System.out.println(exp);
        }
    }

    @FXML
    void ShowPending(ActionEvent event) {
        TableColumn<UserBillBean, String> fromC=new TableColumn<UserBillBean, String>("From");
        fromC.setCellValueFactory(new PropertyValueFactory<UserBillBean,String>("from"));
        fromC.setMinWidth(100);

        TableColumn<UserBillBean, String> toC=new TableColumn<UserBillBean, String>("To");
        toC.setCellValueFactory(new PropertyValueFactory<UserBillBean,String>("to"));
        toC.setMinWidth(100);

        TableColumn<UserBillBean, String> lessdaysC=new TableColumn<UserBillBean, String>("Less Days");
        lessdaysC.setCellValueFactory(new PropertyValueFactory<UserBillBean,String>("lessdays"));
        lessdaysC.setMinWidth(100);

        TableColumn<UserBillBean, String> billC=new TableColumn<UserBillBean, String>("Bill");
        billC.setCellValueFactory(new PropertyValueFactory<UserBillBean,String>("bill"));
        billC.setMinWidth(100);

        tblPendingBills.getColumns().addAll(fromC,toC,lessdaysC,billC);

        tblPendingBills.setItems(null);
        tblPendingBills.setItems(getArrayOfObjects());
        TextTotalPending.setText(String.valueOf(total));
    }

    float total=0;

    ObservableList<UserBillBean> getArrayOfObjects()
    {

        ObservableList<UserBillBean> list= FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from billing where mobilenumber=? and status=1");
            stmt.setString(1,TextMobile.getText());
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {

                java.sql.Date dos=res.getDate("dos");
                java.sql.Date doe=res.getDate("doe");

                String lessdays=String.valueOf(res.getInt("lessdays"));
                String bill=String.valueOf(res.getFloat("bill"));
                total=total+res.getFloat("bill");

                UserBillBean obj=new UserBillBean(dos.toLocalDate().toString(),doe.toLocalDate().toString(),lessdays,bill);
                list.add(obj);
            }

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return  list;

    }

    Connection con;
    @FXML
    void initialize() {
        con= MySQLconnector.getMySQLDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }
        total=0;
    }

}
