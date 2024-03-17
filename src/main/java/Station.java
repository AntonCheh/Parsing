import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Station {
    public String stationName;
    private String depth;
    public LocalDate date;
    private boolean hasTransfer;

    private String lineName;

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public void setHasTransfer(boolean hasTransfer) {
        this.hasTransfer = hasTransfer;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Station(String stationName, boolean hasTransfer) {
        this.stationName = stationName;
        this.hasTransfer = hasTransfer;
    }

    public boolean hasTransfer() {
        return hasTransfer;
    }

    public Station(String stationName, String depth) {
        this.stationName = stationName;
        this.depth = depth;
    }

    public Station(String stationName, LocalDate date) {
        this.stationName = stationName;
        this.date = date;
    }

    public String getStationName() {
        return stationName;
    }

    public String getDepth() {
        return depth;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        if (depth != null) {
            return stationName + " " + depth;
        } else if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return stationName + " " + date.format(formatter);
        } else {
            return stationName;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(stationName, station.stationName) &&
                Objects.equals(depth, station.depth) &&
                Objects.equals(date, station.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, depth, date);
    }
}