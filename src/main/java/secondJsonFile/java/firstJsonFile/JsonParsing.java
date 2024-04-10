package secondJsonFile.java.firstJsonFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import secondJsonFile.FindFolder;
import secondJsonFile.Station;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonParsing {
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        List<Station> stations = getListofDateJsons();

        for (int i = 0; i < stations.size(); i++) {
            System.out.println((i + 1) + " - " + stations.get(i));
        }
    }

    static List<Station> parseJsonFiles(List<String> filePaths) {
        List<Station> allStations = new ArrayList<>();
        Set<String> stationNames = new HashSet<>();

        JSONParser parser = new JSONParser();
        for (String filePath : filePaths) {
            try {
                Object jsonData = parser.parse(Files.newBufferedReader(Path.of(filePath)));

                if (jsonData instanceof JSONArray) {
                    List<Station> stations = parseStations((JSONArray) jsonData, stationNames);
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

    private static List<Station> parseStations(JSONArray stationsArray, Set<String> stationNames) {
        List<Station> stations = new ArrayList<>();
        stationsArray.forEach(stationObject -> {
            if (stationObject instanceof JSONObject) {
                JSONObject stationJson = (JSONObject) stationObject;
                String stationName = (String) stationJson.get("station_name");

                if (!stationNames.contains(stationName)) {
                    String depth = (String) stationJson.get("depth");
                    Station station = new Station(stationName, depth);
                    stations.add(station);
                    stationNames.add(stationName);
                    count++;
                }
            } else {
                throw new IllegalArgumentException("Invalid JSON format: Expected JSONObject in stationsArray");
            }
        });
        return stations;
    }

    public static List<Station> getListofDateJsons() throws IOException {
        FindFolder.walkAndPrintFiles(Path.of(FindFolder.pathOfFolder));
        return parseJsonFiles(FindFolder.pathsOfJsons);
    }
}