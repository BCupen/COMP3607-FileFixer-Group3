package group3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import java.util.*;

public class FileSaverTest {

    private static File location = new File("../filefixer/src/lib/filesToRename");
    private FileRenamerFacade fixFiles = new FileRenamerFacade(location);
    private Collection<File> folders = new ArrayList<File>();
    private Collection<File> filesToBeRenamed = new ArrayList<File>();
    private Collection<File> renamedFiles = new ArrayList<File>();
    private Collection<String> missingSubmissions = new ArrayList<String>();
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
            fileProcessor.renameFiles(filesToBeRenamed, csvFile);
            renamedFiles = fileProcessor.getRenamedFiles();
            fixFiles.setMissingSubmissions(fileProcessor.getMissingSubmissions(csvFile));
            fileSaver.saveFiles(filesToBeRenamed, renamedFiles, f.toPath().toString());
            fileSaver.getMissingSubmissions(missingSubmissions, f.toPath().toString());
        }
        
        foldersTest = fileCollectorTest.getFolders(location);
        for (File file : foldersTest) {
            System.out.println(file.getName()+" : ");
            filesToBeRenamedTest = fileCollectorTest.getFiles(file);
            csvFileTest = fileCollectorTest.getCSV(file);
            fileProcessorTest.renameFiles(filesToBeRenamedTest, csvFileTest);
            renamedFilesTest = fileProcessorTest.getRenamedFiles();
            fixFilesTest.setMissingSubmissions(fileProcessorTest.getMissingSubmissions(csvFileTest));
            fileSaverTest.saveFiles(filesToBeRenamedTest, renamedFilesTest, file.toPath().toString());
            fileSaverTest.getMissingSubmissions(missingSubmissionsTest, file.toPath().toString());
        }
    }

    @Test 
    public void testGetOriginalFileNames(){
        System.out.println("getOriginalFileNames()");
        Collection<File> originalFiles = new ArrayList<File>();
        originalFiles = fileSaver.getOrginalFileNames();
        foldersTest = fileCollectorTest.getFolders(location);

        Collection<File> expectedResult = new ArrayList<File>();
        expectedResult = fileSaverTest.getOrginalFileNames();

        assertEquals(expectedResult, originalFiles);
    }

    @Test
    public void testSetMissingSubmissions(){
        System.out.println("setMissingSubmission()");
        File file1 = new File("../filefixer/src/lib/filesToRename/sample5/missingSubmission.txt");
        File file2 = new File("../filefixer/src/lib/filesToRename/sample1/missingSubmission.txt");
        assertTrue(file1.exists());
        assertTrue(file2.exists());

    }

    @Test
    public void testGetNewFileNames(){
        System.out.println("getNewFileNames()");
        Collection<File> result = new ArrayList<File>();
        Collection<File> expectedResult = new ArrayList<File>();

        result = fileSaver.getNewFileNames();
        expectedResult = fileSaverTest.getNewFileNames();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetOriginalFileNames(){
        System.out.println("setOriginalFileNames()");
        Collection<File> expectedResult = new ArrayList<File>();
        expectedResult = fileSaverTest.getNewFileNames();
        fileSaver.setOrginalFileNames(expectedResult);

        assertEquals(expectedResult, fileSaver.getOrginalFileNames());


    }
}