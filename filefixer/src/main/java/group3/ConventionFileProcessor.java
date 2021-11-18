package group3;

import java.io.File;

public interface ConventionFileProcessor {
    
  

    public File renameFile();
    public abstract String trimFile();
    public abstract String stitchFile();
    public abstract String getFileInfo();
    public abstract String getOriginalFileName();

}