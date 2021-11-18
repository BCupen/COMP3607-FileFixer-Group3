package group3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class FileRenamerFacade implements FileRenamer {
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
        missingSubmissions = (new ArrayList<>());

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

    @Override
    public void renameFiles() {
        filesToBeRenamed = fileCollector.getFiles(location);
        csvFile = fileCollector.getCSV(location);
        System.out.println("ALL FILES");
        for (File file : filesToBeRenamed) {

            System.out.println(file.getName());

        }
        convention1Files = fileProcessor.renameFiles(filesToBeRenamed, csvFile);
        System.out.println("ALL CONVENTION 1 FILES");
        for (File file : convention1Files) {

            System.out.println(file.getName());

        }
        renamedFiles = fileProcessor.getRenamedFiles();
        System.out.println("ALL RENAMED FILES");
        for (File file : renamedFiles) {

            System.out.println(file.getName());

        }
        setMissingSubmissions(fileProcessor.getMissingSubmissions(csvFile));

        fileSaver.saveFiles(filesToBeRenamed, renamedFiles,location.toPath().toString());
        fileSaver.getMissingSubmissions(missingSubmissions, location.toPath().toString());

    }

}
