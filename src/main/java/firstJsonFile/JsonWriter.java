package firstJsonFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

    public static void writeToJsonFile(Map<String, StationInfo> mergedInfo, String filePath) {
        // Создаем новый объект Gson с красивым форматированием
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();

        // Создаем массив для станций
        JsonArray stationsArray = new JsonArray();

        // Проходимся по объединенной информации и добавляем каждую станцию в массив
        for (Map.Entry<String, StationInfo> entry : mergedInfo.entrySet()) {
            JsonObject stationObject = new JsonObject();
            StationInfo stationInfo = entry.getValue();

            stationObject.addProperty("name", entry.getKey());
            stationObject.addProperty("line", stationInfo.getLine());
            stationObject.addProperty("date", stationInfo.getBuildDate() != null ? stationInfo.getBuildDate().toString() : "");
            stationObject.addProperty("depth", stationInfo.getDepth());
            stationObject.addProperty("hasConnection", stationInfo.isHasConnection());

            stationsArray.add(stationObject);
        }

        // Добавляем массив станций в JSON объект
        jsonObject.add("stations", stationsArray);

        // Записываем JSON объект в файл
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(jsonObject, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}