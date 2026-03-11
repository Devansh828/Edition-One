package com.example.paperproject.showerrormsg;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class ShowErrorMsg {
    @FXML
    public static   void 	showMsg(Alert alert, String title, String msg) {
        //Alert alert = new Alert(AlertType.INFORMATION);

        //Alert alert = new Alert(AlertType.WARNING);

        alert.setTitle(title);
        //or
        //alert.setTitle(null);

        alert.setContentText(msg);

        alert.showAndWait();
    }
}
