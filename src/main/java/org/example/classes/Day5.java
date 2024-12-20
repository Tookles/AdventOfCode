package org.example.classes;
import java.util.*;
import org.example.utils.FileManager;

public class Day5 {

    public static HashMap<Integer, List<Integer>> ParsePageOrders(){
        List<String> printingOrders = FileManager.ReadFile("src/main/java/org/example/inputs/Day5PageOrders");
        HashMap<Integer, List<Integer>> orderDict = new HashMap<>();
        for (String order : printingOrders) {
            List<String> list = new ArrayList<>(Arrays.asList(order.split("\\|")));
            Integer key = Integer.parseInt(list.get(0));
            Integer value = Integer.parseInt(list.get(1));
            if (orderDict.containsKey(key)) {
                orderDict.get(key).add(value);
            } else {
                List<Integer> listToAdd = new ArrayList<>();
                orderDict.put(key, listToAdd);
                orderDict.get(key).add(value);
            }
        }
        return orderDict;
    }

    public static List<List<Integer>>  ParsePrintingJobs(){
        List<String> printingJobs = FileManager.ReadFile("src/main/java/org/example/inputs/Day5PrintingJobs");
        List<List<Integer>> printingJobArray = new ArrayList<>();
        for (String job : printingJobs) {
            List<String> list = new ArrayList<>(Arrays.asList(job.split(",")));
            List<Integer> listInt = list.stream().map(p -> Integer.parseInt(p)).toList();
            printingJobArray.add(listInt);
        }
        return printingJobArray;
    }

    public static List<Integer> FixList(int numb, List<Integer> arrayToFix, int iindex, int inumber){
        int indexOfNumb = arrayToFix.indexOf(numb);
        List<Integer> newArray = new ArrayList<>(arrayToFix);
        newArray.remove(iindex);
        newArray.add(indexOfNumb, inumber);
        return newArray;
    }
// several checks

    public static boolean IsPrintingJobValid(List<Integer> job) {
        HashMap<Integer, List<Integer>> orderDict = ParsePageOrders();
        List<Integer> numbersChecked = new ArrayList<>();
        for (int i = 0; i < job.size(); i++) {
            if (orderDict.containsKey(job.get(i))) {
                List<Integer> numbersBefore = orderDict.get(job.get(i));
                for (Integer numb : numbersBefore) {
                    if (numbersChecked.contains(numb)) {
                        return false;
                    }
                }
            }
            numbersChecked.add(job.get(i));
        }
        return true;
    }


    public static List<Integer> SortJob(List<Integer> job) {
        HashMap<Integer, List<Integer>> orderDict = ParsePageOrders();
        List<Integer> numbersChecked = new ArrayList<>();
        for (int i = 0; i < job.size(); i++) {
            if (orderDict.containsKey(job.get(i))) {
                List<Integer> numbersBefore = orderDict.get(job.get(i));
                for (Integer numb : numbersBefore) {
                    if (numbersChecked.contains(numb)) {
                        List<Integer> fixed = FixList(numb, job, i, job.get(i));
                        return fixed;
                    }
                }
            }
            numbersChecked.add(job.get(i));
        }
        return null;
    }

    public static int CountPrintingJobs() {
        int count = 0;
        List<List<Integer>> printingJobs = ParsePrintingJobs();
        for (List<Integer> job : printingJobs) {
            if (!IsPrintingJobValid(job)) {
                List<Integer> swap = SortJob(job);
                if (swap == null) {
                    continue;
                }
                while (!IsPrintingJobValid(swap)) {
                    swap = SortJob(swap);
                }
                double listSize = swap.size();
                count += swap.get((int)Math.floor(listSize / 2));
            }
        }
        return count;
    }


}
