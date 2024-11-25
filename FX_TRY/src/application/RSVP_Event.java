package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RSVP_Event {
    private final StringProperty eventName;
    private final StringProperty eventDate;
    private final StringProperty eventLocation;

    public RSVP_Event(String eventName, String eventDate, String eventLocation) {
        this.eventName = new SimpleStringProperty(eventName);
        this.eventDate = new SimpleStringProperty(eventDate);
        this.eventLocation = new SimpleStringProperty(eventLocation);
    }

    public StringProperty eventNameProperty() {
        return eventName;
    }

    public StringProperty eventDateProperty() {
        return eventDate;
    }

    public StringProperty eventLocationProperty() {
        return eventLocation;
    }
}