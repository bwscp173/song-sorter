import java.io.File;
import java.io.FilenameFilter;


public class FileLocator implements FilenameFilter{
    private String extension;
    public void FileLocator(){
        this.extension = "";
    }

    public void set_extension(String extension_to_search_for){
        this.extension = extension_to_search_for;
    }

    @Override
    public boolean accept(File dir, String file){
        return file.endsWith(this.extension);
    }

    // going to return the list of all the files in the current directory
    public String[] ListFiles(){
        File path = new File(".");
        String[] all_files = path.list();
        return all_files;

    }
}