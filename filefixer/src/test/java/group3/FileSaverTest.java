package group3;

// import org.junit.jupiter.api.AfterEach; //previously After
// import org.junit.jupiter.api.AfterAll; //previously AfterClass
import org.junit.jupiter.api.BeforeEach; //previously Before
// import org.junit.jupiter.api.BeforeAll;  //previously BeforeClass in Junit4
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class FileSaverTest {

    private static File location = new File("filefixer/src/lib/filesToRename");
    private FileRenamerFacade fixFiles = new FileRenamerFacade(location);
    private Collection<File> folders = new ArrayList<File>();
    private Collection<File> filesToBeRenamed = new ArrayList<File>();
    private Collection<File> renamedFiles = new ArrayList<File>();
    private Collection<String> missingSubmissions = new ArrayList<String>();
    private Collection<File> convention1Files = new ArrayList<File>();

    private FileCollector fileCollector = new FileCollector();
    private FileProcessor fileProcessor= new FileProcessor();
    private FileSaver fileSaver = new FileSaver();
    private File csvFile;
    
    public FileSaverTest(){

    }
    

    @BeforeEach
    public void setup(){
        folders = fileCollector.getFolders(location);

        for (File f : folders) {
            System.out.println(f.getName()+" : ");
            filesToBeRenamed = fileCollector.getFiles(f);
            csvFile = fileCollector.getCSV(f);
            convention1Files = fileProcessor.renameFiles(filesToBeRenamed, csvFile);
            renamedFiles = fileProcessor.getRenamedFiles();
            fixFiles.setMissingSubmissions(fileProcessor.getMissingSubmissions(csvFile));
            fileSaver.saveFiles(filesToBeRenamed, renamedFiles, f.toPath().toString());
            fileSaver.getMissingSubmissions(missingSubmissions, f.toPath().toString());
            System.out.println("______________________________________________________________________________________________________");

            fileCollector = new FileCollector();
            filesToBeRenamed = new ArrayList<>();
            renamedFiles = new ArrayList<>();
            missingSubmissions = new ArrayList<>();
            fileProcessor = new FileProcessor();
            fileSaver = new FileSaver();
        }
    }

}
