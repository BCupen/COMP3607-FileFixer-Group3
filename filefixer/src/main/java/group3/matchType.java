package group3;

import java.io.File;
import java.util.regex.*;

public class matchType {
    private File file;
    private String name = null;
    private Pattern pattern1 = Pattern.compile("^.*_[0-9]+_assignsubmission_file_.*$");
    private Pattern pattern2 = Pattern.compile("^[0-9]+-[0-9]+_([a-zA-Z]+((_)?(OΓÇÖ)?(-)?[a-zA-Z]+)+)_[0-9]+_.*$");
    private Pattern ID = Pattern.compile("81\\d\\d\\d\\d\\d\\d");
    private Pattern threeNamewithUnderscores = Pattern.compile("[a-zA-Z]+_[a-zA-Z]+_[a-zA-Z]+");
    private Pattern nameWithWhiteSpace = Pattern.compile("[a-zA-Z]+\\s+[a-zA-Z]+");
    private Pattern threeNamesWithSpaces = Pattern.compile("[a-zA-Z]+\\s[a-zA-Z]+\\s[a-zA-Z]+");
    private Pattern twoNameWithUnderscores = Pattern.compile("[a-zA-Z]+_[a-zA-Z]+");
    private Pattern nameNoSpaces = Pattern.compile("[A-Z][a-zA-Z]+[A-Z]+[a-zA-Z]+");
    private Pattern Identifier = Pattern.compile("_601\\d\\d\\d_");

    public matchType(File file) {
        this.file = file;
    }

    public String getFileType() {
        Matcher m = pattern1.matcher(file.getName());
        Matcher k = pattern2.matcher(file.getName());
        if (m.matches()) {
            return "Convention2";
        } else if (k.matches()) {
            return "Convention1";
        } else {
            return "NoConvention";
        }
    }

    public String getSubstring() {
        Matcher matcher1 = ID.matcher(file.getName());
        Matcher matcher2 = threeNamewithUnderscores.matcher(file.getName());
        Matcher matcher3 = nameWithWhiteSpace.matcher(file.getName());
        Matcher matcher4 = threeNamesWithSpaces.matcher(file.getName());
        Matcher matcher5 = twoNameWithUnderscores.matcher(file.getName());
        Matcher matcher6 = nameNoSpaces.matcher(file.getName());
        Matcher match = Identifier.matcher(file.getName());
        if (match.find()) {
            String[] id = match.group().split("_");
            return id[1];
        } else if (matcher1.find()) {
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
        } else if (matcher6.find()) {
            name = matcher6.group();
            for (int k = 1; k < name.length(); k++) {
                char letter = name.charAt(k);
                if (Character.isUpperCase(letter)) {
                    name = name.substring(0, k) + " " + name.substring(k, name.length());
                    break;
                }
            }
            return name;
        }

        System.out.println("No identifiers: " + file.getName());
        return null;
    }

}
