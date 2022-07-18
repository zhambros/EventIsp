package com.saadin.eventmanagementsystem.Model;

public class Participant {
    private String emailAddress;
    private int eventId;
    private String eventFeedback;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventFeedback() {
        return eventFeedback;
    }

    public void setEventFeedback(String eventFeedback) {
        this.eventFeedback = eventFeedback;
    }
}
