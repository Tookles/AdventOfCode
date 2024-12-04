package org.example.utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    public static List<String> ReadFile(String filePath){
        List<String> fileAsStringList = new ArrayList<>();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                fileAsStringList.add(nextLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return fileAsStringList;
    }
}
