package jFiles.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerDialogController implements Initializable {

        @FXML
        private Label customerName;

        public void setDat (String customer){
            this.customerName.setText(customer);
            System.out.println(customer);
         }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
