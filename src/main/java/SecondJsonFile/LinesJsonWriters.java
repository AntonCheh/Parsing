package SecondJsonFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import firstJsonFile.Station;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LinesJsonWriters {

    public static void main(String[] args) {
        // Получаем данные о станциях из парсера
        Map<String, List<Station>> stationMap = WebParsingHtmlStations.parseWebStations();

        // Создаем линии метро
        List<MetroLine> lines = createLines(stationMap);

        // Сериализуем данные в JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(Map.of("lines", lines));

        // Записываем JSON в файл
        try (FileWriter writer = new FileWriter("lines.json")) {
            writer.write(json);
            System.out.println("JSON записан успешно в файл lines.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<MetroLine> createLines(Map<String, List<Station>> stationMap) {
        List<MetroLine> lines = new ArrayList<>();

        // Проходимся по всем линиям метро
        for (String lineNumber : stationMap.keySet()) {
            // Получаем список станций на текущей линии
            List<Station> stations = stationMap.get(lineNumber);

            // Если список станций не пустой, добавляем линию
            if (!stations.isEmpty()) {
                String lineName = stations.get(0).getStationName(); // Возьмем название линии из первой станции
                lines.add(new MetroLine(lineNumber, lineName));
            }
        }
        return lines;
    }
}
