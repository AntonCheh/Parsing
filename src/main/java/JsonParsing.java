import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonParsing {

      public static List<Station> getListofDateJsons () throws IOException {
        FindFolder.walkAndPrintFiles(Path.of(FindFolder.pathOfFolder));
          //        if (allStations != null) {
//            printStations(allStations);
//        } else {
//            System.out.println("Не удалось распарсить JSON файлы.");
//        }
          return parseJsonFile(FindFolder.pathsOfJsons);
      }

    static List<Station> parseJsonFile(List<String> filePaths) {
        List<Station> allStations = new ArrayList<>();
        JSONParser parser = new JSONParser();
        for (String filePath : filePaths) {
            try {

                Object jsonData = parser.parse(Files.newBufferedReader(Path.of(filePath)));

                if (jsonData instanceof JSONArray) {
                    List<Station> stations = parseStations((JSONArray) jsonData);
                    if (stations != null) {
                        allStations.addAll(stations);
                    }
                } else {
                    throw new IllegalArgumentException("Invalid JSON format: Expected JSONArray");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return allStations;
    }

    private static List<Station> parseStations(JSONArray stationsArray) {
        List<Station> stations = new ArrayList<>();
        stationsArray.forEach(stationObject -> {
            if (stationObject instanceof JSONObject) {
                JSONObject stationJson = (JSONObject) stationObject;
                String stationName = (String) stationJson.get("station_name");
                String depth = (String) stationJson.get("depth");

                Station station = new Station(stationName, depth);
                stations.add(station);
            } else {
                throw new IllegalArgumentException("Invalid JSON format: Expected JSONObject in stationsArray");
            }
        });
        return stations;
    }

    private static void printStations(List<Station> stations) {
        stations.forEach(station -> {
            System.out.println("Station Name: " + station.getStationName());
            System.out.println("Depth: " + station.getDepth());
            System.out.println();
        });
    }

}