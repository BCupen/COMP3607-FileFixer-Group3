package group3;


import java.io.*;
import java.util.*;

public class NoConventionProcessor implements ConventionFileProcessor {
    private File csvFile;
    private File file;
    private String fileInfo;
    private String originalFileName;
    private MatchType findMatch;
    private String match;
    private String ID;
    private String fileName;
    private long[] randomNumbers = new long[2];


    public NoConventionProcessor(File file, File csvFile){ 
        super();
        this.file=file;
        this.originalFileName = file.getName();
        this.csvFile=csvFile;
    }

    @Override
    public File renameFile() {
        if(this.stitchFile()!=null){
            fileName=this.stitchFile();
            file = new File(file.getParent()+File.separator+fileName);
            return file;
            }
            else{
                System.out.println(file.getName()+" : "+ "Invalid Identifier : Could Not Rename File to Convention 1");
                return null;
            }
    }

    @Override
    public String trimFile() {
        findMatch = new MatchType(this.file);
        match = findMatch.getSubstring();
        return match;
    }

    public String getOriginalFileName(){
        return this.originalFileName;
    }

    public long[] generateRandomNumbers(){
       Random rnd1 = new Random();
       long number = (long)(Math.random() * Math.pow(10, 10));
       if(String.valueOf(number).length()!= 10){
           while(String.valueOf(number).length()!= 10){
            number = (long)(Math.random() * Math.pow(10, 10));
           }
       }
       randomNumbers[0]= number;
       randomNumbers[1]= rnd1.nextInt(999999);
       return randomNumbers;
    }



    @Override
    public String stitchFile() {
        if(this.getFileInfo()!=null){
            randomNumbers=this.generateRandomNumbers();
            fileName = randomNumbers[0]+"-"+randomNumbers[1]+"_"+this.getFileInfo()+"_"+this.getOriginalFileName();
            return fileName;
            }
            else{
                return null;
            }
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
                ID=this.trimFile();
                if(ID!=null){
                if ((assignment[2].contains(ID))||(assignment[1].contains(ID))||(assignment[0].contains(ID))){
                    fullIdentifier = assignment[0];
                    fullName = assignment[1];
                    String[] fN = fullName.split(" ");
                    fullName="";
                    for(int k=0; k<fN.length; k++){
                        fullName += fN[k] + "_";
                    }
                    String[] Identifier = fullIdentifier.split(" ");
                    fileInfo = fullName+Identifier[1];
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

   
    
}
