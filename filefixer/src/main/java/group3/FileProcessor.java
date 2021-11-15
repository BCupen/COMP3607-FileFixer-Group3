package group3;

import java.io.*;
import java.util.*;

public class FileProcessor {

    private Collection<File> originalFileNames;
    Collection<File> renamedFiles = new ArrayList<>();
    private Convention2FileProcessor strategy;
    private matchType fileType;
    private File csvFile;

    public File getCSVFile() {
        File path2 = new File("./filefixer/src/lib/filesToRename/sample5");
        List<File> allfiles2 = Arrays.asList(path2.listFiles());

        for (File f : allfiles2) {
            if (!f.isDirectory() && f.getName().endsWith(".csv")) { // only get the csv file
                csvFile = f;
                break;
            }
        }
        return csvFile;

    }

    public Collection<File> renameFiles(Collection<File> filesToBeRenamed) {
        this.originalFileNames = filesToBeRenamed;

        for (File file : filesToBeRenamed) {
            fileType = new matchType(file);
            if (fileType.getFileType().equals("Type1")) {
                strategy = new Type1Processor(file, this.getCSVFile());
            } else if (fileType.getFileType().equals("Type2")) {
                strategy = new Type2Processor(file, this.getCSVFile());
            } else {
                strategy = new Type3Processor(file, this.getCSVFile());
            }
            file = strategy.renameFile();
            if (file != null) {
                System.out.println(file.getName());
                renamedFiles.add(file);
            }
        }

        return renamedFiles;
    }

    public Collection<File> getOrginalFileNames() {
        return this.originalFileNames;
    }

    public Collection<File> getRenamedFileNames() {
        return this.renamedFiles;
    }

    public Collection<String> getMissingSubmissions() {
        Collection<String> missingSubmissions = new ArrayList<>();
        String line = "";
        String splitBy = ",";
        String fullName;
        String id;
        String studentInfo;
        boolean found = false;

        try {
            System.out.println("Missing Submissions: ");
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] assignment = line.split(splitBy);
                for (File file : renamedFiles) {
                   // System.out.println(file.getName().substring(0, file.getName().indexOf("_")));
                    if (assignment[1].contains(file.getName().substring(0, file.getName().indexOf("_")))) {
                        found = true;
                    }
                }
                if (found == false) {
                    fullName = assignment[1];
                    id = assignment[2];
                    studentInfo = fullName + "_" + id;
                    System.out.println(studentInfo);
                    missingSubmissions.add(studentInfo);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(missingSubmissions.isEmpty()){
            System.out.println("No Missing Submissions");
        }
        return missingSubmissions;

    }

}
