package org.example.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static String GetMuls(){
        List<String> returnList = new ArrayList<>();
        try {
            File myObj = new File("src/main/java/org/example/mulinput");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                returnList.add(nextLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            System.out.println(e);
        }
        String returnString = String.join("", returnList);
        return returnString;
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
