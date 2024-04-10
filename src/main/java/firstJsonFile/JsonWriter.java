package firstJsonFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonWriter {

    public static void main(String[] args) {

    }


    public static void writeToJsonFile(Map<String, StationInfo> mergedInfo, String filePath) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();

        StationsContainer stationsContainer = new StationsContainer();
        for (Map.Entry<String, StationInfo> entry : mergedInfo.entrySet()) {
            StationInfo stationInfo = entry.getValue();
            Station station = new Station(entry.getKey(), stationInfo.getLine(), stationInfo.getBuildDate(), stationInfo.getDepth(), stationInfo.isHasConnection());
            stationsContainer.addStation(station);
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(stationsContainer, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class StationsContainer {
        private List<Station> stations;

        public StationsContainer() {
            this.stations = new ArrayList<>();
        }

        public void addStation(Station station) {
            stations.add(station);
        }
    }
}