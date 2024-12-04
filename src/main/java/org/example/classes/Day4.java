package org.example.classes;

import org.example.utils.FileManager;

import java.util.ArrayList;
import java.util.List;

public class Day4 {

    public static void PrintGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(java.util.Arrays.toString(row));
        }
    }

    public static char[][] BuildGraph(){
        List<String> myList = FileManager.ReadFile("src/main/java/org/example/inputs/wordsearch");
        char[][] myGrid = new char[150][150];
        for (int i = 0; i < 150; i++) {
            if ((i < 5) || (i >= 145)) {
                for (int j = 0; j < 150; j++) {
                    myGrid[i][j] = '.';
                }
            } else {
            String rowStr = myList.get(i - 5);
            for (int j = 0; j < 150; j++) {
                myGrid[i][j] = j < 5 || j >= 145 ? '.' : rowStr.charAt(j - 5);
            }
            }
        }
        return myGrid;
    }

    public static List<String> GetStringArray(char[][] grid, int i, int j){
        List<String> stringArray = new ArrayList<>();
        int[][] directions = {
                {1,0}, {-1, 0} ,
                {0, 1}, {0, -1},
                {1, 1}, {-1, -1},
                {-1, 1}, {1, -1}
        };

        for (int[] direction : directions) {
            StringBuilder charHolder = new StringBuilder();
            for (int step = 1; step <= 3; step++) {
                int x = i + step * direction[0];
                int y = j + step * direction[1];
                charHolder.append(grid[x][y]);
            }
            stringArray.add(charHolder.toString());
        }
        return stringArray;
    }

    public static int SearchX(){
        char[][] grid = BuildGraph();
        int count = 0;
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                if (grid[i][j] == 'X') {
                    List<String> getIndexWords = GetStringArray(grid, i, j);
                    for (String word : getIndexWords) {
                        if (word.equals("MAS")) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
