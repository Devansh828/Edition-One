package com.example.paperproject.papermaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.paperproject.JDBC.MySQLconnector;
import com.example.paperproject.showerrormsg.ShowErrorMsg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PaperMasterController {

    Connection con;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TextLanguage;

    @FXML
    private TextField TextPrice;

    @FXML
    private ComboBox<String> comboPaperTitle;

    @FXML
    void Delete(ActionEvent event) {
        String query="delete from Newspapers where paper=?";
        try{
            PreparedStatement pst= con.prepareStatement(query);

            pst.setString(1,String.valueOf(comboPaperTitle.getValue()));

            int c= pst.executeUpdate();
            if(c==0)
            {
                ShowErrorMsg.showMsg(new Alert(Alert.AlertType.ERROR),"Error","invalid mobileno");
            }
            else{
                ShowErrorMsg.showMsg(new Alert(Alert.AlertType.CONFIRMATION),"Confirmation","Record deleted successfully");

                int index=comboPaperTitle.getSelectionModel().getSelectedIndex();
                comboPaperTitle.getItems().remove(index);
                New(event);
            }

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }

    }

    @FXML
    void Find(ActionEvent event) {
        String query="select * from Newspapers where paper=?";
        try{
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,String.valueOf(comboPaperTitle.getValue()));
            ResultSet set=pst.executeQuery();
            if(set.next()==true){
                String language=set.getString("language");
                float price=set.getFloat("price");
                TextLanguage.setText(language);
                TextPrice.setText( String.valueOf( price));
            }
        }
        catch (Exception exp){
            System.out.println(exp);
        }
    }

    @FXML
    void New(ActionEvent event) {
        TextLanguage.setText(null);
        comboPaperTitle.setValue(null);
        TextPrice.setText(null);
    }

    @FXML
    void Save(ActionEvent event) {
        String query="insert into Newspapers values(?,?,?)";
        try{
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,String.valueOf(comboPaperTitle.getValue()));
            pst.setString(2,TextLanguage.getText());
            pst.setFloat(3,Float.parseFloat(TextPrice.getText()));
            pst.executeUpdate();
            comboPaperTitle.getItems().add(comboPaperTitle.getValue().toString());
            ShowErrorMsg.showMsg(new Alert(Alert.AlertType.CONFIRMATION),"Confirmation","Record Saved successfully");
            New(event);
        }
        catch (Exception exp){
            ShowErrorMsg.showMsg(new Alert(Alert.AlertType.ERROR),"Error",exp.toString());
            System.out.println(exp);
        }


    }

    @FXML
    void Update(ActionEvent event) {
        String query="update Newspapers set price=? where paper=? and language=?";
        try{
            PreparedStatement pst = con.prepareStatement(query);
            pst.setFloat(1,Float.parseFloat(TextPrice.getText()));
            pst.setString(2,String.valueOf(comboPaperTitle.getValue()));
            pst.setString(3,TextLanguage.getText());

            int count=pst.executeUpdate();

                ShowErrorMsg.showMsg(new Alert(Alert.AlertType.CONFIRMATION), "Confirmation", "Record updated successfully");
                New(event);

        }
        catch (Exception exp){
            ShowErrorMsg.showMsg(new Alert(Alert.AlertType.ERROR),"Error",exp.toString());
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

        ArrayList<String> papersLst= getAllpapers();
        for(String  s : papersLst){
            comboPaperTitle.getItems().add(s);
        }

    }


    ArrayList<String> getAllpapers()
    {

        ArrayList<String> papers=new ArrayList<String>();

        try
        {
            PreparedStatement stmt = con.prepareStatement("select paper from Newspapers");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String paper=res.getString("paper");
                papers.add(paper);

            }
            System.out.println(papers);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return papers;


    }

}
