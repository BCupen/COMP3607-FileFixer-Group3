package group3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class FileRenamerFacade implements FileRenamer{
    private Collection<File> filesToBeRenamed;
    private Collection<File> renamedFiles;
    private Collection<String> missingSubmissions;

    private FileCollector fileCollector;
    private FileProcessor fileProcessor;
    private FileSaver fileSaver;


    public FileRenamerFacade(){
        filesToBeRenamed = new ArrayList<>();
        renamedFiles = new ArrayList<>();
        missingSubmissions = new ArrayList<>();


        fileCollector = new FileCollector();
        fileProcessor = new Convention2FileProcessor();
        fileSaver = new FileSaver();
    }

    @Override
    public void renameFiles() {
        filesToBeRenamed = fileCollector.getFiles();
        renamedFiles = fileProcessor.renameFiles(filesToBeRenamed);
        missingSubmissions = ((Convention2FileProcessor)fileProcessor).getMissingSubmissions();
        fileSaver.saveFiles(renamedFiles);
    }
    
}
