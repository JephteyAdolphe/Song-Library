package jFiles.Controllers;

import jFiles.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.SplitPane;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class libraryController {


    public Button add_button;
    public Button edit_button;
    public Button delete_button;
    public SplitPane wholeWindow;


    public ObservableList<String> observable_track_list = FXCollections.observableArrayList();
    public AnchorPane songSideBar;
    @FXML
    public ListView<String> song_list = new ListView<>();
    public ArrayList<String> listed_tracks = new ArrayList<>();
    //private songData data = new songData();
    private File file = new File("src/resources/data/songData.txt");

    public void add_song(MouseEvent mouseEvent) throws IOException {

      /*  Scanner scan = new Scanner(file);
        String trackInFile = scan.nextLine();

        song_list.getItems().add(trackInFile);*/
        //scanFile();

        Parent addRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/add_prompt.fxml")));
        Scene add_song = new Scene(addRoot);

        Stage add_song_prompt = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        add_song_prompt.setScene(add_song);
        add_song_prompt.show();
    }

    public void edit_song(MouseEvent mouseEvent) throws FileNotFoundException {
        //song_list.getItems().add("Edit Pressed");
    }

    public void delete_song(MouseEvent mouseEvent) {
    }

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
        }

    @FXML
    public void initialize() throws FileNotFoundException {
        scanFile();
        }
    }

