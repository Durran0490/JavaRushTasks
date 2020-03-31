package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {

        if (!file.isDirectory()) {
                return file.getName().toLowerCase().endsWith(".html") || file.getName().toLowerCase().endsWith(".htm");
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
