package org.example.classes;

import org.example.utils.FileManager;

import java.util.List;

public class Day4RF {

    public static void PrintGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(java.util.Arrays.toString(row));
        }
    }

    public static char[][] BuildGraph(){
        List<String> myList = FileManager.ReadFile("src/main/java/org/example/inputs/wordsearch");
        int rows = 150;
        int cols = 150;
        char[][] myGrid = new char[rows][cols];

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


    public static int SearchX(){
        char[][] grid = BuildGraph();
        int count = 0;
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {

                if (grid[i][j] == 'X') {
                    String indices = String.valueOf(grid[i+1][j]) + String.valueOf(grid[i+2][j])
                            + String.valueOf(grid[i+3][j]);

                    if (indices.equals("MAS"))  {
                        count++;
                    }

                    String indices2 = String.valueOf(grid[i-1][j]) + String.valueOf(grid[i-2][j])
                            + String.valueOf(grid[i-3][j]);

                    if (indices2.equals("MAS"))  {
                        count++;
                    }

                    String indices3 = String.valueOf(grid[i][j+1]) + String.valueOf(grid[i][j+2])
                            + String.valueOf(grid[i][j+3]);

                    if (indices3.equals("MAS"))  {
                        count++;
                    }
                    String indices4 = String.valueOf(grid[i][j-1]) + String.valueOf(grid[i][j-2])
                            + String.valueOf(grid[i][j-3]);

                    if (indices4.equals("MAS"))  {
                        count++;
                    }

                    String indices5 = String.valueOf(grid[i+1][j+1]) + String.valueOf(grid[i+2][j+2])
                            + String.valueOf(grid[i+3][j+3]);

                    if (indices5.equals("MAS"))  {
                        count++;
                    }

                    String indices6 = String.valueOf(grid[i-1][j-1]) + String.valueOf(grid[i-2][j-2])
                            + String.valueOf(grid[i-3][j-3]);

                    if (indices6.equals("MAS"))  {
                        count++;
                    }

                    String indices7 = String.valueOf(grid[i-1][j+1]) + String.valueOf(grid[i-2][j+2])
                            + String.valueOf(grid[i-3][j+3]);

                    if (indices7.equals("MAS"))  {
                        count++;
                    }

                    String indices8 = String.valueOf(grid[i+1][j-1]) + String.valueOf(grid[i+2][j-2])
                            + String.valueOf(grid[i+3][j-3]);

                    if (indices8.equals("MAS"))  {
                        count++;
                    }


                }
            }
        }
        return count;
}
}
