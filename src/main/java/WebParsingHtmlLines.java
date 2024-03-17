import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WebParsingHtmlLines {

    public static void main(String[] args) {
        List<String> lineNames = parseAndReturnLineNames();

        for (String line : lineNames) {
            System.out.println(line);
        }
    }

    public static List<String> parseAndReturnLineNames() {
        List<String> lineNames = new ArrayList<>();

        String htmlFile = parseFile(Path.of("data/code.html"));
        Document document = Jsoup.parse(htmlFile);
        Elements elements = document.select("div.js-toggle-depend");

        elements.forEach(element -> {
            String lineName = element.select("span.js-metro-line").text();
            lineNames.add(lineName);
        });

        return lineNames;
    }

    private static String parseFile(Path path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(String.valueOf(path)));
            lines.forEach(line -> builder.append(line).append("\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}


