package jFiles.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class testController {
    public Stage showCustomerDialog(String customer) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/views/customerDialog.fxml"));

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene((Pane) loader.load()));

        CustomerDialogController controller = loader.<CustomerDialogController>getController();
        //controller.initData(customer);

        stage.show();

        return stage;
    }
}
