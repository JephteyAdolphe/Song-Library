package jFiles.Controllers;

import resources.data.songData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class addPromptController {

    public Button cancel_button;
    public Button add_button;
    public TextField song_name;
    public TextField artist_name;
    public TextField album_name;
    public TextField song_year;

    private libraryController library = new libraryController();

    private songData data = new songData();

    // Validates the details of the song to be added

    public void add(MouseEvent mouseEvent) throws IOException {
        String songName = song_name.getText().trim();
        String artistName = artist_name.getText().trim();
        String albumName = album_name.getText();
        String songYear = song_year.getText();

        String track = songName + " - " + artistName;

        if (songName.length() > 0 && artistName.length() > 0 && (!library.getList().contains(track))) {
            data.write(track);
            if (albumName.trim().length() == 0) albumName = "n/a";
            if (songYear.trim().length() == 0) songYear = "n/a";

            data.writeToDetails(track, albumName, songYear);

            Parent libraryRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/libraryDisplay.fxml")));
            Scene library = new Scene(libraryRoot);

            Stage libraryWindow = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            libraryWindow.setScene(library);
            libraryWindow.show();
        } else {
            System.out.println("Song already exists");
        }
    }

    // Takes user back to the main display without adding a song

    public void cancel(MouseEvent mouseEvent) throws IOException {

        Parent libraryRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/libraryDisplay.fxml")));
        Scene library = new Scene(libraryRoot);

        Stage libraryWindow = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        libraryWindow.setScene(library);
        libraryWindow.show();
    }
}
