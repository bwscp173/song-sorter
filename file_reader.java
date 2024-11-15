import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class file_reader{
    public String file_name;

    public file_reader(String filename){
        this.file_name = filename;
    }

    public void output_file(){
        // code copied and then modified from https://www.w3schools.com/java/java_files_read.asp
        try {
            File myObj = new File(this.file_name);
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

    public List<String> get_file_content(){
        List<String> contents = new ArrayList<String>();

        // code copied and then modified from https://www.w3schools.com/java/java_files_read.asp
        try {
            File myObj = new File(this.file_name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String current_line = myReader.nextLine();
                
                contents.add(current_line);

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