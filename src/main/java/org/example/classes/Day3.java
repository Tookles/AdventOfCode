package org.example.classes;

import org.example.utils.FileManager;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    public static String GetMuls(){
        List<String> returnList = FileManager.ReadFile("src/main/java/org/example/mulinput");
        return String.join("", returnList);
    }

    public static int SplitRegexParse(String lineToFilter) {
        int total = 0;
        Pattern pattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)");
        Matcher match = pattern.matcher(lineToFilter);
        while (match.find()) {
            Pattern pattern2 = Pattern.compile("\\d+");
            String mulMatch = match.group();
            Matcher match2 = pattern2.matcher(mulMatch);
            match2.find();
            Integer num1 = Integer.parseInt(match2.group());
            match2.find();
            Integer num2 = Integer.parseInt(match2.group());
            total += num1 * num2;
        }
        return total;
    }

    public static int RegexParse(){
        int total = 0;
        String stringToParse = GetMuls();
        String[] dontSplits = stringToParse.split("don't\\(\\)");
        total += SplitRegexParse(dontSplits[0]); // calculate the first one
        for (int i = 1; i < dontSplits.length; i++) {
            if (dontSplits[i].contains("do()")) {
                String doSplits = dontSplits[i].substring(dontSplits[i].indexOf("do()") +1);
                total += SplitRegexParse(doSplits);
            }
        }
        return total;

    }

}
