package jFiles.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class editPromptController implements Initializable {
    libraryController parser = new libraryController();


    public TextField song_name;
    public TextField artist_name;
    public TextField album_name;
    public TextField song_year;

    public void edit(MouseEvent mouseEvent) {
        //libraryController test = new libraryController();
        //System.out.println(test.song_list.getSelectionModel().getSelectedItem());
    }

    public void cancel(MouseEvent mouseEvent) throws IOException {

        Parent libraryRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/libraryDisplay.fxml")));
        Scene library = new Scene(libraryRoot);

        Stage libraryWindow = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        libraryWindow.setScene(library);
        libraryWindow.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    void selectedTrack(String track) throws FileNotFoundException {
        String[] details = parser.getDetails(track);

        song_name.setText(details[0]);
        artist_name.setText(details[1]);
        album_name.setText(details[2]);
        song_year.setText(details[3]);
    }
}
