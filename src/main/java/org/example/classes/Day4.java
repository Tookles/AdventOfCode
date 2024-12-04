package org.example.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    public static List<String> GetLevels(){
        List<String> returnList = new ArrayList<>();
        try {
            File myObj = new File("src/main/java/org/example/wordsearch");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                returnList.add(nextLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            System.out.println(e);
        }
        return returnList;
    }



    public static long countOccur(String line, String match) {
        Pattern pattern = Pattern.compile(match);
        Matcher matches = pattern.matcher(line);
        return matches.results().count();
    }

    public static int CheckHorizontal(List<String> listOf){
        int total = 0;
        for (int i = 0; i < listOf.size(); i++) {
            total += countOccur(listOf.get(i), "XMAS");
            total += countOccur(listOf.get(i), "SAMX");
        }
        return total;
    }

    public static int CheckVertical(){
        List<String> horList = GetLevels();
        List<String> verList = new ArrayList<>();
        for (int i = 0; i < horList.get(0).length(); i++) {
            String newVertical = "";
            for (int j = 0; j < horList.size(); j++) {
                newVertical += horList.get(j).substring(i, i+1);
            }
            verList.add(newVertical);
        }
        return CheckHorizontal(verList);
    }

    public static int GetDiagonal(){
        List<String> horList = GetLevels();
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
        return CheckHorizontal(dia1);
    }

    public static int GetDiagonal2(){
        List<String> horList = GetLevels();
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
        return CheckHorizontal(dia1);
    }


    public static int TotalCal(){
        int total = 0;
        total += CheckHorizontal(GetLevels());
        total += CheckVertical();
        total += GetDiagonal() + GetDiagonal2();
        return total;
    }

}