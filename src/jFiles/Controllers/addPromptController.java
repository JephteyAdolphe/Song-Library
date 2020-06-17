package jFiles.Controllers;

import resources.data.songData;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class addPromptController {

    public Button cancel_button;
    public Button add_button;
    public TextField song_name;
    public TextField artist_name;
    public TextField album_name;
    public TextField song_year;

    libraryController library = new libraryController();
    private ObservableList<String> trackList;
    private File file = new File("src/resources/data/songData.txt");

    private songData data = new songData();

    public void add(MouseEvent mouseEvent) throws IOException {
        String songName = song_name.getText();
        String artistName = artist_name.getText();
        String albumName = album_name.getText();
        String songYear = song_year.getText();

        String track = songName + " - " + artistName;   // Maybe use hashmap to hold album and year for each song (in library controller)

        // Use regex so user must enter characters and not just all whitespace

        if (!songName.equals("") && !artistName.equals("") && (!library.getList().contains(track))) {
            data.write(track);
            if (albumName.equals("")) albumName = " ";
            if (songYear.equals("")) songYear = " ";

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

    public void cancel(MouseEvent mouseEvent) throws IOException {

        Parent libraryRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/libraryDisplay.fxml")));
        Scene library = new Scene(libraryRoot);

        Stage libraryWindow = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        libraryWindow.setScene(library);
        libraryWindow.show();
    }
}
