package resources.data;

import java.io.*;
import java.util.Scanner;

public class songData {
    private File file = new File("src/resources/data/songData.txt");

    public void write(String track, String album, String year) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(this.file, true));

        pw.println(track + " " + album + " " + year);
        pw.close();
    }


    public void clear() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(this.file);
        PrintWriter writerSong = new PrintWriter("src/resources/data/songData.txt");

        writer.close();
        writerSong.close();
    }
}
