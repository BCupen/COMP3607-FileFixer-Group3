package group3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;

public class FileCollectorTest {
    FileRenamerFacade fixFiles;
    private FileCollector fileCollector;
    private File loc;

    @BeforeEach
    public void setUp(){
        fileCollector = new FileCollector();
    }

    @Test
    public void testGetCSV(){
        System.out.println("getCSV()");
        loc = new File ("../filefixer/src/lib/filesToRename/sample1");
        File result = fileCollector.getCSV(loc);

        FileCollector collector2 = new FileCollector();
        File expectedResult = collector2.getCSV(loc);

        assertEquals(expectedResult, result);
        
    }

}
