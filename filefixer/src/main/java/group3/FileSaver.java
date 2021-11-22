package group3;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class FileSaver {

    private Collection<File> newFileNames;
    private Collection<File> originalFileNames;

    public FileSaver() {
        newFileNames = new ArrayList<>();
        originalFileNames = new ArrayList<>();

    }

    public void saveFiles(Collection<File> originalFileNames, Collection<File> renamedFiles, String location) {
   
        if (!renamedFiles.isEmpty()) {
            setOrginalFileNames(originalFileNames);

            File dir = new File(location + File.separator + "renamedFiles");

            if (dir.mkdir() || dir.isDirectory()) {
                Iterator<File> originalFiles = originalFileNames.iterator();
                Iterator<File> newFilesNames = renamedFiles.iterator();

                while (originalFiles.hasNext() && newFilesNames.hasNext()) {
                    File origFile = originalFiles.next();
                    File newFile = newFilesNames.next();

                    try {
                        Files.copy(origFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("'renamedFiles'folder created.");
            } else {
                System.out.println("Oops, Something went wrong! Unable to create 'renamedFiles'");
            }
        } 
        else{
           
                System.out.println("No Renamed Files");
         
        }
    }

    public void getMissingSubmissions( Collection<String> missingSubmissions, String location){
        if(!missingSubmissions.isEmpty()) {
            File dir = new File(location +File.separator + "missingSubmission.txt");
            Iterator<String> missingNames = missingSubmissions.iterator();
            String names = "";
            try {
                if (!dir.exists()) {
                    if (dir.createNewFile()) {
                        System.out.println("'MissingSubmission'file created.");
                    }
                }
                BufferedWriter out = new BufferedWriter(new FileWriter(dir));
                while (missingNames.hasNext()) {
                    names += missingNames.next() + "\n";
                }
                out.write(names);
                out.close();
            } catch (IOException e) {
                System.out.println("Oops, Something went wrong! Unable to create 'MissingSubmissions'");
                e.printStackTrace();
            }

        }
        else{
            System.out.println("No Missing Submissions");
        }
    }

    public Collection<File> getOrginalFileNames() {
        return this.originalFileNames;
    }

    public void setOrginalFileNames(Collection<File> originalFileNames) {
        this.originalFileNames = originalFileNames;
    }

    public Collection<File> getNewFileNames() {
        return this.newFileNames;
    }

}
