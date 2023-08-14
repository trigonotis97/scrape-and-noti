package com.trigo.scrapeandnoti;

public class CallTask {
    String title;
    String description;
    CallType callType;

    enum CallType {
        AJAX, HTTP
    }

}
