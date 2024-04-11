package firstJsonFile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        // Получаем списки станций и их информацию из firstJsonFile.JsonParsing и firstJsonFile.CsvParsing
        List<Station> jsonStations = JsonParsing.getListofDateJsons();
        List<Station> csvStations = CsvParsing.parseAndCollectStations();
        Map<String, Boolean> stationMap = parseWebStations();

        // Получаем список линий и станций из HTML-файла
        List<String> lineAndStationNames = WebParsingHtmlLines.parseAndReturnLineAndStationNames();

        // Создаем HashMap для объединенной информации
        Map<String, Station> mergedInfo = mergeStationsInfo(jsonStations, csvStations, lineAndStationNames, stationMap);

        // Выводим объединенную информацию
     // printMergedInfo(mergedInfo);

      JsonWriter.writeToJsonFile(mergedInfo, "merged_info.json");
    }

    public static Map<String, Station> mergeStationsInfo(List<Station> jsonStations, List<Station> csvStations,
                                                             List<String> lineAndStationNames, Map<String, Boolean> stationMap) {
        Map<String, Station> mergedInfo = new HashMap<>();

        // Добавляем информацию из jsonStations
        for (Station station : jsonStations) {
            String stationName = station.getStationName();
            String depth = station.getDepth();
            mergedInfo.put(stationName, new Station(String.valueOf((LocalDate) null), depth));
        }

        // Добавляем информацию из csvStations
        for (Station station : csvStations) {
            String stationName = station.getStationName();
            LocalDate buildDate = station.getBuildDate();
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
        // Добавляем информацию о наличии пересадок
        stationMap.forEach((stationName, hasConnection) ->
                mergedInfo.computeIfPresent(stationName, (key, existingInfo) -> {
                    existingInfo.setHasTransfer(hasConnection);
                    return existingInfo;
                })
        );
        return mergedInfo;
    }

    public static void printMergedInfo(Map<String, Station> mergedInfo) {
        for (Map.Entry<String, Station> entry : mergedInfo.entrySet()) {
            Station stationInfo = entry.getValue();
            System.out.println("\"name\": \"" + entry.getKey() + "\",");
            System.out.println("\"line\": \"" + stationInfo.getLine() + "\",");
            System.out.println("\"date\": \"" + (stationInfo.getBuildDate() != null ? stationInfo.getBuildDate() : "") + "\",");
            System.out.println("\"depth\": " + stationInfo.getDepth() + ",");
            System.out.println("\"hasConnection\": " + stationInfo.hasTransfer());
            System.out.println();
        }
    }

    public static Map<String, Boolean> parseWebStations() {
        Map<String, Boolean> stationMap = new HashMap<>();

        Document document;
        try {
            document = Jsoup.connect("https://skillbox-java.github.io").get();
            Elements elements = document.select("div.js-metro-stations");

            elements.forEach(element -> {
                Elements stations = element.select("p.single-station");

                stations.forEach(station -> {
                    String stationName = station.select("span.name").text();
                    boolean hasTransfer = station.select("span.t-icon-metroln").size() > 0;
                    stationMap.put(stationName, hasTransfer);
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stationMap;
    }
}


