package SecondJsonFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import firstJsonFile.Station;
import firstJsonFile.WebParsingHtmlLines;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MetroJsonWriter {
    public static void main(String[] args) {
        MetroSystem metroSystem = buildMetroSystem();
        writeToJsonFile(metroSystem, "metro.json");
    }

    public static MetroSystem buildMetroSystem() {
        // Получаем данные о станциях и линиях из классов парсинга
        Map<String, Station> stationMap = WebParsingHtmlStations.parseWebStations();
        List<String> lineAndStationNames = WebParsingHtmlLines.parseAndReturnLineAndStationNames();

        // Создаем объект MetroSystem и заполняем его данными
        MetroSystem metroSystem = new MetroSystem();
        List<MetroLine> lines = new ArrayList<>();

        for (String lineAndStationName : lineAndStationNames) {
            String[] parts = lineAndStationName.split(" line: ");
            String stationName = parts[0];
            String lineNumber = parts[1];

            // Получаем объект станции из stationMap
            Station station = stationMap.get(stationName);
            if (station != null) {
                MetroStation metroStation = new MetroStation(stationName, lineNumber, station.hasTransfer());

                // Проверяем, существует ли уже линия с таким номером
                MetroLine metroLine = findLine(lines, lineNumber);
                if (metroLine == null) {
                    metroLine = new MetroLine(lineNumber);
                    lines.add(metroLine);
                }

                // Добавляем станцию в линию
                metroLine.addStation(metroStation);
            }
        }

        metroSystem.setLines(lines);
        return metroSystem;
    }

    private static MetroLine findLine(List<MetroLine> lines, String lineNumber) {
        for (MetroLine line : lines) {
            if (line.getName().equals(lineNumber)) {
                return line;
            }
        }
        return null;
    }

    public static void writeToJsonFile(MetroSystem metroSystem, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(metroSystem, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
