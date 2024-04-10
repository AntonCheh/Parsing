//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WebParsingHtmlData {
//
//    public static void main(String[] args) {
//         //Парсинг и вывод списка имен линий
////        List<String> lineNames = parseAndReturnLineNames();
////        System.out.println("Line Names:");
////        lineNames.forEach(System.out::println);
////        System.out.println();
//
//        // Парсинг и вывод списка линий со станциями
//        List<Line> lines = parseWebStations();
//
//        for (Object line : lines) {
//            System.out.println(line);
//        }
//
//    }
//
//    // Метод для парсинга и возвращения списка имен линий
//    public static List<String> parseAndReturnLineNames() {
//        List<String> lineNames = new ArrayList<>();
//
//        String htmlFile = parseFile(Path.of("data/code.html")); // Путь к HTML-файлу
//        Document document = Jsoup.parse(htmlFile);
//        Elements elements = document.select("div.js-toggle-depend");
//
//        elements.forEach(element -> {
//            String lineName = element.select("span.js-metro-line").text();
//            lineNames.add(lineName);
//        });
//
//        return lineNames;
//    }
//
//    // Метод для парсинга и возвращения списка линий со станциями
//    public static List<Line> parseWebStations() {
//        List<Line> lines = new ArrayList<>();
//
//        Document document;
//        try {
//            document = Jsoup.connect("https://skillbox-java.github.io").get();
//            Elements elements = document.select("div.js-metro-stations");
//
//            elements.forEach(element -> {
//                String lineNumber = element.attr("data-line");
//                Line line = new Line(lineNumber);
//                Elements stations = element.select("p.single-station");
//                List<firstJsonFile.Station> stationList = new ArrayList<>(); // Создаем список станций для текущей линии
//
//                stations.forEach(station -> {
//                    String stationName = station.select("span.name").text();
//                    boolean hasTransfer = station.select("span.t-icon-metroln").size() > 0;
//                    firstJsonFile.Station stationObject = new firstJsonFile.Station(stationName, stationInfo.getLine(), stationInfo.getBuildDate(), stationInfo.getDepth(), hasTransfer);
//                    stationList.add(stationObject); // Добавляем станцию в список станций для текущей линии
//                });
//
//                line.setStations(stationList); // Устанавливаем список станций для текущей линии
//                lines.add(line); // Добавляем линию в список линий
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return lines; // Возвращаем список линий
//    }
//
//    // Метод для чтения HTML-файла и возвращения его содержимого в виде строки
//    private static String parseFile(Path path) {
//        StringBuilder builder = new StringBuilder();
//        try {
//            List<String> lines = Files.readAllLines(path);
//            lines.forEach(line -> builder.append(line).append("\n"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return builder.toString();
//    }
//}
