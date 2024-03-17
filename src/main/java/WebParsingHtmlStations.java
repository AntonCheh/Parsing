import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<Line> lines = parseWebStations();
        lines.forEach(System.out::println);
        // Теперь у вас есть список lines, который вы можете использовать для дальнейшей работы
    }

    public static List<Line> parseWebStations() {
        List<Line> lines = new ArrayList<>();

        elements.forEach(element -> {
            String lineNumber = element.attr("data-line");
            Line line = new Line(lineNumber);
            Elements stations = element.select("p.single-station");
            List<Station> stationList = new ArrayList<>(); // Создаем список станций для текущей линии

            stations.forEach(station -> {
                String stationName = station.select("span.name").text();
                boolean hasTransfer = station.select("span.t-icon-metroln").size() > 0;
                Station stationObject = new Station(stationName, hasTransfer);
                stationList.add(stationObject); // Добавляем станцию в список станций для текущей линии
            });

            line.setStations(stationList); // Устанавливаем список станций для текущей линии
            lines.add(line); // Добавляем линию в список линий
        });

        return lines; // Возвращаем список линий
    }
}