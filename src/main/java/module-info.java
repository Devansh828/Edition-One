module com.example.paperproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.paperproject to javafx.fxml;
    exports com.example.paperproject;

    opens com.example.paperproject.papermaster to javafx.fxml;
    exports com.example.paperproject.papermaster;

    opens com.example.paperproject.areas to javafx.fxml;
    exports com.example.paperproject.areas;

    opens com.example.paperproject.hawkers to javafx.fxml;
    exports com.example.paperproject.hawkers;

    opens com.example.paperproject.customers to javafx.fxml;
    exports com.example.paperproject.customers;

    opens com.example.paperproject.billing to javafx.fxml;
    exports com.example.paperproject.billing;

    opens com.example.paperproject.showpapers to javafx.fxml;
    exports com.example.paperproject.showpapers;

    opens com.example.paperproject.billboard to javafx.fxml;
    exports com.example.paperproject.billboard;

    opens com.example.paperproject.billcollector to javafx.fxml;
    exports com.example.paperproject.billcollector;

    opens com.example.paperproject.customersboard to javafx.fxml;
    exports com.example.paperproject.customersboard;

    opens com.example.paperproject.admindashboard to javafx.fxml;
    exports com.example.paperproject.admindashboard;

    opens com.example.paperproject.adminlogin to javafx.fxml;
    exports com.example.paperproject.adminlogin;
}
