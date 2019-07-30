package by.epam.goalplanner.command;

public class CmdResult {
    private final String url;
    private final boolean forward;

    public CmdResult(String url, boolean forward) {
        this.url = url;
        this.forward = forward;
    }

    public String getUrl() {
        return url;
    }

    public boolean isForward() {
        return forward;
    }
}
