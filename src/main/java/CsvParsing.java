import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CsvParsing {
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        printParsedFiles(parseAndCollectStations());
    }

    public static List<Station> parseAndCollectStations() throws IOException {
        List<Station> stations = new ArrayList<>();
        Set<String> stationNames = new HashSet<>(); // Множество для хранения названий станций
        FindFolder.walkAndPrintFiles(Path.of(FindFolder.pathOfFolder));
        List<String> listOsCvsFiles = FindFolder.pathsOfCvs;

        // Парсим все CSV файлы, находим станции и их даты открытия
        for (String csvFilePath : listOsCvsFiles) {
            List<Station> parsedStations = parseCSV(csvFilePath, stationNames); // Передаем множество в метод парсинга CSV
            stations.addAll(parsedStations);
        }
        return stations;
    }

    static List<Station> parseCSV(String filePath, Set<String> stationNames) {
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
                    String stationName = fields[0];
                    if (!stationNames.contains(stationName)) { // Проверяем, не содержится ли станция уже в множестве
                        //  Создаем, объект и добавляем его в список
                        Station station = new Station(stationName, parseDate(fields[1]));
                        information.add(station);
                        stationNames.add(stationName); // Добавляем название станции во множество
                        count++;
                    }
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

    public static void printParsedFiles(List<Station> parsedFiles) {
        for (int i = 0; i < parsedFiles.size(); i++) {
            Station obj = parsedFiles.get(i);
            System.out.print((i + 1) + " - ");
            System.out.println(obj);
        }
    }

}


