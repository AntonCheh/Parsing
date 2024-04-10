package secondJsonFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import firstJsonFile.CsvParsing;
import firstJsonFile.JsonParsing;
import firstJsonFile.Station;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonWriterCommon {

    public static void main(String[] args) throws IOException {
        List<Station> jsonStations = JsonParsing.getListofDateJsons();
        List<Station> csvStations = CsvParsing.parseAndCollectStations();
        List<Station> allStations = combineStations(jsonStations, csvStations);
        writeStationsToJson(allStations, "JsonWriterCommon.json");
    }

    public static List<Station> combineStations(List<Station> jsonStations, List<Station> csvStations) {

        Map<String, Station> stringStationMap = new HashMap<>();
        for (Station station : jsonStations) {
            stringStationMap.put(station.getStationName(), station);
        }

        for (Station station : csvStations) {
            Station stationMap = stringStationMap.get(station.getStationName());
            stationMap.setBuildDate(station.getBuildDate());
            stringStationMap.put(stationMap.getStationName(), stationMap);
        }
        List<Station> allStations = new ArrayList<>(stringStationMap.values());


        return allStations;
    }

    public static void writeStationsToJson(List<Station> stations, String filePath) {
        JsonArray stationsJsonArray = new JsonArray();

        for (Station station : stations) {
            JsonObject stationJson = new JsonObject();
            stationJson.addProperty("name", station.getStationName());
            stationJson.addProperty("date", String.valueOf(station.getBuildDate()));
            stationJson.addProperty("depth", station.getDepth());
            stationJson.addProperty("hasConnection", station.hasTransfer());

            stationsJsonArray.add(stationJson);
        }

        JsonObject outputJson = new JsonObject();
        outputJson.add("stations", stationsJsonArray);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(outputJson, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//    public static void main(String[] args) throws IOException {
//        // Пример использования:
//        List<firstJsonFile.Station> stations = firstJsonFile.JsonParsing.getListofDateJsons();
//        writeStationsToJson(stations, "output.json");
//    }
