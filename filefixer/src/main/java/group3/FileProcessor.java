package group3;

import java.io.*;
import java.util.*;

public class FileProcessor {

    private Collection<File> originalFileNames;
    private Collection<File> convention1Files = new ArrayList<>();
    private Collection<File> renamedFiles = new ArrayList<>();
    private ConventionFileProcessor strategy;
    private matchType fileType;

    public Collection<File> getOrginalFileNames() {
        return this.originalFileNames;
    }

    public Collection<File> getRenamedFileNames() {
        return this.renamedFiles;
    }

    public Collection<File> renameFiles(Collection<File> filesToBeRenamed, File csvFile) {
        this.originalFileNames = filesToBeRenamed;

        for (File file : filesToBeRenamed) {
            fileType = new matchType(file);
            if (fileType.getFileType().equals("Convention1")) {
                this.convention1Files.add(file);
                strategy = new Con1ToCon2Processor(file, csvFile);
                file = strategy.renameFile();
                if (file != null) {
                    System.out.println(file.getName());
                    this.renamedFiles.add(file);
                }
            } else if (fileType.getFileType().equals("Convention2")) {
                this.renamedFiles.add(file);
            } else {
                strategy = new NoConventionProcessor(file, csvFile);
                file = strategy.renameFile();
                if (file != null) {
                    System.out.println(file.getName());
                    this.convention1Files.add(file);
                    strategy = new Con1ToCon2Processor(file, csvFile, strategy.getOriginalFileName());
                    file = strategy.renameFile();
                    if (file != null) {
                        System.out.println(file.getName());
                        this.renamedFiles.add(file);
                    }
                }

            }

        }

        return this.convention1Files;
    }

    public Collection<File> getRenamedFiles() {
        return this.renamedFiles;
    }

    public Collection<String> getMissingSubmissions(File csvFile) {
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
                if (!renamedFiles.isEmpty()) {
                    for (File file : renamedFiles) {
                        // System.out.println(file.getName().substring(0, file.getName().indexOf("_")));
                        if (assignment[1].contains(file.getName().substring(0, file.getName().indexOf("_")))) {
                            found = true;
                        }
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
        if (missingSubmissions.isEmpty()) {
            System.out.println("No Missing Submissions");
        }
        return missingSubmissions;

    }

}
