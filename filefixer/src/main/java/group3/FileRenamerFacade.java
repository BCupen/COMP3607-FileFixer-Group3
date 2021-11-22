package group3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class FileRenamerFacade implements FileRenamer {
    private Collection<File> folders;
    private Collection<File> filesToBeRenamed;
    private Collection<File> renamedFiles;
    private Collection<String> missingSubmissions;
    private Collection<File> convention1Files;

    private FileCollector fileCollector;
    private FileProcessor fileProcessor;
    private FileSaver fileSaver;
    private File csvFile;
    private static File location;

    public FileRenamerFacade(File Loc) {
        filesToBeRenamed = new ArrayList<>();
        renamedFiles = new ArrayList<>();
        missingSubmissions = new ArrayList<>();
        fileCollector = new FileCollector();
        fileProcessor = new FileProcessor();
        fileSaver = new FileSaver();
        location = Loc;
    }

    public Collection<String> getMissingSubmissions() {
        return missingSubmissions;
    }

    public void setMissingSubmissions(Collection<String> missingSubmissions) {
        this.missingSubmissions = missingSubmissions;
    }

    public Collection<File> getConvention1Files() {
        return convention1Files;
    }

    @Override
    public void renameFiles() {

        folders = fileCollector.getFolders(location);

        for (File f : folders) {
            System.out.println(f.getName() + " : ");
            filesToBeRenamed = fileCollector.getFiles(f);
            csvFile = fileCollector.getCSV(f);
            convention1Files = fileProcessor.renameFiles(filesToBeRenamed, csvFile);
            renamedFiles = fileProcessor.getRenamedFiles();
            setMissingSubmissions(fileProcessor.getMissingSubmissions(csvFile));
            fileSaver.saveFiles(filesToBeRenamed, renamedFiles, f.toPath().toString());
            fileSaver.getMissingSubmissions(missingSubmissions, f.toPath().toString());
            System.out.println(
                    "________________________________________________________________________________________________________________________________________________");

            fileCollector = new FileCollector();
            filesToBeRenamed = new ArrayList<>();
            renamedFiles = new ArrayList<>();
            missingSubmissions = new ArrayList<>();
            fileProcessor = new FileProcessor();
            fileSaver = new FileSaver();
        }

    }

}
