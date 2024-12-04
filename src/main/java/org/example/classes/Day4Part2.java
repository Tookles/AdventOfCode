package org.example.classes;
import org.example.utils.FileManager;
import java.util.Arrays;
import java.util.List;

public class Day4Part2 {

    public static void PrintGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(java.util.Arrays.toString(row));
        }
    }

    public static char[][] BuildGraph(){
        int rows = 140;
        int cols = 140;
        char[][] myGrid = new char[rows][cols];
        List<String> myList = FileManager.ReadFile("src/main/java/org/example/inputs/wordsearch");
        for (int i = 0; i < myList.size(); i++) {
            String rowStr = myList.get(i);
            for (int j = 0; j < myList.size(); j++) {
                myGrid[i][j] = rowStr.charAt(j);
            }
        }
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
