package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();
    public static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String file1 = null;
        String file2 = null;

        try {
            file1 = read.readLine();
            file2 = read.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file1 != null && file2 != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
                while (br.ready()) {
                    allLines.add(br.readLine());
                }
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
                while (br.ready()) {
                    forRemoveLines.add(br.readLine());
                }
            }
        }
        Solution solution = new Solution();

        try {
            solution.joinData();
        }catch (CorruptedDataException e){
            e.printStackTrace();
        }

    }

    public void joinData() throws CorruptedDataException {
        boolean clear = true;
        for (String s: forRemoveLines) {
            for(String s1:allLines)
            if(s.equals(s1)){
                clear = false;
                break;
            }else {
                clear = true;
            }
            if(clear){
                break;
            }
        }

        if(clear){
            allLines.clear();
            throw new CorruptedDataException();
        }else{
            allLines.removeAll(forRemoveLines);
        }

    }
}
