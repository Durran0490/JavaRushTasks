package com.javarush.task.task31.task3109;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Path testFilePath = Paths.get("C:\\Users\\Username\\Desktop\\testFile.txt");
        boolean startsWithLalala = testFilePath.endsWith("testFile.txt");
        System.out.println(startsWithLalala);

    }
}
