package io.github.glynch.owcs.rest.client.aggregated;

public class AggregatedRestError {

    private final int httpStatus;
    private final String problemInstance;
    private final String detail;
    private final String title;
    private final String problemType;

    public AggregatedRestError(int httpStatus, String problemInstance, String detail, String title, String problemType) {
        this.httpStatus = httpStatus;
        this.problemInstance = problemInstance;
        this.detail = detail;
        this.title = title;
        this.problemType = problemType;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getProblemInstance() {
        return problemInstance;
    }

    public String getDetail() {
        return detail;
    }

    public String getTitle() {
        return title;
    }

    public String getProblemType() {
        return problemType;
    }

}
