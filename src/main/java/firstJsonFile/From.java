package firstJsonFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class From {

    public static String path = "/Users/User/Desktop/";
    public static String in = path + "stations.zip";
    public static String out = "folder/";


    public static void main(String[] args) throws Exception {

        FileInputStream inputStream = new FileInputStream(in);
        ZipInputStream zipInput = new ZipInputStream(inputStream);
        while (true) {
            ZipEntry entry = zipInput.getNextEntry();
            if (entry == null) {
                break;
            }
            File file = new File(out + entry.getName());
            if (entry.isDirectory()) {
                file.mkdirs();
            } else {
                byte[] bytes = zipInput.readAllBytes();
                Files.write(Paths.get(file.getAbsolutePath()), bytes, StandardOpenOption.CREATE);
            }
        }
    }
}