package com.example.paperproject.customersboard;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.paperproject.JDBC.MySQLconnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomersboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboAreas;

    @FXML
    private ComboBox<String> ComboHawkers;

    @FXML
    private ComboBox<String> ComboPapers;

    @FXML
    private TableView<CustomersBean> tblData;

    @FXML
    void showData(ActionEvent event) {
        TableColumn<CustomersBean, String> mobileC = new TableColumn<>("MOBILE");
        mobileC.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        mobileC.setMinWidth(100);

        TableColumn<CustomersBean, String> nameC = new TableColumn<>("NAME");
        nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameC.setMinWidth(100);

        TableColumn<CustomersBean, String> emailC = new TableColumn<>("EMAIL");
        emailC.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailC.setMinWidth(100);

        TableColumn<CustomersBean, String> addressC = new TableColumn<>("ADDRESS");
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressC.setMinWidth(100);

        TableColumn<CustomersBean, String> dosC = new TableColumn<>("DATE OF START");
        dosC.setCellValueFactory(new PropertyValueFactory<>("dos"));
        dosC.setMinWidth(100);

        TableColumn<CustomersBean, String> areaC = new TableColumn<>("AREA");
        areaC.setCellValueFactory(new PropertyValueFactory<>("area"));
        areaC.setMinWidth(100);

        TableColumn<CustomersBean, String> harkeridC = new TableColumn<>("HAWKER");
        harkeridC.setCellValueFactory(new PropertyValueFactory<>("hawker"));
        harkeridC.setMinWidth(100);

        TableColumn<CustomersBean, String> paperC = new TableColumn<>("PAPER");
        paperC.setCellValueFactory(new PropertyValueFactory<>("paper"));
        paperC.setMinWidth(100);

        TableColumn<CustomersBean, String> priceC = new TableColumn<>("PRICE");
        priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceC.setMinWidth(100);

        TableColumn<CustomersBean, String> statusC = new TableColumn<>("Status");
        statusC.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusC.setMinWidth(100);

        tblData.getColumns().clear();

        tblData.getColumns().addAll(mobileC,nameC,emailC,addressC,dosC,areaC,harkeridC,paperC,priceC,statusC);
        tblData.setItems(null);

        ObservableList<CustomersBean> filtered = getFilteredCustomers();
        tblData.setItems(filtered);

    }

    ObservableList<CustomersBean> getFilteredCustomers() {
        ObservableList<CustomersBean> list = FXCollections.observableArrayList();

        try {
            String area = ComboAreas.getValue();
            String paper = ComboPapers.getValue();
            String hawker = ComboHawkers.getValue();

            PreparedStatement pst = null;

            // CASE 1: All none
            if (area.equals("none") && paper.equals("none") && hawker.equals("none")) {
                pst = con.prepareStatement("SELECT * FROM customers");
            }
            // CASE 2: Only area
            else if (!area.equals("none") && paper.equals("none") && hawker.equals("none")) {
                pst = con.prepareStatement("SELECT * FROM customers WHERE area = ?");
                pst.setString(1, area);
            }
            // CASE 3: Only paper
            else if (area.equals("none") && !paper.equals("none") && hawker.equals("none")) {
                pst = con.prepareStatement("SELECT * FROM customers WHERE paper LIKE ?");
                pst.setString(1, "%" + paper + "%");
            }
            // CASE 4: Only hawker
            else if (area.equals("none") && paper.equals("none") && !hawker.equals("none")) {
                pst = con.prepareStatement("SELECT * FROM customers WHERE harkerid = ?");
                pst.setString(1, hawker);
            }
            // CASE 5: Area + paper
            else if (!area.equals("none") && !paper.equals("none") && hawker.equals("none")) {
                pst = con.prepareStatement("SELECT * FROM customers WHERE area = ? AND paper LIKE ?");
                pst.setString(1, area);
                pst.setString(2, "%" + paper + "%");
            }
            // CASE 6: Area + hawker
            else if (!area.equals("none") && paper.equals("none") && !hawker.equals("none")) {
                pst = con.prepareStatement("SELECT * FROM customers WHERE area = ? AND harkerid = ?");
                pst.setString(1, area);
                pst.setString(2, hawker);
            }
            // CASE 7: Paper + hawker
            else if (area.equals("none") && !paper.equals("none") && !hawker.equals("none")) {
                pst = con.prepareStatement("SELECT * FROM customers WHERE paper LIKE ? AND harkerid = ?");
                pst.setString(1, "%" + paper + "%");
                pst.setString(2, hawker);
            }
            // CASE 8: All three selected
            else if (!area.equals("none") && !paper.equals("none") && !hawker.equals("none")) {
                pst = con.prepareStatement("SELECT * FROM customers WHERE area = ? AND paper LIKE ? AND harkerid = ?");
                pst.setString(1, area);
                pst.setString(2, "%" + paper + "%");
                pst.setString(3, hawker);
            }

            // Execute the query and build the list
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                String mobile = res.getString("mobile");
                String name = res.getString("cname");
                String email = res.getString("emailid");
                String address = res.getString("address");
                Date dos = res.getDate("dos");
                String areaVal = res.getString("area");
                String harkerid = res.getString("hawker");
                String paperVal = res.getString("papers");
                String price = res.getString("prices");
                int status = res.getInt("status");

                CustomersBean cbn = new CustomersBean(
                        mobile, name, email, address,
                        dos != null ? dos.toString() : "",
                        areaVal, harkerid, paperVal,price,String.valueOf(status)
                );
                list.add(cbn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
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
        ComboAreas.getItems().add("none");
        ComboPapers.getItems().add("none");
        ComboHawkers.getItems().add("none");
        ArrayList<String> ary = getAllAreas();
        for (String str : ary) {
            ComboAreas.getItems().add(str);
        }
        ArrayList<String> ppr = getAllPapers();
        for (String strr : ppr) {
            ComboPapers.getItems().add(strr);
        }
        ArrayList<String> hawk = getAllHawker();
        for (String strrr : hawk) {
            ComboHawkers.getItems().add(strrr);
        }


    }

    ArrayList<String> getAllAreas() {
        ArrayList<String> Areas = new ArrayList<String>();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT area FROM areas");
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                String area = res.getString("area");
                Areas.add(area);

            }
            System.out.println(Areas);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return Areas;
    }

    ArrayList<String> getAllPapers() {
        ArrayList<String> paper = new ArrayList<String>();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT paper FROM newspapers");
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                String paperr = res.getString("paper");
                paper.add(paperr);

            }
            System.out.println(paper);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return paper;
    }

    ArrayList<String> getAllHawker() {
        ArrayList<String> haw = new ArrayList<String>();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT name FROM Hawkers");
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                String hawker = res.getString("name");
                haw.add(hawker);

            }
            System.out.println(haw);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return haw;
    }

}
