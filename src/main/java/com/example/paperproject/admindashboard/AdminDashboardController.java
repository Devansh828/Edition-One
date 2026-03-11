package com.example.paperproject.admindashboard;

import java.net.URL;
import java.time.temporal.Temporal;
import java.util.ResourceBundle;

import com.example.paperproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void Areas(ActionEvent event) {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("areas/AreasView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
//            Scene scene1=(Scene) .getScene();
//            scene1.getWindow().hide();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }




    @FXML
    void BillBoard(ActionEvent event) {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billboard/BillBoardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
            //Scene scene1=(Scene)btnComboApp.getScene();
            //scene1.getWindow().hide();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }




    @FXML
    void BillCollector(ActionEvent event) {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billcollector/BillCollectorView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
            //Scene scene1=(Scene)btnComboApp.getScene();
            //scene1.getWindow().hide();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }




    @FXML
    void Billing(ActionEvent event) {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("billing/BillingView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
            //Scene scene1=(Scene)btnComboApp.getScene();
            //scene1.getWindow().hide();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }



    @FXML
    void Customers(ActionEvent event) {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customers/CustomersView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
            //Scene scene1=(Scene)btnComboApp.getScene();
            //scene1.getWindow().hide();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }



    @FXML
    void CustomersBoard(ActionEvent event) {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customersboard/CustomersboardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
            //Scene scene1=(Scene)btnComboApp.getScene();
            //scene1.getWindow().hide();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }



    @FXML
    void Hawkers(ActionEvent event) {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hawkers/HawkersView.fxml"));
//            Scene scene = new Scene(fxmlLoader.load());
            Scene scene = new Scene(fxmlLoader.load(), 673, 570);
            stage.setTitle("Hello!");
            stage.setResizable(false);
//            stage.setMaxHeight(600);     // hawkers
//            stage.setMaxWidth(673);
            stage.setScene(scene);
            stage.show();
            //Scene scene1=(Scene)btnComboApp.getScene();
            //scene1.getWindow().hide();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }



    @FXML
    void PaperMaster(ActionEvent event) {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("papermaster/PaperMasterView.fxml"));
//            Scene scene = new Scene(fxmlLoader.load());
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setResizable(false);
            stage.setTitle("Hello!");
//            stage.setMaxHeight(425);   // PaperMaster
//            stage.setMaxWidth(600);
            stage.setScene(scene);
            stage.show();
            //Scene scene1=(Scene)btnComboApp.getScene();
            //scene1.getWindow().hide();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }



    @FXML
    void ShowPapers(ActionEvent event) {
        try{
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("showpapers/ShowPapersView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
            //Scene scene1=(Scene)btnComboApp.getScene();
            //scene1.getWindow().hide();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }



    @FXML
    void initialize() {

    }

}
