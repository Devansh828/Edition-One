package com.example.paperproject.hawkers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


import com.example.paperproject.JDBC.MySQLconnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class HawkersController {

    Connection con;

    @FXML
    private TextArea BoxSelAreas;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboAllAreas;

    @FXML
    private ComboBox<String> ComboHawkerID;

    @FXML
    private DatePicker DateOfJoining;

    @FXML
    private ImageView ImgPrev;


    @FXML
    private TextField TextAddress;

    @FXML
    private TextField TextAdhaar;

    @FXML
    private TextField TextContact;

    @FXML
    private TextField TextName;

    String path;

    @FXML
    void Browse(ActionEvent event) {
        FileChooser chooser=new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.jpg","*.png") );
        File file= chooser.showOpenDialog(null);
        path=(file.getAbsolutePath());

        try {

            ImgPrev.setImage(new Image(new FileInputStream(file)));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Clear(ActionEvent event) {
        ComboHawkerID.setValue(null);
        ComboAllAreas.setValue(null);

        TextAdhaar.setText(null);
        TextAdhaar.setDisable(false);

        TextName.setText(null);
        TextName.setDisable(false);

        TextContact.setText(null);
        TextContact.setDisable(false);

        TextAddress.setText(null);
        BoxSelAreas.setText(null);
        DateOfJoining.setValue(null);
        ImgPrev.setImage(img);
    }

    @FXML
    void Delete(ActionEvent event) {
        String query="delete from hawkers where hawkerid=?";
        try {
            PreparedStatement pst= con.prepareStatement(query);

            pst.setString(1,ComboHawkerID.getValue());

            int count=pst.executeUpdate();
            if(count==0)
                System.out.println("Invalid Id");
            else
                System.out.println("Record Deleted");

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }
        ComboHawkerID.getItems().remove(ComboHawkerID.getValue());
        Clear(event);
    }

    @FXML
    void doselareas(ActionEvent event) {
      BoxSelAreas.setText(BoxSelAreas.getText()+ComboAllAreas.getValue()+",");
    }

    @FXML
    void Fetch(ActionEvent event) {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from hawkers where hawkerid=?");
            stmt.setString(1,ComboHawkerID.getValue());
            ResultSet res= stmt.executeQuery();

            if(res.next()==true)
            {
                String name=res.getString("name");
                String contact=res.getString("contact");
                String address=res.getString("address");
                String adhaar=res.getString("adhaar");
                Date doj=res.getDate("doj");
                String picPath=res.getString("picpath");
                String selareas=res.getString("selareas");

                String[] sel=selareas.split(",");

                for(String str:sel){
//                    ComboAllAreas.getItems().
                }

                TextName.setText(name);
                TextName.setDisable(true);
                TextContact.setText(contact);
                TextContact.setDisable(true);
                TextAddress.setText(address);
                TextAdhaar.setText(adhaar);
                TextAdhaar.setDisable(true);
                DateOfJoining.setValue(doj.toLocalDate());
                ImgPrev.setImage(new Image(new FileInputStream(new File(picPath))));
                path=picPath;
                BoxSelAreas.setText(selareas);

            }
            else
                System.out.println("Invalid ID");

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }

    }

    @FXML
    void Save(ActionEvent event) {
//        hawkerid varchar(15) primary key,name varchar(50),contact varchar(10),address varchar(150),adhaar varchar(12),doj date,picpath varchar(500),selareas varchar(100)
        String query="insert into hawkers values(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst = con.prepareStatement(query);

            String name=TextName.getText();
            String contact=TextContact.getText();
            String hawkerID;
            if(name.length()>=5) {
                hawkerID = name.substring(0, 5) + contact.substring(contact.length()-5,contact.length());
            }
            else {
                hawkerID=name.substring(0,name.length())+contact.substring(contact.length()-5,contact.length());
            }
            pst.setString(1,hawkerID);
            pst.setString(2,name);
            pst.setString(3,contact);
            pst.setString(4,TextAddress.getText());
            pst.setString(5,TextAdhaar.getText());


            LocalDate lcl= DateOfJoining.getValue();
            java.sql.Date dt=java.sql.Date.valueOf(lcl);
            pst.setDate(6, dt);

            pst.setString(7,path);
            pst.setString(8,BoxSelAreas.getText());
            pst.executeUpdate();
            ComboHawkerID.getItems().add(hawkerID);
        }
        catch (Exception exp){
            System.out.println(exp);
        }

        Clear(event);
    }

    @FXML
    void Update(ActionEvent event) {
        // hawkerid varchar(15) primary key,name varchar(50),contact varchar(10),address varchar(150),adhaar varchar(12),doj date,picpath varchar(500),selareas varchar(100)

        String query="update hawkers set  address=?,doj=?, picpath=?,selareas=? where hawkerid=?";
        try {
            PreparedStatement pst= con.prepareStatement(query);
            pst.setString(1,TextAddress.getText());

            LocalDate lcl= DateOfJoining.getValue();
            java.sql.Date dt=java.sql.Date.valueOf(lcl);
            pst.setDate(2, dt);

//            if(path==null){
//                ResultSet res=getsinglehawker(ComboHawkerID.getValue());
//                path=res.getString("picpath");
//            }

            pst.setString(3,path);
            pst.setString(4,BoxSelAreas.getText());
            pst.setString(5,ComboHawkerID.getValue());

            int count=pst.executeUpdate();
            if(count==0)
                System.out.println("Inavlid Id");
            else
                System.out.println("Record Updated");

        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }
        Clear(event);
    }
    Image img;

    @FXML
    void initialize() {
        con= MySQLconnector.getMySQLDBConnection();
        if(con==null)
        {
            System.out.println("Connection Error");
            return;
        }

        img=ImgPrev.getImage();

        ArrayList<String> lstAreas=getAllAreas();
        for(String str: lstAreas){
            ComboAllAreas.getItems().add(str);
        }
        ArrayList<String> lstHawkerID=getHawkerID();
        for(String str: lstHawkerID){
            ComboHawkerID.getItems().add(str);
        }

    }

    ArrayList<String> getAllAreas(){
        ArrayList<String> Areas=new ArrayList<String>();

        try
        {
            PreparedStatement stmt = con.prepareStatement("select area from areas");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String area=res.getString("area");
                Areas.add(area);

            }
            System.out.println(Areas);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return Areas;
    }

    ArrayList<String> getHawkerID(){
        ArrayList<String> HawkerIDs=new ArrayList<String>();

        try
        {
            PreparedStatement stmt = con.prepareStatement("select hawkerid from hawkers");
            ResultSet res= stmt.executeQuery();

            while(res.next())
            {
                String hawkerID=res.getString("hawkerid");
                HawkerIDs.add(hawkerID);

            }
            System.out.println(HawkerIDs);
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return HawkerIDs;
    }

    ResultSet getsinglehawker(String id){
        ResultSet res=null;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from hawkers where hawkerid=?");
            stmt.setString(1,ComboHawkerID.getValue());
            res= stmt.executeQuery();

        }
        catch(Exception exp)
        {
            exp.printStackTrace();
        }
        return res;
    }


}
