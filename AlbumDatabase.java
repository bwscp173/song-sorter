import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AlbumDatabase {
    public static void main(String[] args) {
        AlbumDatabase fr = new AlbumDatabase("albums.txt");
        System.out.println("file_reader: " + fr.file_name);
        fr.output_file();
        ArrayList<String> file_contents = new ArrayList<String>(fr.get_file_content());
        //System.out.println(file_contents.get(5));
        //System.out.println(file_contents);
        for (int i=0; i<file_contents.size();i++){
            String current_line = file_contents.get(i);
            //System.out.println(current_line);
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