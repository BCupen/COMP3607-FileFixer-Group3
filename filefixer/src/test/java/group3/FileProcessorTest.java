package group3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileProcessorTest {
    private FileCollector fileCollector = new FileCollector();
    private FileProcessor fileProcessor = new FileProcessor();
    private static File location = new File("../filefixer/src/lib/filesToRename");
    private FileRenamerFacade fixFiles = new FileRenamerFacade(location);
    private Collection<File> folders = new ArrayList<File>();
    private Collection<File> filesToBeRenamed = new ArrayList<File>();
    private Collection<File> renamedFiles = new ArrayList<File>();
    private Collection<File> convention1Files = new ArrayList<File>();
    private File csvFile;

    private FileCollector fileCollectorTest = new FileCollector();
    private FileProcessor fileProcessorTest = new FileProcessor();
    private FileRenamerFacade fixFilesTest = new FileRenamerFacade(location);
    private Collection<File> foldersTest = new ArrayList<File>();
    private Collection<File> filesToBeRenamedTest = new ArrayList<File>();
    private Collection<File> renamedFilesTest = new ArrayList<File>();
    private Collection<File> convention1FilesTest = new ArrayList<File>();
    private File csvFileTest;

    @BeforeEach
    public void setUp(){
        folders = fileCollector.getFolders(location);

        for (File f : folders) {
            System.out.println(f.getName()+" : ");
            filesToBeRenamed = fileCollector.getFiles(f);
            csvFile = fileCollector.getCSV(f);
            convention1Files = fileProcessor.renameFiles(filesToBeRenamed, csvFile);
        }

        foldersTest = fileCollectorTest.getFolders(location);
        for (File f : foldersTest) {
            System.out.println(f.getName()+" : ");
            filesToBeRenamedTest = fileCollectorTest.getFiles(f);
            csvFileTest = fileCollectorTest.getCSV(f);
            convention1FilesTest = fileProcessorTest.renameFiles(filesToBeRenamedTest, csvFileTest);
            
        }
         
    }

    @Test
    public void testGetOriginalFileNames(){
        System.out.println("getOriginalFileNames()");
        Collection<File> result = fileProcessor.getOrginalFileNames();
        Collection<File> expectedResult = fileProcessorTest.getOrginalFileNames();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetRenamedFileNames(){
        System.out.println("getRenamedFiles()");
        Collection<File> result = fileProcessor.getRenamedFileNames();
        Collection<File> expectedResult = fileProcessorTest.getRenamedFileNames();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testRenameFiles(){
        System.out.println("renameFiles()");
        fileProcessor.renameFiles(filesToBeRenamed, csvFile);
        fileProcessorTest.renameFiles(filesToBeRenamedTest, csvFileTest);

        FileCollector col1 = new FileCollector();
        FileCollector col2 = new FileCollector();

        Collection<File> result = col1.getFiles(new File("../filefixer/src/lib/filesToRename/sample3/renamedFiles"));
        Collection<File> expectedResult = col2.getFiles(new File("../filefixer/src/lib/filesToRename/sample3/renamedFiles"));

        assertEquals(expectedResult, result);
        
    }

    @Test
    public void testGetRenamedFiles(){
        System.out.println("getRenamedFiles()");
        Collection<File> result = fileProcessor.getRenamedFiles();
        Collection<File> expectedResult = fileProcessorTest.getRenamedFileNames();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetMissingSubmissions(){
        System.out.println("setMissingSubmission()");
        File file1 = new File("../filefixer/src/lib/filesToRename/sample5/missingSubmission.txt");
        File file2 = new File("../filefixer/src/lib/filesToRename/sample1/missingSubmission.txt");
        assertTrue(file1.exists());
        assertTrue(file2.exists());

    }
}
