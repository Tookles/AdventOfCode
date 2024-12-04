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
        List<String> myList = FileManager.ReadFile("src/main/java/org/example/inputs/wordsearch");
        int rows = myList.getFirst().length(); // assumes all rows are the same length
        int cols = myList.size();;
        char[][] myGrid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String rowStr = myList.get(i);
            for (int j = 0; j < cols; j++) {
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

//int[][] dir = { {-1, 1}, {1, -1}, {-1, 1}, {1, 1} };
//StringBuilder charHolder = new StringBuilder();
//                    for (int[] d : dir) {
//        charHolder.append(grid[d[0]][d[1]]);
//                    }


