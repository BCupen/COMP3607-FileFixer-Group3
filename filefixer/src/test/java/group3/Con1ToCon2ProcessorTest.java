package group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class Con1ToCon2ProcessorTest {

    Con1ToCon2Processor c1, c2, c3, c4, c5;
    private File csvFile = new File("../filefixer/src/lib/filesToRename/sample3/Sample 3 CSV.csv");
    private File file1 = new File("../filefixer/src/lib/filesToRename/sample3/1409121490-602637_Beth_Morales-Horton_601683_Assignment1_81305512.pdf");
    private File file2 = new File("../filefixer/src/lib/filesToRename/sample3/1410583171-602403_Gloria_Harmon_601720_Assignment 1 -Info 2603.pdf");
    private File file3 = new File("../filefixer/src/lib/filesToRename/sample3/1410693229-601945_Darrell_Rufus_Porter_601706_Assignment 1.pdf");
    private File file4= new File("../filefixer/src/lib/filesToRename/sample3/1410820386-602084_Laurence_Rodriguez_601724_Assignment 1 -Jelani Simmons(81370818).pdf");
    private File file5 = new File("../filefixer/src/lib/filesToRename/sample3/1420369269-602492_Lorene_Craig_601701_INFO 2603 A1 81304445.pdf");


    @Before
    public void setUp(){ 

        c1 = new Con1ToCon2Processor(file1, csvFile);
        c2 = new Con1ToCon2Processor(file2, csvFile);
        c3 = new Con1ToCon2Processor(file3, csvFile);
        c4 = new Con1ToCon2Processor(file4, csvFile);
        c5 = new Con1ToCon2Processor(file5, csvFile);

     }

    @Test
    public void TestgetOriginalFileName()
    {
        
        String actualResult1 = c1.getOriginalFileName();
        String expectedResult1 = "Assignment1_81305512.pdf";

        String actualResult2 = c2.getOriginalFileName();
        String expectedResult2 = "Assignment 1 -Info 2603.pdf";

        String actualResult3 = c3.getOriginalFileName();
        String expectedResult3 = "Assignment 1.pdf";

        String actualResult4 = c4.getOriginalFileName();
        String expectedResult4 = "Assignment 1 -Jelani Simmons(81370818).pdf";

        String actualResult5 = c5.getOriginalFileName();
        String expectedResult5 = "INFO 2603 A1 81304445.pdf";
       
        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
        assertEquals(expectedResult4, actualResult4);
        assertEquals(expectedResult5, actualResult5);
        
    }
    
    @Test
    public void TesttrimFile(){

        String actualResult1 = c1.trimFile();
        String expectedResult1 = "601683";

        String actualResult2 = c2.trimFile();
        String expectedResult2 = "601720";
        
        String actualResult3 = c3.trimFile();
        String expectedResult3 = "601706";

        String actualResult4 = c4.trimFile();
        String expectedResult4 = "601724";

        String actualResult5 = c5.trimFile();
        String expectedResult5 = "601701";


        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
        assertEquals(expectedResult4, actualResult4);
        assertEquals(expectedResult5, actualResult5);

    }

    
}
