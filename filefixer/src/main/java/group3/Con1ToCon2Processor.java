package group3;

import java.io.*;

public class Con1ToCon2Processor implements ConventionFileProcessor {

    private File csvFile;
    private File file;
    private String fileInfo;
    private matchType findMatch;
    private String match;
    private String ID;
    private String[] fileName1;
    private String fileName;
    private String OriginalFileName = null;

    public Con1ToCon2Processor(File file, File csvFile, String OriginalFileName) {
        super();
        this.csvFile = csvFile;
        this.file = file;
        this.OriginalFileName = OriginalFileName;
    }

    public Con1ToCon2Processor(File file, File csvFile) {
        super();
        this.csvFile = csvFile;
        this.file = file;
    }

    public String getOriginalFileName() {
        if (this.OriginalFileName == null) {
            fileName1 = file.getName().split("_");
            if (fileName1[fileName1.length - 2].contains("601")) {
                OriginalFileName = fileName1[fileName1.length - 1];
            } else {
                OriginalFileName = fileName1[fileName1.length - 2] + "_" + fileName1[fileName1.length - 1];
            }
        }
        return this.OriginalFileName;
    }

    @Override
    public String trimFile() {
        findMatch = new matchType(this.file);
        match = findMatch.getSubstring();
        return match;
    }

    @Override
    public String getFileInfo() {

        String line = "";
        String splitBy = ",";
        String fullIdentifier = "";
        String fullName = "";
        try {

            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            ID = this.trimFile();
            while ((line = br.readLine()) != null) {
                String[] assignment = line.split(splitBy);
                if (ID != null) {
                    if (assignment[0].contains(ID)) {
                        fullIdentifier = assignment[0];
                        fullName = assignment[1];
                        String[] Identifier = fullIdentifier.split(" ");
                        fileInfo = fullName + "_" + Identifier[1] + "_assignsubmission_file_";
                        br.close();

                        return fileInfo;
                    }
                } else {
                    br.close();
                    return null;
                }

            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public String stitchFile() {
        if (this.getFileInfo() != null) {
            fileName = this.getFileInfo() + this.getOriginalFileName();
            return fileName;
        } else {
            return null;
        }

    }

    @Override
    public File renameFile() {
        if (this.stitchFile() != null) {
            fileName = this.stitchFile();
            file = new File(file.getParent() + File.separator + "renamedFiles" + File.separator + fileName);

            return file;
        } else {
            System.out.println(file.getName() + " : " + "Invalid Identifier : Could Not Rename File to Convention 2 :"+this.trimFile());
            return null;
        }
    }

}
