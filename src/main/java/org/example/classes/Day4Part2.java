package org.example.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day4Part2 {
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
            System.out.println(e);
        }
        return returnList;
    }

    public static char[][] BuildGraph(){
        int rows = 140;
        int cols = 140;
        char[][] myGrid = new char[rows][cols];
        List<String> myList = GetLevels();
        for (int i = 0; i < myList.size(); i++) {
            String rowStr = myList.get(i);
            for (int j = 0; j < myList.size(); j++) {
                myGrid[i][j] = rowStr.charAt(j);
            }
        }

//        for (char[] row : myGrid) {
//            System.out.println(java.util.Arrays.toString(row));
//        }

        return myGrid;
    }

    public static int SearchA(){
        char[][] grid = BuildGraph();
        int count = 0;
        String[] indexOptions = { "MMSS", "SSMM", "SMSM", "MSMS"};
        for (int i = 1; i < 139; i++) {
            for (int j = 1; j < 139; j++) {

                if (grid[i][j] == 'A') {

                    String indices = String.valueOf(grid[i-1][j-1]) + String.valueOf(grid[i+1][j-1])
                            + String.valueOf(grid[i-1][j+1]) + String.valueOf(grid[i+1][j+1]);

                    if (Arrays.asList(indexOptions).contains(indices)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}