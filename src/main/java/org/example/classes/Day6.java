package org.example.classes;

import org.example.utils.FileManager;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static void PrintGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(java.util.Arrays.toString(row));
        }
    }

    public static char[][] BuildGraph(){
        List<String> myList = FileManager.ReadFile("src/main/java/org/example/inputs/Day6Test");
        int x = myList.get(0).length();
        int y = myList.size();
        char[][] myGrid = new char[y][x];
        for (int i = 0; i < x; i++) {
            String rowStr = myList.get(i);
            for (int j = 0; j < y; j++) {
                myGrid[i][j] = rowStr.charAt(j);
            }
        }
        return myGrid;
    }

    public static int[] DirToIndexConverter(Direction direction){
        int[][] directions = { {0, -1}, {1, 0}, {0, 1}, {-1, 0} };
        int index;
        switch(direction) {
            case Direction.UP:
                index = 3;
                break;
            case Direction.DOWN:
                index = 1;
                break;
            case Direction.LEFT:
                index = 0;
                break;
            default:
                index = 2;
            };
        return directions[index];
    }

    public static char DirToCharConverter(Direction direction){
        char dir;
        switch(direction) {
            case UP:
                dir = '^';
                break;
            case DOWN:
                dir = 'v';
                break;
            case LEFT:
                dir = '<';
                break;
            default:
                dir = '>';
        };
        return dir;
    }

    public static Direction Move90Degrees(Direction direction){
        Direction dir;
        switch(direction) {
            case UP:
                dir = Direction.RIGHT;
                break;
            case DOWN:
                dir = Direction.LEFT;
                break;
            case LEFT:
                dir = Direction.UP;
                break;
            default:
                dir = Direction.DOWN;
        };
        return dir;
    }


    public static Triplet<Integer, Integer, Direction> FindGuard(){
        char[][] grid = BuildGraph();
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid[1].length ; j++) {
                if ((grid[i][j] == '^') ||
                        (grid[i][j] == 'v') ||
                        (grid[i][j] == '<') ||
                        (grid[i][j] == '>'))
                        {
                            char symbol = grid[i][j];
                            Direction direction = symbol == '^' ? Direction.UP : symbol == '<' ? Direction.LEFT : symbol == '>' ? Direction.RIGHT : Direction.DOWN;
                            return new Triplet<Integer, Integer, Direction>(i, j, direction);

                        }
                }
            }
        return new Triplet<Integer, Integer, Direction>(0, 0, Direction.DOWN);
    }

    public static char[][] MoveGuard() {
        Triplet<Integer, Integer, Direction> guardPos = FindGuard();
        char[][] myGrid = BuildGraph();
        int xLimit = myGrid[1].length;
        int yLimit = myGrid[0].length;
        Direction currentDir = guardPos.getValue2();
        int currentX = guardPos.getValue0();
        int currentY = guardPos.getValue1();
        int obstruction = 0;
        while (true) {
            int[] dirToMove = DirToIndexConverter(currentDir);
            int newX = currentX + dirToMove[0];
            int newY = currentY + dirToMove[1];

            if ((newX == xLimit) || (newY == yLimit) || (newX < 0) || (newY < 0)) {
                break;
            }

            if (myGrid[newX][newY] == '#') {
                currentDir = Move90Degrees(currentDir);
            } else {
                myGrid[newX][newY] = DirToCharConverter(currentDir);
                currentX = newX;
                currentY = newY;
            }
        }

        return myGrid;
    }

    public static int CountGuards() {
        char[][] myGrid = MoveGuard();
        int count = 0;
        for (int i = 0; i < myGrid[0].length; i++) {
            for (int j = 0; j < myGrid[1].length; j++) {
                if ((myGrid[i][j] == '^') ||
                        (myGrid[i][j] == 'v') ||
                        (myGrid[i][j] == '<') ||
                        (myGrid[i][j] == '>')) {
                    count++;
                }
            }
        }
        return count;
        }

}
