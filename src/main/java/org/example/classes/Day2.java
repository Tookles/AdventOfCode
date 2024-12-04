package org.example.classes;

import org.example.utils.FileManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

    public static boolean CheckLevels(List<Integer> listOfLevels, int recursion) {
        if (recursion > 1) {
            return false;
        }

        List<Integer> listofDiff = new ArrayList<Integer>();
        if (listOfLevels.getFirst() < listOfLevels.getLast()) {
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
        List<String> myLevels = FileManager.ReadFile("src/main/java/org/example/levels");
        for (int i = 0; i < myLevels.size(); i++) {
            List<Integer> listOfLevels = Arrays.stream(myLevels.get(i).split(" "))
                    .map(Integer :: parseInt).collect(Collectors.toList());
            if (CheckLevels(listOfLevels, 0)) {
                count += 1;
            }
        }
        return count;
    }


}
