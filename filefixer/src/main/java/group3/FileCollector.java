package group3;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FileCollector {
    private Collection<File> files;
    private File csv = null;

    public FileCollector() {
        files = new ArrayList<>();

    }

    public Collection<File> getFiles(File loc) {
        List<File> allfiles = Arrays.asList(loc.listFiles());

        for (File f : allfiles) {
            if (!f.isDirectory() && f.getName().endsWith(".pdf")) // only get the pdf files
                files.add(f);

            if (files.isEmpty()) {
                System.out.println("No PDF files found...Listing Missing Submissions");
            }
        }

        return files;
    }

    public File getCSV(File loc) {
        List<File> allfiles = Arrays.asList(loc.listFiles());

        for (File f : allfiles) {
            if (!f.isDirectory() && f.getName().endsWith(".csv"))
                this.csv = f;
        }
        if (csv == null) {
            System.out.println("No CSV file found...Terminating Program");
            System.exit(0);
        }
        return csv;
    }

}
