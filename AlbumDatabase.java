import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AlbumDatabase {
    public static void main(String[] args) {
        AlbumDatabase fr = new AlbumDatabase("albums.txt");
        System.out.println("Reading from file: " + fr.file_name);
        //fr.output_file();
        ArrayList<String> file_contents = new ArrayList<String>(fr.get_file_content());
        int first_space_index;
        String title;

        for (int i=0; i<file_contents.size();i++){
            String current_line = file_contents.get(i);

            // The Beatles : Rubber Soul (1965)
            // 0:02:25 - Drive My Car
            // for example these next lines will check for the first space starting from the left.
            // then it will go back 3 characters, if this is a ':' then it has to be a song
            // else it will be a artists name
            // doing it this way because of a small edge case where the artists name starts with a number so we cant check if the first character is a number
            // cant check the location of the string for a ":" at a static position because there is a small edge case for a +10hr song/albumcollection.
            // 
            first_space_index = current_line.indexOf(" ");
            if (current_line.charAt(first_space_index-3) == ':'){
                // it must be a track
                
                // getting the hour min secons dynamically (so its more resistant to hrs larger than 9)
                String total_time = new String(current_line.substring(0,first_space_index));
                // total_time example:  "0:00:38"

                int hr = Integer.parseInt(total_time.substring(0,total_time.indexOf(":")));
                total_time = new String(total_time.substring(total_time.indexOf(":")+1));
                // total_time_example: "00:38"
                
                int min = Integer.parseInt(total_time.substring(0,total_time.indexOf(":")));
                total_time = new String(total_time.substring(total_time.indexOf(":")));
                // total_time_example: ":38"
                
                int sec = Integer.parseInt(total_time.substring(1));
                total_time = new String(total_time.substring(1));


                // now getting the title
                title = new String(current_line.substring(current_line.indexOf(" - ") + 3));  // finds the gap between the time and title, adds 3 because the string to find is 3 long, removes everything before that point.
                duration line_duration_obj = new duration(hr, min, sec);


                //making of the Track_obj
                Track line_track_obj = new Track(line_duration_obj,title);
                System.out.println("line_track_obj.toString():  "+line_track_obj.toString());

                //int min = current_line.substring(i)
                //duration line_duration_obj = new duration(current_line[first_space_index])
                //public duration(int hr,int min,int sec)

            
            }
            else{
                // it must be a album
                ;
            }
        }
    }

    public String file_name;

    public AlbumDatabase(String filename){
        this.file_name = filename;
    }

    public void output_file(){
        // code copied and then modified from https://www.w3schools.com/java/java_files_read.asp
        try {
            File myObj = new File(this.file_name);  // changed the filename
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                
            }
            myReader.close();
            }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<String> get_file_content(){
        ArrayList<String> contents = new ArrayList<String>();

        // code copied and then modified from https://www.w3schools.com/java/java_files_read.asp
        try {
            File myObj = new File(this.file_name);  // changed the filename
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String current_line = myReader.nextLine();
                
                contents.add(current_line);  // added this line

            }
            myReader.close();
            }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return contents;
    }
}