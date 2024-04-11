package SecondJsonFile;


import java.util.List;
import java.util.Map;

public class MetroSystem {
    private Map<String, List<String>> stations;
    private List<List<Connection>> connections;
    private List<MetroLine> lines;

    public MetroSystem(Map<String, List<String>> stations, List<List<Connection>> connections, List<MetroLine> lines) {
        this.stations = stations;
        this.connections = connections;
        this.lines = lines;
    }

    public Map<String, List<String>> getStations() {
        return stations;
    }

    public void setStations(Map<String, List<String>> stations) {
        this.stations = stations;
    }

    public List<List<Connection>> getConnections() {
        return connections;
    }

    public void setConnections(List<List<Connection>> connections) {
        this.connections = connections;
    }

    public List<MetroLine> getLines() {
        return lines;
    }

    public void setLines(List<MetroLine> lines) {
        this.lines = lines;
    }
}