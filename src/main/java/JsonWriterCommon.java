import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            stationMap.setDate(station.getDate());
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
            stationJson.addProperty("date", String.valueOf(station.getDate()));
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
//        List<Station> stations = JsonParsing.getListofDateJsons();
//        writeStationsToJson(stations, "output.json");
//    }
