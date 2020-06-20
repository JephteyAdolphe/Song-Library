package jFiles.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.data.songData;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class editPromptController implements Initializable {
    private songData data = new songData();


    public TextField song_name;
    public TextField artist_name;
    public TextField album_name;
    public TextField song_year;

    private String old_track;
    private String old_details;

    // Confirms the edit made by the user

    public void edit(MouseEvent mouseEvent) throws IOException {
        String album;
        String year;

        if (song_name.getText().trim().length() > 0 && artist_name.getText().trim().length() > 0) {

            String new_track = song_name.getText().trim() + " - " + artist_name.getText().trim();

            data.delete_track(old_track, old_details);

            if (album_name.getText().trim().length() == 0) {
                album = "n/a";
            } else {
                album = album_name.getText().trim();
            }
            if (song_year.getText().trim().length() == 0) {
                year = "n/a";
            } else {
                year = song_year.getText().trim();
            }

            data.write(new_track);
            data.writeToDetails(new_track, album, year);

            //if (songName.trim().length() > 0 && artistName.trim().length() > 0
            Parent libraryRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/libraryDisplay.fxml")));
            Scene library = new Scene(libraryRoot);

            Stage libraryWindow = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            libraryWindow.setScene(library);
            libraryWindow.show();
        }

    }

    // Takes user back to main display without altering the songs

    public void cancel(MouseEvent mouseEvent) throws IOException {

        Parent libraryRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/libraryDisplay.fxml")));
        Scene library = new Scene(libraryRoot);

        Stage libraryWindow = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        libraryWindow.setScene(library);
        libraryWindow.show();
    }

    // Loads up the data of the song to be deleted

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    void selectedTrack(String track) throws FileNotFoundException {
        String[] details = data.getDetails(track);

        song_name.setText(details[0]);
        artist_name.setText(details[1]);
        album_name.setText(details[2]);
        song_year.setText(details[3]);

        old_track = details[0] + " - " + details[1];
        old_details = old_track + " - " + details[2] + " - " + details[3];

    }
}
