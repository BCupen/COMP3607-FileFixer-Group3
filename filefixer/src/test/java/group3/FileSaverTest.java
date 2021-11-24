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

    private static File location = new File("../filefixer/src/lib/filesToRename");
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
    
    private FileRenamerFacade fixFilesTest = new FileRenamerFacade(location);
    private Collection<File> foldersTest = new ArrayList<File>();
    private Collection<File> filesToBeRenamedTest = new ArrayList<File>();
    private Collection<File> renamedFilesTest = new ArrayList<File>();
    private Collection<String> missingSubmissionsTest = new ArrayList<String>();
    private Collection<File> convention1FilesTest = new ArrayList<File>();

    private FileCollector fileCollectorTest = new FileCollector();
    private FileProcessor fileProcessorTest = new FileProcessor();
    private FileSaver fileSaverTest = new FileSaver();
    private File csvFileTest;

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

            // fileCollector = new FileCollector();
            // filesToBeRenamed = new ArrayList<>();
            // renamedFiles = new ArrayList<>();
            // missingSubmissions = new ArrayList<>();
            // fileProcessor = new FileProcessor();
            // fileSaver = new FileSaver();
        }
    }

    @Test 
    public void testGetOriginalFileNames(){
        Collection<File> originalFiles = new ArrayList<File>();
        originalFiles = fileSaver.getOrginalFileNames();

        FileRenamerFacade fixFilesTest = new FileRenamerFacade(location);
        Collection<File> foldersTest = new ArrayList<File>();
        Collection<File> filesToBeRenamedTest = new ArrayList<File>();
        Collection<File> renamedFilesTest = new ArrayList<File>();
        Collection<String> missingSubmissionsTest = new ArrayList<String>();
        Collection<File> convention1FilesTest = new ArrayList<File>();
    
        FileCollector fileCollectorTest = new FileCollector();
        FileProcessor fileProcessorTest = new FileProcessor();
        FileSaver fileSaverTest = new FileSaver();
        File csvFileTest;

        foldersTest = fileCollectorTest.getFolders(location);

        for (File f : foldersTest) {
            System.out.println(f.getName()+" : ");
            filesToBeRenamedTest = fileCollectorTest.getFiles(f);
            csvFileTest = fileCollectorTest.getCSV(f);
            convention1FilesTest = fileProcessorTest.renameFiles(filesToBeRenamedTest, csvFileTest);
            renamedFilesTest = fileProcessorTest.getRenamedFiles();
            fixFilesTest.setMissingSubmissions(fileProcessorTest.getMissingSubmissions(csvFileTest));
            fileSaverTest.saveFiles(filesToBeRenamedTest, renamedFilesTest, f.toPath().toString());
            fileSaverTest.getMissingSubmissions(missingSubmissionsTest, f.toPath().toString());
        }

        Collection<File> expectedResult = new ArrayList<File>();
        expectedResult = fileSaverTest.getOrginalFileNames();

        assertEquals(expectedResult, originalFiles);
    }
    
}
