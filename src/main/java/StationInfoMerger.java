import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationInfoMerger {
    public static void main(String[] args) throws IOException {
        // Получаем списки станций и их информацию из JsonParsing и CsvParsing
        List<Station> jsonStations = JsonParsing.getListofDateJsons();
        List<Station> csvStations = CsvParsing.parseAndCollectStations();

        // Получаем список линий и станций из HTML-файла
        List<String> lineAndStationNames = WebParsingHtmlLines.parseAndReturnLineAndStationNames();

        // Создаем HashMap для объединенной информации
        Map<String, StationInfo> mergedInfo = mergeStationsInfo(jsonStations, csvStations, lineAndStationNames);

        // Выводим объединенную информацию
        printMergedInfo(mergedInfo);
    }

    public static Map<String, StationInfo> mergeStationsInfo(List<Station> jsonStations, List<Station> csvStations, List<String> lineAndStationNames) {
        Map<String, StationInfo> mergedInfo = new HashMap<>();

        // Добавляем информацию из jsonStations
        for (Station station : jsonStations) {
            String stationName = station.getStationName();
            String depth = station.getDepth();
            mergedInfo.put(stationName, new StationInfo(null, depth));
        }

        // Добавляем информацию из csvStations
        for (Station station : csvStations) {
            String stationName = station.getStationName();
            LocalDate buildDate = station.getDate();
            mergedInfo.computeIfPresent(stationName, (key, existingInfo) -> {
                existingInfo.setBuildDate(buildDate);
                return existingInfo;
            });
        }

        // Добавляем информацию о линиях для каждой станции
        for (String lineAndStationName : lineAndStationNames) {
            String[] parts = lineAndStationName.split("line: ");
            if (parts.length == 2) {
                String stationName = parts[0].trim();
                String lineInfo = parts[1].trim();
                mergedInfo.computeIfPresent(stationName, (key, existingInfo) -> {
                    existingInfo.setLine(lineInfo);
                    return existingInfo;
                });
            }
        }

        return mergedInfo;
    }

    public static void printMergedInfo(Map<String, StationInfo> mergedInfo) {
        for (Map.Entry<String, StationInfo> entry : mergedInfo.entrySet()) {
            System.out.println("name: " + entry.getKey());
            System.out.println("date: " + entry.getValue().getBuildDate());
            System.out.println("line: " + entry.getValue().getLine());
            System.out.println("depth: " + entry.getValue().getDepth());
            System.out.println("has connection: " );
            System.out.println();
        }
    }
}

class StationInfo {
    private LocalDate buildDate;
    private String depth;
    private String line;

    public StationInfo(LocalDate buildDate, String depth) {
        this.buildDate = buildDate;
        this.depth = depth;
    }

    public LocalDate getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(LocalDate buildDate) {
        this.buildDate = buildDate;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
