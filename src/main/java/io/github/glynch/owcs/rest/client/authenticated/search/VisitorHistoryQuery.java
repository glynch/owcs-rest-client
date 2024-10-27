package io.github.glynch.owcs.rest.client.authenticated.search;

public interface VisitorHistoryQuery extends SearchQuery {

    String VISITOR_ID = "visitorid";
    String HISTORY_DEF = "historyDef";
    String START_TIME = "starttime";
    String END_TIME = "endtime";

    String visitorId();

    String historyDef();

    long startTime();

    long endTime();
}
