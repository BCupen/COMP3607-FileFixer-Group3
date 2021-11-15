package group3;

import java.io.File;
import java.util.regex.*;

public class matchType {
    private Pattern pattern1 = Pattern.compile("^.*_[0-9]+_assignsubmission_file_.*$");
    private Pattern pattern2 = Pattern
            .compile("^[0-9]+-[0-9]+_[a-zA-Z]+_[a-zA-Z]+-[a-zA-Z]+_[0-9]+_[a-zA-Z]+\\d_[0-9]+\\.[a-zA-Z]+$");
    private File file;
    private Pattern ID = Pattern.compile("81\\d\\d\\d\\d\\d\\d");
    private Pattern threeNamewithUnderscores = Pattern.compile("[a-zA-Z]+_[a-zA-Z]+_[a-zA-Z]+");
    private Pattern NamewithWhiteSpace = Pattern.compile("[a-zA-Z]+\\s+[a-zA-Z]+");
    private Pattern threeNameswithSpaces = Pattern.compile("[a-zA-Z]+\\s[a-zA-Z]+\\s[a-zA-Z]+");
    private Pattern twoNamewithUnderscores = Pattern.compile("[a-zA-Z]+_[a-zA-Z]+");
    private Pattern nameNoSpaces=Pattern.compile("[A-Z][a-zA-Z]+[A-Z]+[a-zA-Z]+");
    private String name = null;

    public matchType(File file) {
        this.file = file;
    }

    public String getFileType() {
        Matcher m = pattern1.matcher(file.getName());
        Matcher k = pattern2.matcher(file.getName());
        if (m.matches()) {
            return "Type1";
        } else if (k.matches()) {
            return "Type2";
        } else {
            return "Type3";
        }
    }

    public String getSubstring() {
        Matcher matcher1 = ID.matcher(file.getName());
        Matcher matcher2 = threeNamewithUnderscores.matcher(file.getName());
        Matcher matcher3 = NamewithWhiteSpace.matcher(file.getName());
        Matcher matcher4 = threeNameswithSpaces.matcher(file.getName());
        Matcher matcher5 = twoNamewithUnderscores.matcher(file.getName());
        Matcher matcher6 = nameNoSpaces.matcher(file.getName());
        if (matcher1.find()) {
            return matcher1.group();
        } else if (matcher2.find()) {
            return matcher2.group().replaceAll("_", " ");
        } else if (matcher3.find()) {

            name = matcher3.group().substring(0, 1).toUpperCase()
                    + matcher3.group().substring(1, (matcher3.group().indexOf(" "))).toLowerCase() + " "
                    + matcher3.group().substring(matcher3.group().indexOf(" ") + 1, matcher3.group().indexOf(" ") + 2)
                            .toUpperCase()
                    + matcher3.group().substring((matcher3.group().indexOf(" ")) + 2, matcher3.group().length())
                            .toLowerCase();

            return name;
        } else if (matcher4.find()) {
            return matcher4.group();
        } else if (matcher5.find()) {
            return matcher5.group().replace("_", " ");
        }
        else if(matcher6.find()){
            name = matcher6.group();
            for(int k=1; k<name.length(); k++){
                char letter = name.charAt(k);
                if(Character.isUpperCase(letter)){
                    name = name.substring(0,k)+" "+name.substring(k,name.length());
                    break;
                }
            }
            return name;
        }
        return null;
    }

}
