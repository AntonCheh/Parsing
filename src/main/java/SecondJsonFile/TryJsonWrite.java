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

public class TryJsonWrite {
    public static void main(String[] args) {
        // Получаем данные о станциях из парсера
        Map<String, List<Station>> stationMap = WebParsingHtmlStations.parseWebStations();

        // Создаем новую карту для хранения станций в желаемом формате
        Map<String, List<String>> formattedStationsMap = new HashMap<>();

        // Преобразуем данные из stationMap в формат для formattedStationsMap
        stationMap.forEach((lineNumber, stationList) -> {
            List<String> stationNames = new ArrayList<>();
            for (Station station : stationList) {
                stationNames.add(station.getStationName());
            }
            formattedStationsMap.put(lineNumber, stationNames);
        });

        // Оборачиваем данные в объект "stations"
        Map<String, Map<String, List<String>>> data = new HashMap<>();
        data.put("stations", formattedStationsMap);

        // Создаем объект Gson для сериализации
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Сериализуем данные в JSON
        String json = gson.toJson(data);

        // Записываем JSON в файл
        try (FileWriter writer = new FileWriter("stations.json")) {
            writer.write(json);
            System.out.println("JSON записан успешно в файл stations.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
