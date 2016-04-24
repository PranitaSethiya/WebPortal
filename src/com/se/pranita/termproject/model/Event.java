package com.se.pranita.termproject.model;

import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

/**
 * Created by Pranita on 23/4/16.
 */
public class Event extends Announcement {
    private Timestamp eventDatetime;
    private String eventVenue;

    public Event(String netID, String link, String title, String details, Timestamp createTime, Timestamp eventDatetime, String eventVenue) {
        super(netID, link, title, details, AnnouncementType.EVENT, createTime);
        this.eventDatetime = eventDatetime;
        this.eventVenue = eventVenue;
    }

    public Event(Timestamp eventDatetime, String eventVenue) {
        this.eventDatetime = eventDatetime;
        this.eventVenue = eventVenue;
    }

    public Event() {

    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

    public Timestamp getEventDatetime() {
        return eventDatetime;
    }

    public void setEventDatetime(Timestamp eventDatetime) {
        this.eventDatetime = eventDatetime;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }
}
