package secondJsonFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import firstJsonFile.CsvParsing;
import firstJsonFile.Station;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class JsonWriterCvs {

    public static void main(String[] args) throws IOException {
        // Парсим CSV файлы и получаем список объектов firstJsonFile.Station
        List<Station> stations = CsvParsing.parseAndCollectStations();

        // Записываем список объектов firstJsonFile.Station в JSON файл
        writeStationsToJson(stations, "cvsWriter.json");
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