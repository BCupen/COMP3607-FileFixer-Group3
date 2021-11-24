package group3;

// import org.junit.jupiter.api.AfterEach; //previously After
import org.junit.jupiter.api.AfterAll; //previously AfterClass
import org.junit.jupiter.api.BeforeEach; //previously Before
import org.junit.jupiter.api.BeforeAll;  //previously BeforeClass in Junit4
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class NoConventionProcessorTest {
    
    NoConventionProcessor c1, c2, c3;
    private File csvFile = new File("../filefixer/src/lib/filesToRename/sample5/Sample 5 CSV.csv");
    private File file1 = new File("../filefixer/src/lib/filesToRename/sample5/81333104.pdf");
    private File file2 = new File("../filefixer/src/lib/filesToRename/sample5/Info 2603 Assignment 1-Maryann Steele.pdf");
    private File file3 = new File("../filefixer/src/lib/filesToRename/sample5/Assignment 1-81343013.pdf");
    //filefixer\src\lib\filesToRename\Cases\sample5\81333104.pdf
    //filefixer\src\lib\filesToRename\Cases\sample5\Info 2603 Assignment 1-Maryann Steele.pdf
    //filefixer\src\lib\filesToRename\Cases\sample5\Assignment 1-81343013.pdf
    
    @BeforeEach
    public void setUp(){ 

        c1 = new NoConventionProcessor(file1, csvFile);
        c2 = new NoConventionProcessor(file2, csvFile);
        c3 = new NoConventionProcessor(file3, csvFile);

     }

    @Test
    public void TestgetOriginalFileName(){
        System.out.println("getOriginalFileName()");
        String actualResult1 = c1.getOriginalFileName();
        String expectedResult1 = "81333104.pdf";

        String actualResult2 = c2.getOriginalFileName();
        String expectedResult2 = "Info 2603 Assignment 1-Maryann Steele.pdf";

        String actualResult3 = c3.getOriginalFileName();
        String expectedResult3 = "Assignment 1-81343013.pdf";

       
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }
    
    @Test
    public void TesttrimFile(){
        System.out.println("trimFile()");
        String actualResult1 = c1.trimFile();
        String expectedResult1 = "81333104";

        String actualResult2 = c2.trimFile();
        String expectedResult2 = "Maryann Steele";
        
        String actualResult3 = c3.trimFile();
        String expectedResult3 = "81343013";

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);


    }

    @Test
    public void TestgetFileInfo(){
        System.out.println("getFileInfo()");
        String actualResult1 = c1.getFileInfo();
        String expectedResult1 = "Josephine_Hodges_601700";

        String actualResult2 = c2.getFileInfo();
        String expectedResult2 = "Maryann_Steele_601725";
        
        String actualResult3 = c3.getFileInfo();
        String expectedResult3 = "Lynette_Arnold_601729";

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }

}