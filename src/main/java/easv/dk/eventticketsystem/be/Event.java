package easv.dk.eventticketsystem.be;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private int eventId;
    private String eventName;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String location;
    private String notes;
    private String eventImagePath;
    private String assignedUser;

    private LocalDateTime dateTime;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Event(int eventId, String eventName, LocalDateTime startDatetime, LocalDateTime endDatetime,
                 String location, String notes, String locationGuidance, String eventImagePath, String assignedUser) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.location = location;
        this.notes = notes;
        this.eventImagePath = eventImagePath;
        this.assignedUser = assignedUser;
    }

    public Event(int eventId, String eventName, LocalDateTime startDatetime, LocalDateTime endDatetime, String location, String notes, String eventImagePath, String assignedUser) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.location = location;
        this.notes = notes;
        this.eventImagePath = eventImagePath;
        this.assignedUser  = assignedUser;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
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
    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }
    public void setStartDatetime(LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }
    public LocalDateTime getEndDatetime() {return endDatetime; }
    public void setEndDatetime(LocalDateTime endDatetime) {
        this.endDatetime = endDatetime;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) { this.location = location; }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getEventImagePath() {
        return eventImagePath;
    }

    public Timestamp getFormattedDateTime() {
        return Timestamp.valueOf(dateTime);

    }

    public void setEventImagePath(String eventImagePath) {
        this.eventImagePath = eventImagePath;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", startDatetime=" + startDatetime +
                ", endDatetime=" + endDatetime +
                ", location='" + location + '\'' +
                ", notes='" + notes + '\'' +
                ", eventImagePath='" + eventImagePath + '\'' +
                ", assignedUser='" + assignedUser + '\'' +
                '}';
    }
}
