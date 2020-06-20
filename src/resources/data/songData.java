package resources.data;

import java.io.*;
import java.util.Scanner;

public class songData {

    private File file = new File("src/resources/data/songData.txt");
    private File detailsFile = new File("src/resources/data/songDetails.txt");

    // Writes to text file containing only the song / artist

    public void write(String track) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(file, true));

        pw.println(track);
        pw.close();
    }

    // Writes to text file containing the full details of the track

    public void writeToDetails(String track, String album, String year) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(detailsFile, true));

        pw.println(track + " - " + album + " - " + year);
        pw.close();
    }

    // Deletes all data connected to the selected track

    public void delete_track(String trackToDelete, String detailsToDelete) throws IOException {
        File inputFile = new File(String.valueOf(file));
        File tempFile = new File("src/resources/data/myTempFile.txt");

        BufferedReader track_reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter track_writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = track_reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(trackToDelete)) continue;
            track_writer.write(currentLine + System.getProperty("line.separator"));
        }
        track_writer.close();
        track_reader.close();

        //delete file
        boolean del = inputFile.delete();

        //rename file
        boolean newData = tempFile.renameTo(new File("src/resources/data/songData.txt"));

        File inputFile2 = new File(String.valueOf(detailsFile));
        File tempFile2 = new File("src/resources/data/myTempFile2.txt");

        BufferedReader details_reader = new BufferedReader(new FileReader(inputFile2));
        BufferedWriter details_writer = new BufferedWriter(new FileWriter(tempFile2));

        String curr;

        while((curr = details_reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmed = curr.trim();
            if(trimmed.equals(detailsToDelete.trim())) continue;
            details_writer.write(curr + System.getProperty("line.separator"));
        }
        details_writer.close();
        details_reader.close();

        //delete file
        boolean delDet = inputFile2.delete();

        //rename file
        boolean newDetails = tempFile2.renameTo(new File("src/resources/data/songDetails.txt"));
    }

    // Scans through, parses, and returns the full details of the selected track

    public String[] getDetails(String selected) throws FileNotFoundException {
        Scanner scan = new Scanner(detailsFile);
        String[] details = new String[4];

        while (scan.hasNextLine()) {
            String song_line = scan.nextLine();

            if (song_line.contains(selected)) {
                details = song_line.split(" - ");
            }
        }
        scan.close();
        return details;
    }
}
