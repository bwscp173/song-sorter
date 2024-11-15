import java.util.ArrayList;
import java.util.List;

public class AlbumDatabase {
    public static void main(String[] args) {
        file_reader fr = new file_reader("albums.txt");
        System.out.println("file_reader: " + fr.file_name);
        //fr.output_file();
        List<String> file_contents = new ArrayList<String>(fr.get_file_content());
        //System.out.println(file_contents.get(5));
        for (int i=0; i<file_contents.size();i++){
            String current_line = file_contents.get(i);
            System.out.println(current_line);
        }
    }
}
