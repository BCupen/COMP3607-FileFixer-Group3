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
        missingSubmissions=(new ArrayList<>());

        fileCollector = new FileCollector();
        fileProcessor = new FileProcessor();
        fileSaver = new FileSaver();
    }


    public Collection<String> getMissingSubmissions() {
        return missingSubmissions;
    }


    public void setMissingSubmissions(Collection<String> missingSubmissions) {
        this.missingSubmissions = missingSubmissions;
    }


    @Override
    public void renameFiles() {
        filesToBeRenamed = fileCollector.getFiles("./filefixer/src/lib/filesToRename/sample3");
        renamedFiles = fileProcessor.renameFiles(filesToBeRenamed);
        setMissingSubmissions(fileProcessor.getMissingSubmissions());
        fileSaver.saveFiles(filesToBeRenamed, renamedFiles);
    }
    
}
