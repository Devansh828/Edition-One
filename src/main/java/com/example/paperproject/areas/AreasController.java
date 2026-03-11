package com.example.paperproject.areas;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.example.paperproject.JDBC.MySQLconnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AreasController {

    Connection con;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textAreas;

    @FXML
    void Save(ActionEvent event) {
        String query="insert into Areas values(?)";
        try{
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,textAreas.getText());

            pst.executeUpdate();

        }
        catch (Exception exp){
            System.out.println(exp);
        }
        clear();
    }

    void clear(){
        textAreas.setText(null);
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

}
