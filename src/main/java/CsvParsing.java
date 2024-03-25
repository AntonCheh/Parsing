import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvParsing {
    public static int count = 0;

    public static void main(String[] args) throws IOException {
     FindFolder.walkAndPrintFiles(Path.of(FindFolder.pathOfFolder));
     parseAndPrint(FindFolder.pathsOfCvs);
    }

    public static void parseAndPrint(List<String> filePath) {
        for (String filePaths : filePath) {
            List<Station> parsedFiles = parseCSV(filePaths);
            printParsedFiles(parsedFiles);
        }
    }

    public static void printParsedFiles(List<Station> parsedFiles) {
        for (int i = 0; i < parsedFiles.size(); i++) {
            Station obj = parsedFiles.get(i);
            System.out.print((i + 1) + " - ");
            System.out.println(obj);
        }
    }

    static List<Station> parseCSV(String filePath) {
        List<Station> information = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            boolean titleSkip = false; // флаг, чтобы пропустить первую строку, если она содержит заголовки

            while ((line = reader.readLine()) != null) {
                if (!titleSkip) {
                    titleSkip = true;
                    continue; // пропустить первую строку с заголовками
                }
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    // Создаем объект и добавляем его в список
                    Station station = new Station(fields[0], parseDate(fields[1]));
                    information.add(station);
                    count++;
                } else {
                    System.err.println("Некорректное количество полей в строке: " + line);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return information;
    }

    public static LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    public static List<Station> parseAndCollectStations() {
        List<Station> stations = new ArrayList<>();

        try {
            FindFolder.walkAndPrintFiles(Path.of(FindFolder.pathOfFolder));

            // Парсим все CSV файлы, находим станции и их даты открытия
            for (String csvFilePath : FindFolder.pathsOfCvs) {
                List<Station> parsedStations = parseCSV(csvFilePath);
                stations.addAll(parsedStations);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stations;
    }
}


