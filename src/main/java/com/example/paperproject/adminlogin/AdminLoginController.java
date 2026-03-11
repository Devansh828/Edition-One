package com.example.paperproject.adminlogin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.paperproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginController {

    @FXML
    private Label txtMsg;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void login(ActionEvent event) throws IOException {
        String username=txtUsername.getText();
        String password=txtPassword.getText();

        BufferedReader r=new BufferedReader(new FileReader(new File("password.txt")));
        String required =r.readLine();
        if(username.equals("Admin") && password.equals(required)){
            try{
                Stage stage=new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admindashboard/AdminDashboard.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 690, 700);
                stage.setResizable(false);
                stage.setTitle("Hello!");
                stage.setScene(scene);
                stage.show();
                Scene scene1=(Scene)txtUsername.getScene();
                scene1.getWindow().hide();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            txtMsg.setText("Fill the valid credentials");
        }
    }

    @FXML
    void initialize() {

    }

}
