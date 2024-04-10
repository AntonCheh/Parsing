package SecondJsonFile;

public class MetroStation {
    private String name;
    private String lineNumber;
    private boolean hasConnection;

    public MetroStation(String name, String lineNumber, boolean hasConnection) {
        this.name = name;
        this.lineNumber = lineNumber;
        this.hasConnection = hasConnection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public boolean isHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }
}
