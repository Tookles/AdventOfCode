package org.example;
import java.io.CharArrayWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

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

    public static char[][] buildGraph(){
        int rows = 140;
        int cols = 140;
        char[][] myGrid = new char[rows][cols];
        List<String> myList = GetLevels();
        for (int i = 0; i < myList.size(); i++) {
            String tempStr = myList.get(i);
            for (int j = 0; j < myList.size(); j++) {
                myGrid[i][j] = tempStr.charAt(j);
            }
        }

//        for (char[] row : myGrid) {
//            System.out.println(java.util.Arrays.toString(row));
//        }

        return myGrid;
    }

    public static int SearchA(){
        char[][] grid = buildGraph();
        int count = 0;
        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 140; j++) {

                if (grid[i][j] == 'A') {
                    if ((i == 0) || (j == 0) || (i==139) || (j==139) ) {
                        continue;
                    }


                    // MS
                    // Ms
                    if ((grid[i-1][j-1] == 'M')
                            && (grid[i+1][j-1] == 'M')
                            && (grid[i-1][j+1] == 'S')
                            && (grid[i+1][j+1] == 'S')) {
                        count ++;
                    }
                    // SM
                    // SM
                    else if ((grid[i-1][j-1] == 'S')
                            && (grid[i+1][j-1] == 'S')
                            && (grid[i-1][j+1] == 'M')
                            && (grid[i+1][j+1] == 'M'))  {
                        count ++;
                    }
                    // SS
                    // MM
                    else if ((grid[i-1][j-1] == 'S')
                            && (grid[i+1][j-1] == 'M')
                            && (grid[i-1][j+1] == 'S')
                            && (grid[i+1][j+1] == 'M'))  {
                        count ++;
                    }
                    else if ((grid[i-1][j-1] == 'M')
                            && (grid[i+1][j-1] == 'S')
                            && (grid[i-1][j+1] == 'M')
                            && (grid[i+1][j+1] == 'S'))  {
                        count ++;
                    } else {
                        System.out.println("This A is at " + i + " and " + j);
                    }

                }
                }
            }
        return count;
    }

    /*
    Find each A
    Then check if the positions around it are M,S,M,S
    Count this


     */


    public static void main(String[] args) {
        System.out.println(SearchA());
    }
}