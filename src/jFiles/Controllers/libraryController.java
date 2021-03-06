package jFiles.Controllers;

import jFiles.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import resources.data.songData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class libraryController implements Initializable {

    public Button add_button;
    public Button edit_button;
    public Button delete_button;
    public SplitPane wholeWindow;


    private ObservableList<String> observable_track_list = FXCollections.observableArrayList();
    public AnchorPane songSideBar;
    @FXML
    public ListView<String> song_list = new ListView<>();

    private ArrayList<String> listed_tracks = new ArrayList<>();

    public Label song_label;
    public Label artist_label;
    public Label album_label;
    public Label year_label;

    private File file = new File("src/resources/data/songData.txt");
    private File detailsFile = new File("src/resources/data/songDetails.txt");

    private songData data = new songData();

    // Takes user to the "add a song" prompt

    public void add_song(MouseEvent mouseEvent) throws IOException {

        Parent addRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/add_prompt.fxml")));
        Scene add_song = new Scene(addRoot);

        Stage add_song_prompt = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        add_song_prompt.setScene(add_song);
        add_song_prompt.show();
    }

    // Takes user to the "edit song" prompt

    public void edit_song(MouseEvent mouseEvent) throws IOException {
        String selected_track = song_list.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/edit_prompt.fxml"));

            Parent editRoot = (Parent) loader.load();
            Scene edit_song = new Scene(editRoot);
            editPromptController editor = loader.getController();
            editor.selectedTrack(selected_track);

            Stage edit_song_prompt = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            edit_song_prompt.setScene(edit_song);
            edit_song_prompt.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void delete_song(MouseEvent mouseEvent) throws IOException {

        String[] details = data.getDetails(song_list.getSelectionModel().getSelectedItem());
        String full_details = details[0] + " - " + details[1] + " - " + details[2] + " - " + details[3];

        //deletePromptController del = new deletePromptController();
        //del.selectedTrack(song_list.getSelectionModel().getSelectedItem(), details, full_details);

        //data.delete_track(song_list.getSelectionModel().getSelectedItem(), full_details);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/views/delete_prompt.fxml"));

            Parent delRoot = (Parent) loader.load();
            Scene delete_song = new Scene(delRoot);
            deletePromptController del = loader.getController();
            del.selectedTrack(song_list.getSelectionModel().getSelectedItem(), details, full_details);

            Stage del_song_prompt = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            del_song_prompt.setScene(delete_song);
            del_song_prompt.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Scans through the text file (mock database) containing the listed tracks

    private void scanFile() throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        song_list.setItems(observable_track_list);

        while (scan.hasNextLine()) {
            String song = scan.nextLine();
            if (!listed_tracks.contains(song)) {
                observable_track_list.add(song);
                listed_tracks.add(song);
                 }
            }

        Collections.sort(observable_track_list, String.CASE_INSENSITIVE_ORDER);
        scan.close();
        }

    // Returns the existing tracks

    public ArrayList<String>  getList() throws FileNotFoundException {
        scanFile();
        return listed_tracks;
    }

    // Loads the existing tracks

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            scanFile();
            song_list.getSelectionModel().select(0);
            fill_in_details();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
     }

     // Loads the details of the selected song in the main display

    private void fill_in_details() throws FileNotFoundException {
        String[] details = data.getDetails(song_list.getSelectionModel().getSelectedItem());

        song_label.setText(details[0]);
        artist_label.setText(details[1]);
        album_label.setText(details[2]);
        year_label.setText(details[3]);
    }

    public void item_selection(MouseEvent mouseEvent) throws FileNotFoundException {
        fill_in_details();
    }
}
