package group3;

import java.io.*;


public class Type1Processor implements Convention2FileProcessor{

    private File file;

    public Type1Processor(File file, File csvFile){ 
        super();
        this.file=file;
    }
    @Override
    public String trimFile() {
        return null;
    }

    @Override
    public String stitchFile() {
        return null;
    }

    @Override
    public String getFileInfo() {
        return null;
    }

    @Override
    public File renameFile() {
        return this.file;
    }


    
    
}
