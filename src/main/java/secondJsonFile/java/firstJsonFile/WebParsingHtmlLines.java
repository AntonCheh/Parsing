package secondJsonFile.java.firstJsonFile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WebParsingHtmlLines {

    public static void main(String[] args) {
        List<String> lineAndStationNames = parseAndReturnLineAndStationNames();

        for (String name : lineAndStationNames) {
            System.out.println(name);
        }
    }

    public static List<String> parseAndReturnLineAndStationNames() {
        List<String> lineAndStationNames = new ArrayList<>();

        try {
            // Чтение HTML-файла
            String htmlContent = Files.readString(Path.of("data/code.html"));

            // Парсинг HTML с помощью Jsoup
            Document document = Jsoup.parse(htmlContent);
            Elements lineElements = document.select("div.js-toggle-depend");

            // Извлечение названий линий и станций
            lineElements.forEach(lineElement -> {
                String lineName = lineElement.select("span.js-metro-line").text();

                // Получаем список станций на данной линии
                Elements stationElements = lineElement.nextElementSibling().select("p.single-station");

                // Добавляем название линии к каждой станции на этой линии
                for (Element stationElement : stationElements) {
                    String stationName = stationElement.select("span.name").text();
                    lineAndStationNames.add(stationName + " line: " + lineName); // Добавляем название линии
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineAndStationNames;
    }
}