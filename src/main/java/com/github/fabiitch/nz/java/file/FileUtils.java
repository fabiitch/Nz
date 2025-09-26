package com.github.fabiitch.nz.java.file;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;

@UtilityClass
public class FileUtils {

    public static File getIncFile(File folder, String fileName) {
        int inc = 1;
        String extension = getExtension(fileName);
        String baseName = getBaseName(fileName);
        Path resolve = folder.toPath().resolve(baseName + "-" + inc + extension);
        while (new File(resolve.toUri()).exists()) {
            inc++;
            resolve = folder.toPath().resolve(baseName + "-" + inc + extension);
        }
        return resolve.toFile();
    }

    public static String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex < 0) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }

    public static String getExtension(File file) {
        return getExtension(file.getName());
    }

    public static String getBaseName(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex < 0) {
            return fileName;
        }
        return fileName.substring(0, dotIndex);
    }

    public static String getBaseName(File file) {
        return getBaseName(file.getName());
    }
}
