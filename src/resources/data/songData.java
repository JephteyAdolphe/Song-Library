package resources.data;

import java.io.*;
import java.util.Scanner;

public class songData {
    private File file = new File("src/resources/data/songData.txt");
    private File detailsFile = new File("src/resources/data/songDetails.txt");

    public void write(String track) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(this.file, true));

      //  pw.println(track + " " + album + " " + year);
        pw.println(track);
        pw.close();
    }

    public void writeToDetails(String track, String album, String year) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(this.detailsFile, true));

        pw.println(track + " - " + album + " - " + year);
        pw.close();
    }


    public void clear() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(this.file);
        PrintWriter writerSong = new PrintWriter("src/resources/data/songData.txt");

        writer.close();
        writerSong.close();
    }
}
