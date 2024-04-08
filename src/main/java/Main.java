import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FindFolder.walkAndPrintFiles(Path.of(FindFolder.pathOfFolder));
        List<String> cvsData = FindFolder.pathsOfCvs;
       for (String data : cvsData) {
           System.out.println(data);
       }

    }
}
