package com.example.paperproject.billboard;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.example.paperproject.JDBC.MySQLconnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboStatus;

    @FXML
    private DatePicker DateFrom;

    @FXML
    private DatePicker DateTo;

    @FXML
    private TextField TextAmount;

    @FXML
    private TableView<BillsBean> tblBills;

    float amount;

    @FXML
    void FindBill(ActionEvent event) {

        TableColumn<BillsBean, String> IDC=new TableColumn<BillsBean, String>("ID");
        IDC.setCellValueFactory(new PropertyValueFactory<BillsBean,String>("rid"));
        IDC.setMinWidth(50);
        IDC.setMaxWidth(50);

        TableColumn<BillsBean, String> mobileC=new TableColumn<BillsBean, String>("Mobile Number");
        mobileC.setCellValueFactory(new PropertyValueFactory<BillsBean,String>("mobile"));
        mobileC.setMinWidth(100);
        mobileC.setMaxWidth(100);


        TableColumn<BillsBean, String> fromC=new TableColumn<BillsBean, String>("From");
        fromC.setCellValueFactory(new PropertyValueFactory<BillsBean,String>("dos"));
        fromC.setMinWidth(100);
        fromC.setMaxWidth(100);

        TableColumn<BillsBean, String> toC=new TableColumn<BillsBean, String>("To");
        toC.setCellValueFactory(new PropertyValueFactory<BillsBean,String>("doe"));
        toC.setMinWidth(100);
        toC.setMaxWidth(100);

        TableColumn<BillsBean, String> lessdaysC=new TableColumn<BillsBean, String>("Less Days");
        lessdaysC.setCellValueFactory(new PropertyValueFactory<BillsBean,String>("lessdays"));
        lessdaysC.setMinWidth(75);
        lessdaysC.setMaxWidth(75);

        TableColumn<BillsBean, String> billC=new TableColumn<BillsBean, String>("Bill");
        billC.setCellValueFactory(new PropertyValueFactory<BillsBean,String>("bill"));
        billC.setMinWidth(75);
        billC.setMaxWidth(75);

        tblBills.getColumns().clear();

        tblBills.getColumns().addAll(IDC,mobileC,fromC,toC,lessdaysC,billC);

        tblBills.setItems(null);
        tblBills.setItems(getArrayOfObjects());
        TextAmount.setText(String.valueOf(amount));

    }

    ObservableList<BillsBean> getArrayOfObjects()
    {
        amount=0;
        String query;
        ObservableList<BillsBean> list= FXCollections.observableArrayList();
        try {
            if(ComboStatus.getValue().equals("Paid")){
                query="select * from billing where dos>=? and doe<=? and status=0";
            } else if (ComboStatus.getValue().equals("Unpaid")) {
                query="select * from billing where dos>=? and doe<=? and status=1";
            }
            else {
                query="select * from billing where dos>=? and doe<=?";
            }

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setDate(1, Date.valueOf(DateFrom.getValue()));
            stmt.setDate(2, Date.valueOf(DateTo.getValue()));
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
//                rid int primary key auto_increment,mobilenumber varchar(10),dos date,doe date,totaldays int,lessdays int,bill float,status int
                String rid=String.valueOf(res.getInt("rid"));
                String mobile =res.getString("mobilenumber");
                java.sql.Date dos=res.getDate("dos");
                java.sql.Date doe=res.getDate("doe");

                String totaldays=String.valueOf(res.getInt("totaldays"));
                String lessdays=String.valueOf(res.getInt("lessdays"));
                String bill=String.valueOf(res.getFloat("bill"));
                amount+=res.getFloat("bill");
                BillsBean obj=new BillsBean(rid,mobile,dos.toLocalDate().toString(),doe.toLocalDate().toString(),totaldays,lessdays,bill);
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
      ComboStatus.getItems().add("All");
      ComboStatus.getItems().add("Paid");
      ComboStatus.getItems().add("Unpaid");
      ComboStatus.getSelectionModel().select("All");
        amount=0;

    }

}
