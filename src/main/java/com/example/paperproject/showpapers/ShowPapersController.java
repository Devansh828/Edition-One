package com.example.paperproject.showpapers;

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
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowPapersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<PapersBean> tblPapers;

    @FXML
    void fetchPapers(ActionEvent event) {
        TableColumn<PapersBean, String> paperC=new TableColumn<PapersBean, String>("Paper");
        paperC.setCellValueFactory(new PropertyValueFactory<PapersBean,String>("paper"));
        paperC.setMinWidth(100);

        TableColumn<PapersBean, String> languageC=new TableColumn<PapersBean, String>("Langauge");
        languageC.setCellValueFactory(new PropertyValueFactory<PapersBean,String>("language"));
        languageC.setMinWidth(100);

        TableColumn<PapersBean, String> priceC=new TableColumn<PapersBean, String>("Price");
        priceC.setCellValueFactory(new PropertyValueFactory<PapersBean,String>("price"));
        priceC.setMinWidth(100);

        tblPapers.getColumns().addAll(paperC,languageC,priceC);

        tblPapers.setItems(null);
        tblPapers.setItems(getArrayOfObjects());
    }

    ObservableList<PapersBean> getArrayOfObjects()
    {

        ObservableList<PapersBean> list= FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from newspapers");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String price=String.valueOf(res.getFloat("price"));
                String paper=res.getString("paper");

                String language=res.getString("language");

                PapersBean obj=new PapersBean(paper,price,language);
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

    }

}
