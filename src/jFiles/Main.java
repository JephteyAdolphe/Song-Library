package jFiles;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import jFiles.Controllers.libraryController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main extends Application {
    private File file = new File("src/resources/data/songData.txt");
    @FXML
    public ListView<String> song_list = new ListView<>();

    public ObservableList<String> observable_track_list = FXCollections.observableArrayList();


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent libraryRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/libraryDisplay.fxml")));
        Scene library = new Scene(libraryRoot);

        primaryStage.setTitle("Jeff's Song Library");
        primaryStage.setScene(library);
        primaryStage.show();

    }

    public void scanFile() throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        song_list.setItems(observable_track_list);

        while (scan.hasNextLine()) {
            //song_list.getItems().add(scan.nextLine());
            observable_track_list.add(scan.nextLine());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Program closed");
    }
}
