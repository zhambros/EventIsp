package com.saadin.eventmanagementsystem.Model;

import java.sql.Time;
import java.util.Date;

public class Event {
    private int eventId;
    private String eventName;
    private Date eventDate;
    private Time eventTime;
    private int noOfParticipant;
    private CommunityService communityService;
    private Lecture lecture;
    private Competition competition;
    private int joined;
    private Participant participant;

    public Event() {
    }

    public CommunityService getCommunityService() {
        return communityService;
    }

    public void setCommunityService(CommunityService communityService) {
        this.communityService = communityService;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Competition getCompetition() {
        return competition;
    }

    public int getJoined() {
        return joined;
    }

    public void setJoined(int joined) {
        this.joined = joined;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Event(String eventName, Date eventDate, Time eventTime, int noOfParticipant) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.noOfParticipant = noOfParticipant;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public int getNoOfParticipant() {
        return noOfParticipant;
    }

    public void setNoOfParticipant(int noOfParticipant) {
        this.noOfParticipant = noOfParticipant;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
