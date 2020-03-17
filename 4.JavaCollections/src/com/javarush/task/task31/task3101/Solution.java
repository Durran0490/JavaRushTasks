package com.javarush.task.task31.task3101;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.ArrayList;
/* 
Проход по дереву файлов
*/
public class Solution {
    public static ArrayList<File> fileList = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File newFile = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        if (FileUtils.isExist(resultFileAbsolutePath)) {
            FileUtils.renameFile(resultFileAbsolutePath, newFile);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
        writer.close();

        MyFileVisitor fV = new MyFileVisitor();
        Files.walkFileTree(Paths.get(path.getAbsolutePath()), fV);
//        ArrayList<File> fileList = fV.fileList;
        fileList.sort(Comparator.comparing(File::getName));

//        System.out.println(fileList.toString());

        writer = new BufferedWriter(new FileWriter(newFile, true));
        BufferedReader reader;

        for (File file : fileList) {
            if (!(file.getName().equals(newFile.getName())) ) {
                reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null && file.length() <= 50) {
                    writer.write(line);
                    System.out.println(file.getName());
                }
                writer.write("\n");
                reader.close();
            }
        }
        writer.flush();
        writer.close();
    }
}
