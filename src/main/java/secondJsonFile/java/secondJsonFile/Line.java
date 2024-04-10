package secondJsonFile.java.secondJsonFile;

import secondJsonFile.Station;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String lineNumber;
    private String lineName;
    private List<Station> stations;

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public Line(String lineNumber) {
        this.lineNumber = lineNumber;
        this.stations = new ArrayList<>();
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public String getLineName() {

        return lineName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Line: ").append(lineNumber).append("\n");
        for (Station station : stations) {
            sb.append(station.getStationName());
            if (station.hasTransfer()) {
                sb.append(" - Has Connection");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}


