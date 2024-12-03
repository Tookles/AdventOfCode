package org.example.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static List<String> ReadFile(){
        List<String> returnList = new ArrayList<>();
        try {
            File myObj = new File("src/main/java/org/example/lists");
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

    public static List<Integer> GetLists(int oddOrEven){
        List<String> theLists = ReadFile();
        List<Integer> returnList = new ArrayList<>();
        for (String line : theLists ){
            String hold = line.split("   ")[oddOrEven];
            returnList.add(Integer.parseInt(hold));
        }
        return returnList;
    }


    public static int CompareLists()
    {
        List<Integer> list1 = GetLists(0);
        List<Integer> list2 = GetLists(1);
        Collections.sort(list1);
        Collections.sort(list2);
        Integer count = 0;

        for (int i = 0; i < list1.size(); i++){
            Integer diff = list1.get(i) - list2.get(i);
            if (diff < 0) {
                diff = diff * -1;
            }
            count += diff;
        }

        return count;
    }

    public static int SortDifference()
    {
        List<Integer> list1 = GetLists(0);
        List<Integer> list2 = GetLists(1);
        Integer count = 0;

        for (int i = 0; i < list1.size(); i++){
            Integer indexValue = list1.get(i);
            long occur = list2.stream().filter(p -> p.equals(indexValue)).count();
            count += indexValue * (int) occur;
        }

        return count;
    }

}
