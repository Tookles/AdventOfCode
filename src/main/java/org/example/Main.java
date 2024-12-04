package org.example;
import java.io.CharArrayWriter;
import java.util.Arrays;
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
            File myObj = new File("src/main/java/org/example/levels");
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

    public static boolean CheckLevels(List<Integer> listOfLevels, int recursion) {
        if (recursion > 1) {
            return false;
        }

        int first = listOfLevels.getFirst();
        int second = listOfLevels.getLast();
        List<Integer> listofDiff = new ArrayList<Integer>();
        if (first < second) {
            listofDiff.add(-1);
            listofDiff.add(-2);
            listofDiff.add(-3);
        } else {
            listofDiff.add(1);
            listofDiff.add(2);
            listofDiff.add(3);
        }

        for (int i = 0; i < (listOfLevels.size() - 1); i++ ) {
            if ( ! listofDiff.contains( listOfLevels.get(i) - listOfLevels.get(i + 1) )) {
                List<Integer> firstIndex = new ArrayList<>(listOfLevels);
                List<Integer> secondIndex = new ArrayList<>(listOfLevels);
                firstIndex.remove(i);
                secondIndex.remove(i + 1);
                return CheckLevels(firstIndex, recursion + 1) || CheckLevels(secondIndex, recursion + 1);
            }
        }
        return true;
    }


    public static int CheckReviews() {
        int count = 0;
        List<String> myLevels = GetLevels();
        for (int i = 0; i < myLevels.size(); i++) {
            List<Integer> listOfLevels = Arrays.stream(myLevels.get(i).split(" "))
                    .map(Integer :: parseInt).collect(Collectors.toList());
            if (CheckLevels(listOfLevels, 0)) {
                count += 1;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(CheckReviews());
    }
}