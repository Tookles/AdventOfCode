package org.example.classes;

import org.example.utils.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    public static List<Integer> GetLists(int oddOrEven){
        List<String> theLists = FileManager.ReadFile("src/main/java/org/example/lists");
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
