package SecondJsonFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import firstJsonFile.Station;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class ConnectionJsonWriter {
    public static void main(String[] args) {
        // Получаем данные о станциях из парсера
        Map<String, List<Station>> stationMap = WebParsingHtmlStations.parseWebStations();

        // Создаем связи между станциями
        List<List<Connection>> connections = createConnections(stationMap);

        // Сериализуем данные в JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(Map.of("connections", connections));

        // Записываем JSON в файл
        try (FileWriter writer = new FileWriter("connections.json")) {
            writer.write(json);
            System.out.println("JSON записан успешно в файл connections.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}