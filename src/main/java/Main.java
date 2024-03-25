import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Line> stations = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        lines = WebParsingHtmlLines.parseAndReturnLineNames();
        stations = WebParsingHtmlStations.parseWebStations();

        List<String> fileNames = new ArrayList<>();
        fileNames = FindFolder.walkAndPrintFiles(Path.of(FindFolder.pathOfFolder));

        // Вызовите FinfFolder. ОН должен вернуть список путей ко всем файлам. Положите его в список выше.

//
//        for(String fileName: fileNames){
//            // для каждого fileName определите тип данных.
//            if fileName -> json{
//                // Вызвать парсер json
//                // В парсер передать stations. Внутри парсера найти нужную станцию по имени. Дописать в неё глубину
//            }
//            if fileName -> csv{
//                // Вызвать парсер csv
//                // В парсер передать stations. Внутри парсера найти нужную станцию по имени. Дописать в неё дату.
//            }
//        }
//
//        for(Station station: stations){
//            // Преобразовать каждую станцию в json и сложить в JsonArray. ИЛИ передать stations в класс записи
//            // в файл и уже там собрать из списка станций json.
//            // После того как есть json записать его в файл (это уже точно внутри класса отдельного)
//        }

    }
}
