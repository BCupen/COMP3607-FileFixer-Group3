package group3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public abstract class FileProcessor {

    private Collection<File> originalFileNames;

    public FileProcessor(Collection<File> filesToBeRenamed){
        this.originalFileNames = filesToBeRenamed;

    }
    
    public Collection<File> renameFiles(Collection<File> filesToBeRenamed) {
        Collection<File> renamedFiles = new ArrayList<>();
        String fileInfo;
        
        for (File file:filesToBeRenamed){
            
            trimFile(file);
            fileInfo = getFileInfo(file.getName());
            stitchFile(file, fileInfo);

            renamedFiles.add(file);
        }
        return renamedFiles;
    }

    public abstract void trimFile(File file);
    public abstract void stitchFile(File file, String fileInfo);
    public abstract String getFileInfo(String name);
    
    public Collection<File> getOrginalFileNames(){
        return this.originalFileNames;
    }
}
