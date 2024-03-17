import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Создание объекта Station с датой
        Station stationWithDate = new Station("Station with Date", LocalDate.of(2022, 3, 1));
        System.out.println(stationWithDate);

        // Создание объекта Station без даты
        Station stationWithoutDate = new Station("Station without Date", "Some depth");
        System.out.println(stationWithoutDate);
    }
}
