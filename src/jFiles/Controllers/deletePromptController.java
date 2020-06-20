package jFiles.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.data.songData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class deletePromptController implements Initializable {
    public Button delete_button;
    public Button cancel_button;
    public Label song_name;
    public Label artist_name;
    public Label album_name;
    public Label song_year;

    private String full_track;
    private String full_details;

    private songData data = new songData();


    public void delete(MouseEvent mouseEvent) throws IOException {

        data.delete_track(full_track, full_details);


        Parent libraryRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/libraryDisplay.fxml")));
        Scene library = new Scene(libraryRoot);

        Stage libraryWindow = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        libraryWindow.setScene(library);
        libraryWindow.show();
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

    void selectedTrack(String track, String[] detailsArr, String detailsString) throws FileNotFoundException {
        //String[] details = data.getDetails(track);

        song_name.setText(detailsArr[0]);
        artist_name.setText(detailsArr[1]);

        if (detailsArr[2].equals("n/a")) {
            album_name.setText(" ");
        } else {
            album_name.setText(detailsArr[2]);
        }

        if (detailsArr[3].equals("n/a")) {
            song_year.setText(" ");
        } else {
            song_year.setText(detailsArr[3]);
        }

        full_track = track;
        full_details = detailsString;
    }
}
