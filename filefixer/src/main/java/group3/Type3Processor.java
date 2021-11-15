package group3;

import java.io.*;

public class Type3Processor implements Convention2FileProcessor{
    
    private File csvFile;
    private File file;
    private String fileInfo;
    private matchType findMatch;
    private String Identifier;
    private String fileName;

    public Type3Processor(File file, File csvFile) {
        super();
        this.csvFile = csvFile;
        this.file = file;
    }


    public String trimFile() {
        findMatch = new matchType(this.file);
        Identifier = findMatch.getSubstring();
        return Identifier;
    }

    @Override
    public String getFileInfo() {

        String line = "";
        String splitBy = ",";
        String fullIdentifier = "";
        String fullName = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] assignment = line.split(splitBy);
                if(this.trimFile()!=null){
                if ((assignment[2].contains(this.trimFile()))||(assignment[1].contains(this.trimFile()))){
                    fullIdentifier = assignment[0];
                    fullName = assignment[1];
                    String[] Identifier = fullIdentifier.split(" ");
                    fileInfo = fullName+"_"+Identifier[1]+"_assignsubmission_file_";
                    br.close();
                    return fileInfo;
                }
                }
                else{
                    br.close();
                    return null;
                }
            }
            br.close();
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public String stitchFile() {
        if(this.getFileInfo()!=null){
        fileName = this.getFileInfo()+file.getName();
        return fileName;
        }
        return null;

    }

    @Override
    public File renameFile() {
        if(this.stitchFile()!=null){
        fileName=this.stitchFile();
        file = new File("./filefixer/src/lib/filesToRename/renamedFiles/"+fileName);
        return file;
        }
        else{
            System.out.println("Invalid Identifier : Cannot Rename File");
            return null;
        }
    }

    
    
}
