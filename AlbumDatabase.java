import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AlbumDatabase {
    private String file_name;


    public AlbumDatabase(String filename){
        this.file_name = filename;
    }

    // fun fact: there is a difference between 'boolean' and 'Boolean'. Boolean can hold a null value as its a object
    public static boolean is_track(String line, int first_space_index){
        return line.charAt(first_space_index-3) == ':';
    }

    //will return the hr,min,seconds,title
    public static void handle_track(String current_line, int first_space_index, Album current_album){
        // getting the hour min secons dynamically (so its more resistant to hrs larger than 9)
        String total_time = new String(current_line.substring(0,first_space_index));
        // total_time example:  "0:00:38"

        int hr = Integer.parseInt(total_time.substring(0,total_time.indexOf(":")));
        total_time = new String(total_time.substring(total_time.indexOf(":")+1));
        // total_time_example: "00:38"
        
        int min = Integer.parseInt(total_time.substring(0,total_time.indexOf(":")));
        total_time = new String(total_time.substring(total_time.indexOf(":")));
        // total_time_example: ":38"
        
        int sec = Integer.parseInt(total_time.substring(1));  // just cuts the ":" and everything else is just the seconds.


        // now getting the title
        String title = new String(current_line.substring(current_line.indexOf(" - ") + 3));  // finds the gap between the time and title, adds 3 because the string to find is 3 long, removes everything before that point.
        duration line_duration_obj = new duration(hr, min, sec);


        //making of the Track_obj
        Track line_track_obj = new Track(line_duration_obj,title);
        current_album.add_track_obj(line_track_obj);
    }

    public static void handle_album(String current_line, AlbumCollection all_albums){
        //gets everything upto the " : "
        String album_artist = new String(current_line.substring(0,current_line.indexOf(" : ")));
                
        //gets everything between the " : " and the start of the bracket used in year
        String album_title = new String(current_line.substring(current_line.indexOf(" : ") + 3, current_line.indexOf(" (")));

        //gets everything between the brackets
        Integer album_year = Integer.parseInt(current_line.substring(current_line.indexOf(" (") + 2, current_line.indexOf(")")));


        Album current_album = new Album(album_artist, album_title, album_year);
        all_albums.add_album_object(current_album);
        // as its done by object the pointer inside the AlbumCollection class should get the data when its appended. instead of adding the data first then appending.
    }

    public static void main(String[] args) {
        AlbumDatabase fr = new AlbumDatabase("albums.txt");  //fr for file_reader
        System.out.println("Reading from file: " + fr.file_name);
        ArrayList<String> file_contents = new ArrayList<String>(fr.get_file_content());

        // i have to use placeholder text so the object can be initialised so java will compile it. even though
        // it gets overwritten immediatly by the else statement
        Album current_album = new Album("PLACEHOLDER", "PLACEHOLDER", 9999);  // stores the most recently seen album to append the track to
        AlbumCollection all_albums = new AlbumCollection();

        for (int i=0; i<file_contents.size();i++){
            String current_line = file_contents.get(i);
            

            // The Beatles : Rubber Soul (1965)
            // 0:02:25 - Drive My Car
            // for example these next lines will check for the first space starting from the left.
            // then it will go back 3 characters, if this is a ':' then it has to be a song
            // else it will be a artists name
            // doing it this way because of a small edge case where the artists name starts with a number so we cant check if the first character is a number
            // cant check the location of the string for a ":" at a static position because there is a small edge case for a +10hr song/albumcollection.
            
            
            // todo: decide if this first_space index should be put into the is_track and handle_track functions
            // as it looks nicer if its put inside the function not as a augument, But its wasted data if it is.
            int first_space_index = current_line.indexOf(" ");
            if (is_track(current_line, first_space_index)){
                // it must be a track                
                handle_track(current_line, first_space_index, current_album);

            }
            else{
                // else it must be a album
                handle_album(current_line, all_albums);

            }
        }
    }

    public void output_file(){
        // function isnt used much anymore, just for de-bugging at the start of the project.
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