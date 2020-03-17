package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<Integer, String> fileList = new TreeMap<>();

        for (String fileName = reader.readLine(); !fileName.equals("end"); fileName = reader.readLine()) {
            String[] partNumStr = fileName.split("\\.");
            int partNum = Integer.parseInt(partNumStr[partNumStr.length - 2].split("part")[1]);
            fileList.put(partNum, fileName);
        }
        reader.close();
        //System.out.println(fileList.toString());

        Map.Entry<Integer, String> firstEntry = fileList.firstEntry();
        String commonName = firstEntry.getValue().split(".part")[0] + ".txt";
        //System.out.println(commonName);

        FileOutputStream outputFile = new FileOutputStream(commonName);

        for (Map.Entry<Integer, String> item : fileList.entrySet()) {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(item.getValue()));
            byte[] buff = new byte[input.available()];
            input.read(buff);
            outputFile.write(buff);
            //System.out.println(Arrays.toString(buff));
            input.close();
        }
        outputFile.close();
    }
}
