package secondJsonFile.java.firstJsonFile;

import java.time.LocalDate;

public class StationInfo {


    private LocalDate buildDate;
    private String depth;
    private String line;
    private boolean hasConnection;

    public StationInfo(LocalDate buildDate, String depth) {
        this.buildDate = buildDate;
        this.depth = depth;
    }


    public LocalDate getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(LocalDate buildDate) {
        this.buildDate = buildDate;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public boolean isHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }


}
