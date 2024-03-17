import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class JsonWriterCvs {

    public static void main(String[] args) {
        // Парсим CSV файлы и получаем список объектов Station
        List<Station> stations = CsvParsing.parseAndCollectStations();

        // Записываем список объектов Station в JSON файл
        writeStationsToJson(stations, "output22.json");
    }

    public static void writeStationsToJson(List<Station> stations, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            gson.toJson(stations, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}