package SecondJsonFile;

import java.util.ArrayList;
import java.util.List;

public class MetroLine {
    private String name;
    private List<MetroStation> stations;

    public MetroLine(String name) {
        this.name = name;
        this.stations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MetroStation> getStations() {
        return stations;
    }

    public void setStations(List<MetroStation> stations) {
        this.stations = stations;
    }

    public void addStation(MetroStation station) {
        stations.add(station);
    }
}
