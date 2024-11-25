package application;

import java.sql.Date;

public class RSVP {
    private int rsvpId;
    private int residentId;
    private int eventId;
    private String status;
    private Date rsvpDate;

    public RSVP(int rsvpId, int residentId, int eventId, String status, Date rsvpDate) {
        this.rsvpId = rsvpId;
        this.residentId = residentId;
        this.eventId = eventId;
        this.status = status;
        this.rsvpDate = rsvpDate;
    }

    // Getters and setters for each field
    public int getRsvpId() {
        return rsvpId;
    }

    public void setRsvpId(int rsvpId) {
        this.rsvpId = rsvpId;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRsvpDate() {
        return rsvpDate;
    }

    public void setRsvpDate(Date rsvpDate) {
        this.rsvpDate = rsvpDate;
    }
}
