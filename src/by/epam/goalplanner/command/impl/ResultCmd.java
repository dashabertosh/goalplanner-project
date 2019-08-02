package by.epam.goalplanner.command.impl;

public class ResultCmd {
    private final String url;
    private final boolean forward;

    public ResultCmd(String url, boolean forward) {
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
