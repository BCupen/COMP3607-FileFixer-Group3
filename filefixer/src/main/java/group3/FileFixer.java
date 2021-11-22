package group3;

import java.io.*;

public class FileFixer {
    public static void main(String[] args) {
        FileRenamerFacade FixFiles;
        File path = new File("filefixer/src/lib/filesToRename");
        FixFiles = new FileRenamerFacade(path);
        FixFiles.renameFiles();
    }
}
