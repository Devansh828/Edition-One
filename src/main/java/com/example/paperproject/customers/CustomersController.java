package com.example.paperproject.customers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.paperproject.JDBC.MySQLconnector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class CustomersController {

    Connection con;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboAreas;

    @FXML
    private ComboBox<String> ComboHawkers;

    @FXML
    private ListView<String> ListPapers;

    @FXML
    private ListView<String> ListPrices;

    @FXML
    private ListView<String> ListSelPapers;

    @FXML
    private ListView<String> ListSelPrices;

    @FXML
    private TextField TextAddress;

    @FXML
    private TextField TextEmail;

    @FXML
    private TextField TextMobile;

    @FXML
    private TextField TextName;

    @FXML
    private DatePicker Textdos;

    @FXML
    void 	showMsg(Alert alert, String title, String msg) {
        //Alert alert = new Alert(AlertType.INFORMATION);

        //Alert alert = new Alert(AlertType.WARNING);

        alert.setTitle(title);
        //or
        //alert.setTitle(null);

        alert.setContentText(msg);

        alert.showAndWait();
    }

    @FXML
    void Clear(ActionEvent event) {
        TextMobile.setText("");
        TextName.setText("");
        TextEmail.setText("");
        TextAddress.setText("");
        Textdos.setValue(null);
        ComboAreas.setValue(null);
        ComboHawkers.getItems().clear();
        ListSelPapers.getItems().clear();
        ListSelPrices.getItems().clear();
    }

    @FXML
    void Fetch(ActionEvent event) {
        Clear(event);
        try {
            String query = "select * from customers where mobile=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, TextMobile.getText());
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                TextName.setText(res.getString("cname"));
                TextEmail.setText(res.getString("emailid"));
                TextAddress.setText(res.getString("address"));
                Textdos.setValue(res.getDate("dos").toLocalDate());

                ComboAreas.setValue(res.getString("area"));
                ComboHawkers.setValue(res.getString("hawker"));
                String s=res.getString("papers");
                String[] ary=s.split(",");
                for(String m:ary)
                {
                    ListSelPapers.getItems().add(m);
                }
                String spr=res.getString("prices");
                String[] arypr=spr.split(",");
                for(String m:arypr)
                {
                    ListSelPrices.getItems().add(m);
                }

            }
            else{
                showMsg(new Alert(Alert.AlertType.ERROR),"Error","Invalid mobileno  ");
            }
        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }
    }

    @FXML
    void Modify(ActionEvent event) {
        String query="update customers set emailid=?, address=?,dos=?,area=?,hawker=?,papers=?,prices=?  where mobile=?";
        try {
            PreparedStatement pst= con.prepareStatement(query);
            pst.setString(1,TextEmail.getText());
            pst.setString(2,TextAddress.getText());
            LocalDate lcl= Textdos.getValue();
            java.sql.Date dt=java.sql.Date.valueOf(lcl);
            pst.setDate(3, dt);
            pst.setString(4,ComboAreas.getValue());
            pst.setString(5,ComboHawkers.getValue());
            ObservableList<String> items = ListSelPapers.getItems();
            String paper="";
            for(String s:items)
            {
                paper=paper+","+s;

            }
            pst.setString(6,paper);
            String price="";
            ObservableList<String> itemprice = ListSelPrices.getItems();
            for(String s:itemprice)
            {
                price=price+","+s;

            }
            pst.setString(7,price);
            pst.setString(8,TextMobile.getText());



            int count=pst.executeUpdate();
            if(count==0)
                showMsg(new Alert(Alert.AlertType.ERROR),"Error","invalid mobiono");
            else
                showMsg(new Alert(Alert.AlertType.CONFIRMATION),"Confirmation","Record updated successfully");

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }
        Clear(event);
    }

    @FXML
    void Remove(ActionEvent event) {
        try {
            String query = "delete from customers where mobile=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,TextMobile.getText());

            int c= pst.executeUpdate();
            if(c==0)
            {
                showMsg(new Alert(Alert.AlertType.ERROR),"Error","invalid mobileno");
            }
            else{
                showMsg(new Alert(Alert.AlertType.CONFIRMATION),"Confirmation","Record deleted successfully");
                Clear(event);
            }

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }
        Clear(event);
    }

    @FXML
    void Save(ActionEvent event) {
        try {
            String query = "insert into customers values(?,?,?,?,?,?,?,?,?,1)";
            PreparedStatement pst = con.prepareStatement(query);


            pst.setString(1,TextMobile.getText());

            pst.setString(2,TextName.getText());
            pst.setString(3,TextEmail.getText());

            pst.setString(4,TextAddress.getText());
            LocalDate lcl=Textdos.getValue();
            Date dt= Date.valueOf(lcl);
            pst.setDate(5,dt);

            pst.setString(6,ComboAreas.getValue());

            pst.setString(7,ComboHawkers.getValue());

            ObservableList<String> items = ListSelPapers.getItems();
            String paper="";
            for(String s:items)
            {
                paper=paper+s+",";

            }
            pst.setString(8,paper);
            String price="";
            ObservableList<String> itemprice = ListSelPrices.getItems();
            for(String s:itemprice)
            {
                price=price+s+",";

            }
            pst.setString(9,price);
            pst.executeUpdate();
            showMsg(new Alert(Alert.AlertType.CONFIRMATION),"Confirmation","Record Saved successfully");

        }
        catch(Exception exp)
        {

            showMsg(new Alert(Alert.AlertType.ERROR),"Error",exp.toString());

        }
        Clear(event);
    }

    @FXML
    void selpapers(MouseEvent event) {
        if(event.getClickCount()==2)
        {
            ListSelPapers.getItems().add(ListPapers.getSelectionModel().getSelectedItem());
            int ind=ListPapers.getSelectionModel().getSelectedIndex();
            ListPrices.getSelectionModel().select(ind);
            ListSelPrices.getItems().add(ListPrices.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void fillhawkerid(ActionEvent event) {
        ComboHawkers.getItems().clear();
        try {
            String query = "select distinct name from hawkers where selareas like ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,"%"+ComboAreas.getValue()+"%");
            ResultSet res=pst.executeQuery();
            while(res.next())
            {
                String id=res.getString("name");
                System.out.println(id);
                ComboHawkers.getItems().add(id);
            }
        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }

    }

    @FXML
    void initialize() {
        con= MySQLconnector.getMySQLDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }
        ComboHawkers.getItems().clear();
        ListSelPrices.getItems().clear();
        ListSelPapers.getItems().clear();
        ArrayList<String> arlst=getareas();
        for(String s:arlst)
        {
            ComboAreas.getItems().add(s);
        }
        getpapersandprices();
        for(int i=0;i<aryprices.size();i++)
        {
            String paper= arypaper.get(i);
            ListPapers.getItems().add(paper);
            String prices=aryprices.get(i);
            ListPrices.getItems().add(prices);
        }

    }


    ArrayList<String> getareas()
    {

        ArrayList<String> pt=new ArrayList<String>();

        try
        {
            PreparedStatement stmt = con.prepareStatement("select distinct area from areas");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String area=res.getString("area");
                pt.add(area);

            }
            System.out.println(pt);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return pt;


    }

    ArrayList<String> aryprices=new ArrayList<String>();
    ArrayList<String>arypaper=new ArrayList<String>();

    void  getpapersandprices ()
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement("select paper,price  from newspapers");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String paper=res.getString("paper");
                Float price=res.getFloat("price");
                arypaper.add(paper);
                aryprices.add(String.valueOf(price));

            }
            System.out.println(arypaper);
            System.out.println(aryprices);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }



    }


}
