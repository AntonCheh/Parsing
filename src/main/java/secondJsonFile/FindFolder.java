package secondJsonFile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FindFolder {
    public static List<String> pathsOfJsons = new ArrayList<>();
    public static List<String> pathsOfCvs = new ArrayList<>();
    public static String pathOfFolder = "folder/data";

    public static void main(String[] args) {

        try {
            walkAndPrintFiles(Path.of(pathOfFolder));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(pathsOfJsons);
        System.out.println(pathsOfCvs);
    }

    public static List<String> walkAndPrintFiles(Path directory) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path path : stream) {
                if (Files.isDirectory(path)) {
                    walkAndPrintFiles(path);
                } else {
                    printFileInfo(path);
                }
            }
        }
        return null;
    }

    private static void printFileInfo(Path file) {
        String fileName = file.getFileName().toString().toLowerCase();
        String relativePathString = file.toString();

        if (relativePathString.startsWith("/")) {
            // Удаляем начальный символ "/", если он есть
            relativePathString = relativePathString.substring(1);
        }

        if (fileName.endsWith(".json")) {
            pathsOfJsons.add(relativePathString);
        } else if (fileName.endsWith(".csv")) {
            pathsOfCvs.add(relativePathString);
        }
    }
}

