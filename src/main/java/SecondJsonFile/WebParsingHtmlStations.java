package SecondJsonFile;

import firstJsonFile.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void main(String[] args) {
        Map<String, Station> stationMap = parseWebStations();
        // Выводим содержимое карты в консоль
        stationMap.forEach((stationName, station) -> {
            System.out.println("Station Name: " + stationName);
            System.out.println("Line Number: " + station.getLineNumber());
            System.out.println("Has Connection: " + station.hasTransfer());
            System.out.println();
        });
    }

    public static Map<String, Station> parseWebStations() {
        Map<String, Station> stationMap = new HashMap<>();

        elements.forEach(element -> {
            String lineNumber = element.attr("data-line");

            Elements stations = element.select("p.single-station");

            stations.forEach(stationElement -> {
                String stationName = stationElement.select("span.name").text();
                boolean hasTransfer = stationElement.select("span.t-icon-metroln").size() > 0; // Проверка на наличие перехода

                Station station = new Station(stationName, lineNumber, hasTransfer); // Создаем объект станции с информацией о переходе
                stationMap.put(stationName, station);
            });
        });

        return stationMap;
    }
}

