import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class JsonWriteFromHTML {

    public static void main(String[] args) {
        //List<String> lineNames = WebParsingHtmlLines.parseAndReturnLineNames();
        //List<Line> lines = WebParsingHtmlStations.parseWebStations();

       // writeLinesAndStationsToJson(lineNames, lines, "html.json");
    }

    public static void writeLinesAndStationsToJson(List<String> lineNames, List<Line> lines, String filePath) {
        // Создаем объект JSON, который будет содержать данные о линиях и станциях
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (localDate, type, jsonSerializationContext) ->
                        jsonSerializationContext.serialize(localDate.toString()))
                .setPrettyPrinting()
                .create();

        JsonObject jsonData = new JsonObject();
        jsonData.add("lineNames", gson.toJsonTree(lineNames));
        jsonData.add("lines", gson.toJsonTree(lines));

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(jsonData, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

