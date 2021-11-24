package group3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.File;
public class FileCollectorTest {
    private static FileCollector fileCollector;
    private File path = new File ("../filefixer/src/lib/filesToRename/sample1");

    @BeforeAll
    public static void setUp(){
        fileCollector = new FileCollector();
    }

    @Test
    public void testGetFiles(){
        List<File> allFiles = Arrays.asList(path.listFiles());
        ArrayList<File> expected =  new ArrayList<File>();
        for(File f: allFiles){
            if(!f.isDirectory() && f.getName().endsWith("pdf"))
                expected.add(f);
        }
        List<File> actualFiles = (List<File>) fileCollector.getFiles(path);

        assertEquals(expected, (List<File>)actualFiles);
    }

    @Test
    public void testGetCSV(){
        System.out.println("getCSV()");
        path = new File ("../filefixer/src/lib/filesToRename/sample1");
        File result = fileCollector.getCSV(path);

        FileCollector collector2 = new FileCollector();
        File expectedResult = collector2.getCSV(path);

        assertEquals(expectedResult, result);
        
    }  

    // @Test
    // public void testGetFolders(){
    //     path = new File("../filefixer/src/lib/filesToRename/Cases");
    //     List<File> allFiles = Arrays.asList(path.listFiles());
    //     ArrayList<File> expected = new ArrayList<File>();
    //     for (File f: allFiles){
    //         if(f.getName().endsWith(".zip"))
    //             expected.add(f);
    //     }

    //     List<File> actualFolders = (List<File>)fileCollector.getFolders(path);
    //     assertNull(actualFolders);
    // }

}