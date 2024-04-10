package firstJsonFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Station {
    private String lineNumber;
    public String stationName;
    private String depth;
    public LocalDate buildDate;
    private String line;
    private boolean hasTransfer;

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public void setHasTransfer(boolean hasTransfer) {
        this.hasTransfer = hasTransfer;
    }


    public Station(String stationName, String lineNumber,  boolean hasTransfer) {
        this.stationName = stationName;
        this.hasTransfer = hasTransfer;
        this.lineNumber = lineNumber;
    }

    public Station(String stationName, String depth) {
        this.stationName = stationName;
        this.depth = depth;
    }

    public Station(String stationName, LocalDate date) {
        this.stationName = stationName;
        this.buildDate = date;
    }

    public Station(String stationName,  boolean hasTransfer) {
        this.stationName = stationName;
        this.hasTransfer = hasTransfer;
    }

    public boolean hasTransfer() {
        return hasTransfer;
    }



    public String getStationName() {
        return stationName;
    }

    public String getDepth() {
        return depth;
    }

    public LocalDate getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(LocalDate buildDate) {
        this.buildDate = buildDate;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        if (depth != null) {
            return stationName + " " + depth;
        } else if (buildDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return stationName + " " + buildDate.format(formatter);
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
                Objects.equals(buildDate, station.buildDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, depth, buildDate);
    }
}