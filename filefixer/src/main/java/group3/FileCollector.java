package group3;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FileCollector {
    private Collection<File> files;
    
    public FileCollector(){
        files =  new ArrayList<>();
    }

    public Collection<File> getFiles(String loc){
        File path =  new File(loc); // change location without having to modify File Collector
        List<File> allfiles = Arrays.asList(path.listFiles());

        for(File f: allfiles){
            if(!f.isDirectory() && f.getName().endsWith(".pdf"))  //only get the pdf files
                files.add(f);
        }

        // for(File f: files){
        //     System.out.println(f.getName());
        // }
        

        return files;
    }


}
