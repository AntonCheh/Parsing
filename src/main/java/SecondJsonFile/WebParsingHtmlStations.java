package SecondJsonFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import firstJsonFile.LocalDateAdapter;
import firstJsonFile.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class WebParsingHtmlStations {

    static Document document;

    static {
        try {
            document = Jsoup.connect("https://skillbox-java.github.io").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static Elements elements = document.select("div.js-metro-stations");


    public static Map<String, List<Station>> parseWebStations() {
        Map<String, List<Station>> stationMap = new HashMap<>();

        elements.forEach(element -> {
            String lineNumber = element.attr("data-line");
            List<Station> stationList = new ArrayList<>(); // Создаем новый список станций для текущей линии

            Elements stations = element.select("p.single-station");
            stations.forEach(stationElement -> {
                String stationName = stationElement.select("span.name").text();
                boolean hasTransfer = stationElement.select("span.t-icon-metroln").size() > 0; // Проверка на наличие перехода
                Station station = new Station(stationName, lineNumber, hasTransfer); // Создаем объект станции
                stationList.add(station); // Добавляем станцию в список
            });

            stationMap.put(lineNumber, stationList); // Добавляем список станций в карту по ключу номера линии
        });

        return stationMap;
    }
}

