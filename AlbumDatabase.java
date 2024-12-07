import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AlbumDatabase {
    private final String file_name;


    public AlbumDatabase(String filename){
        this.file_name = filename;
    }

    // fun fact: there is a difference between 'boolean' and 'Boolean'. Boolean can hold a null value as its a object
    public static Boolean is_track(String line, int first_space_index){
        try {
            return line.charAt(first_space_index-3) == ':';
            }
        catch (Exception e) {
            // if the return in null then it will skip over the current line
            return null;
        }
        
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
        Duration line_duration_obj = new Duration(hr, min, sec);

        //making of the Track_obj
        Track line_track_obj = new Track(line_duration_obj,title);
        //System.out.println("addign track obj" + line_duration_obj.toString());
        current_album.add_track_obj(line_track_obj);
    }

    public static Album handle_album(String current_line, AlbumCollection all_albums){
        //gets everything upto the " : "
        String album_artist = new String(current_line.substring(0,current_line.indexOf(" : ")));
                
        //gets everything between the " : " and the start of the bracket used in year
        String album_title = new String(current_line.substring(current_line.indexOf(" : ") + 3, current_line.indexOf(" (")));

        //gets everything between the brackets
        int album_year = Integer.parseInt(current_line.substring(current_line.indexOf(" (") + 2, current_line.indexOf(")")));
    

        Album current_album = new Album(album_artist, album_title, album_year);
        all_albums.add_album_object(current_album);
        return current_album;
        // as its done by object the pointer inside the AlbumCollection class should get the data when its appended. instead of adding the data first then appending.
    }

    public static void main(String[] args) {
        FileLocator file_handler = new FileLocator();
        file_handler.set_extension(".txt");
        File current_dir = new File("."); // the path being "." means current directory
        String[] all_files = file_handler.ListFiles();
        ArrayList<String> all_txts = new ArrayList<>();

        for (String all_file : all_files) {
            //System.out.println("file: " + all_files[i]);
            if (file_handler.accept(current_dir, all_file)) {
                System.out.println("adding file: " + all_file);
                all_txts.add(all_file);
            }
        }

        for (int i = 0; i < all_txts.size(); i++) {
            
            
            AlbumDatabase fr = new AlbumDatabase(all_txts.get(i));  //fr for file_reader
            System.out.println("Reading from file: " + fr.file_name);
            ArrayList<String> file_contents = new ArrayList<>(fr.get_file_content());

            // i have to use placeholder text so the object can be initialised so java will compile it. even though
            // it gets overwritten immediatly by the else statement
            Album current_album = new Album("PLACEHOLDER", "PLACEHOLDER", 9999);  // stores the most recently seen album to append the track to
            AlbumCollection all_albums = new AlbumCollection();

            for (int j=0; j<file_contents.size();j++){
                String current_line = file_contents.get(j);

                if (current_line.length() == 0){
                    continue;
                }
                

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

                
                // if the line is empty, ignore the line
                if(is_track(current_line, first_space_index) == null){
                    continue;
                }


                else if (is_track(current_line, first_space_index) == true){
                    // it must be a track
                    try {
                        handle_track(current_line, first_space_index, current_album);
                    }
                    catch (java.lang.StringIndexOutOfBoundsException e) {
                        // this error occurs because the first_space_index 
                        System.out.println("[ERROR] Line(" + j + ") from file('" + fr.file_name + "'') is in a incorrect format");
                    }
                }

                else if (is_track(current_line, first_space_index) == false){
                    // else it must be a album
                    try {
                        current_album = handle_album(current_line, all_albums);
                    }
                    catch (java.lang.StringIndexOutOfBoundsException e) {
                        System.out.println("[ERROR] Line(" + j + ") from file('" + fr.file_name + "') is in a incorrect format");
                    }
                }
            }
            
            //print each album in all_albums, arranging the songs into alphabetical order using comparable
            ArrayList<Album> all_albums_list = all_albums.get_Albums();
            
            Duration kraftwerkDuration = new Duration(0, 0, 0); // Creates a duration object for Kraftwerk albums
            String shortestAlbumName = "PLACEHOLDERPLACEHOLDERPLACEHOLDER"; // Will hold the shortest album name - pasted PLACEHOLDER 3x to make sure
                                                                            // it is longer than some names, otherwise there is a chance it will be
                                                                            // shorter than all album names and will not be overwritten
            Track longestTrack = new Track(new Duration(0, 0, 0), "PLACEHOLDER"); // Will hold the longest track

            List<Album> sortedAlbums = new ArrayList<>(all_albums_list);
            Collections.sort(sortedAlbums, (album1, album2) -> {
                int artistCompare = album1.get_artist().compareTo(album2.get_artist());
                if (artistCompare == 0) {
                    return Integer.compare(album1.get_year(), album2.get_year());
                } else {
                    return artistCompare;
                }
            });
            
            for (Album album : sortedAlbums){ // Iterates through each album

                if (album.get_title().length() < shortestAlbumName.length()){ // Checks if current album is the shortest album name
                    shortestAlbumName = album.get_title(); // If it is shorter, it replaces the current shortest name
                }

                if (album.get_artist().equals("Kraftwerk")) { // Only add duration if the artist is Kraftwerk
                    Duration albumDuration = album.get_duration_obj();
                    kraftwerkDuration.add_hr(albumDuration.get_hr()); // Adding the durations
                    kraftwerkDuration.add_min(albumDuration.get_min());
                    kraftwerkDuration.add_sec(albumDuration.get_sec());
                };


                System.out.println("\n" + album.toString()); // Prints the details of the album (e.g., 0:51:9::Pink Floyd : Momentary Lapse of Reason (1987))

                for (Track track : album.get_track_obj()){ // Loops for each track in the album
                    System.out.println("\t" + track.toString()); // Prints the track & its duration
                    // Checks if the current track is longer than the longest track
                    if (track.get_duration().get_hr() > longestTrack.get_duration().get_hr()){ // First checks the hours section of the timestamp
                        longestTrack = track; // If the new track lasts for more hours, it is longer and replaces the current longest track
                    }
                    else if (track.get_duration().get_min() > longestTrack.get_duration().get_min()){ // checks minutes
                        longestTrack = track; // Same as above except for minutes instead of hours
                    }
                    else if (track.get_duration().get_sec() > longestTrack.get_duration().get_sec()){ // checks seconds
                        longestTrack = track; // Same as above except for seconds
                    }
                }
            }
            System.out.println("\n\n\nTotal duration of Kraftwerk albums: " + kraftwerkDuration.toString()); // Prints final total duration of Kraftwerk albums
            System.out.println("\nShortest album name: " + shortestAlbumName); // Prints the album with the shortest name
            System.out.println("\nTrack with the longest duration: " + longestTrack.toString() + "\n\n"); // Prints the track with the longest duration
        }
        
    }

    public ArrayList<String> get_file_content(){
        ArrayList<String> contents = new ArrayList<>();

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