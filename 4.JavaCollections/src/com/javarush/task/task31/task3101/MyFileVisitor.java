package com.javarush.task.task31.task3101;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.ArrayList;
public class MyFileVisitor extends SimpleFileVisitor<Path> {
//    ArrayList<File> fileList = new ArrayList<File>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            Solution.fileList.add(file.toFile());
        }
        return FileVisitResult.CONTINUE;
    }

}