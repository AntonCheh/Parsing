package SecondJsonFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import firstJsonFile.Station;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonJsonDone {
    public static void main(String[] args) {
        // Получаем данные о станциях из парсера
        Map<String, List<Station>> stationMap = WebParsingHtmlStations.parseWebStations();

        // Создаем линии метро
        List<MetroLine> lines = createLines(stationMap);

        // Создаем связи между станциями
        List<List<Connection>> connections = createConnections(stationMap);

        // Создаем форматированные станции
        Map<String, List<String>> formattedStationsMap = createFormattedStations(stationMap);

        // Сериализуем данные в JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, Object> data = new HashMap<>();
        data.put("stations", formattedStationsMap);
        data.put("connections", connections);
        data.put("lines", lines);
        String json = gson.toJson(data);

        // Записываем JSON в файл
        try (FileWriter writer = new FileWriter("metroFinal.json")) {
            writer.write(json);
            System.out.println("JSON записан успешно в файл metroFinal.json");
        }  catch (IOException e) {
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

    private static List<List<Connection>> createConnections(Map<String, List<Station>> stationMap) {
        List<List<Connection>> connections = new ArrayList<>();
        // Проходимся по всем линиям метро
        for (List<Station> stations : stationMap.values()) {
            List<Connection> lineConnections = new ArrayList<>();
            for (Station station : stations) {
                if (station.hasTransfer()) { // Если у станции есть переходы
                    lineConnections.add(new Connection(station.getLineNumber(), station.getStationName()));
                }
            }
            if (!lineConnections.isEmpty()) { // Если есть связи на текущей линии
                connections.add(lineConnections);
            }
        }
        return connections;
    }

    private static Map<String, List<String>> createFormattedStations(Map<String, List<Station>> stationMap) {
        Map<String, List<String>> formattedStationsMap = new HashMap<>();
        // Преобразуем данные из stationMap в формат для formattedStationsMap
        stationMap.forEach((lineNumber, stationList) -> {
            List<String> stationNames = new ArrayList<>();
            for (Station station : stationList) {
                stationNames.add(station.getStationName());
            }
            formattedStationsMap.put(lineNumber, stationNames);
        });
        return formattedStationsMap;
    }
}