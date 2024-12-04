package org.example.classes;
import org.example.utils.FileManager;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    public static long CountOccur(String line, String match) {
        Pattern pattern = Pattern.compile(match);
        Matcher matches = pattern.matcher(line);
        return matches.results().count();
    }

    public static int AssessString(List<String> listOf){
        int total = 0;
        for (int i = 0; i < listOf.size(); i++) {
            total += CountOccur(listOf.get(i), "XMAS");
            total += CountOccur(listOf.get(i), "SAMX");
        }
        return total;
    }

    public static int GetVertical(){
        List<String> horList = FileManager.ReadFile("src/main/java/org/example/inputs/wordsearch");
        List<String> verList = new ArrayList<>();
        for (int i = 0; i < horList.get(0).length(); i++) {
            String newVertical = "";
            for (int j = 0; j < horList.size(); j++) {
                newVertical += horList.get(j).substring(i, i+1);
            }
            verList.add(newVertical);
        }
        return AssessString(verList);
    }

    public static int GetDiagonalL2R(){
        List<String> horList = FileManager.ReadFile("src/main/java/org/example/inputs/wordsearch");
        List<String> dia1 = new ArrayList<>();
        for (int i = 0; i < horList.get(0).length(); i++) {
            String newVertical = "";
            int j = 0;
            int x = i;
            while ((x < horList.get(0).length()) && (j < horList.size()))
            {
                newVertical += horList.get(x).substring(j, j+1);
                j++;
                x++;
            }
            dia1.add(newVertical);
        }

        for (int i = 1; i < horList.get(0).length(); i++) {
            String newVertical = "";
            int j = i;
            int x = 0;
            while ((x < horList.get(0).length()) && (j < horList.size()))
            {
                newVertical += horList.get(x).substring(j, j+1);
                j++;
                x++;
            }
            dia1.add(newVertical);
        }
        return AssessString(dia1);
    }

    public static int GetDiagonalR2L(){
        List<String> horList = FileManager.ReadFile("src/main/java/org/example/inputs/wordsearch");
        List<String> dia1 = new ArrayList<>();
        for (int i = horList.get(0).length() -1; i >= 0; i--) {
            String newVertical = "";
            int j = i;
            int x = 0;
            while ((x < horList.get(0).length()) && (j >= 0))
            {
                newVertical += horList.get(x).substring(j, j+1);
                j--;
                x++;
            }
            dia1.add(newVertical);
        }

        for (int i = 1; i < horList.get(0).length(); i++) {
            String newVertical = "";
            int j = horList.size() -1;
            int x = i;
            while ((x < horList.get(0).length()) && (j >= 0))
            {
                newVertical += horList.get(x).substring(j, j+1);
                j--;
                x++;
            }
            dia1.add(newVertical);
        }
        return AssessString(dia1);
    }


    public static int TotalCal(){
        int total = 0;
        total += AssessString(FileManager.ReadFile("src/main/java/org/example/inputs/wordsearch"));
        total += GetVertical();
        total += GetDiagonalL2R() + GetDiagonalR2L();
        return total;
    }

}