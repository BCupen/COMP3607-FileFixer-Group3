package group3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FileSaver {

    private Collection<File> newFileNames;
    private Collection<File> originalFileNames;

    public FileSaver(){
        newFileNames = new ArrayList<>();
        originalFileNames = new ArrayList<>();
    
    }

    public void setOriginalFileNames(String loc){
        FileCollector originalfiles = new FileCollector();
        originalFileNames = originalfiles.getFiles(loc);  // parse the path as parameter
    }

    public void saveFiles(Collection<File> renamedFiles) {
        
        // raw file names should be pre-processed and converted to convention(1)
        setOriginalFileNames("./filefixer/src/lib/filesToRename/sample3"); 

        File dir = new File("./filefixer/src/lib/filesToRename/renamedFiles");

        if (dir.mkdir()){
            Iterator<File> originalFiles = originalFileNames.iterator();
            Iterator<File> newFilesNames = renamedFiles.iterator();

            while(originalFiles.hasNext() && newFilesNames.hasNext()){
                File origFile = originalFiles.next();
                File newFile = newFilesNames.next();

                if (origFile.renameTo(newFile)){
                    System.out.println("Renamed");
                }
                else
                    System.out.println("Not renamed");              
            }
        }
        else
            System.out.println("Oops, Something went wrong! Unable to create 'renamedFiles'");
        
    }

    public Collection<File> getOrginalFileNames(){
        return this.originalFileNames;
    }

    public Collection<File> getNewFileNames(){
        return this.newFileNames;
    }

    
}
