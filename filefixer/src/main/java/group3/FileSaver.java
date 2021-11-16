package group3;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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

    public void saveFiles(Collection<File> originalFileNames, Collection<File> renamedFiles) {
        
        setOrginalFileNames(originalFileNames);

        File dir = new File("./filefixer/src/lib/filesToRename/renamedFiles");

        if (dir.mkdir()){
            Iterator<File> originalFiles = originalFileNames.iterator();
            Iterator<File> newFilesNames = renamedFiles.iterator();

            while(originalFiles.hasNext() && newFilesNames.hasNext()){
                File origFile = originalFiles.next();
                File newFile = newFilesNames.next();

                try {
                    Files.copy(origFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                }
             }
            }
        else
            System.out.println("Oops, Something went wrong! Unable to create 'renamedFiles'");
        
    }

    public Collection<File> getOrginalFileNames(){
        return this.originalFileNames;
    }

    public void setOrginalFileNames(Collection <File> originalFileNames){
        this.originalFileNames = originalFileNames;
    }

    public Collection<File> getNewFileNames(){
        return this.newFileNames;
    }

    

    
}
