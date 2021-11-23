package group3;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;

public class FileCollectorTest {
    private FileCollector fileCollector;
    private File path = new File ("filefixer/src/lib/filesToRename/sample1");

    @BeforeEach
    public void setUp(){
        fileCollector = new FileCollector();
    }

    @Test
    public void test(){
        System.out.println(fileCollector.getCSV(path));
    }

}
