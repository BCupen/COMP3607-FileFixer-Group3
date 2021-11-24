package group3;

import java.io.File;

public interface ConventionFileProcessor {

    public File renameFile();
    public String trimFile();
    public String stitchFile();
    public String getFileInfo();
    public String getOriginalFileName();

}