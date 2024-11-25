

    
package application;

public class EventRecord {

    private int eventId;
    private String eventName;
    private String eventDescription;
    private int eventManager;

    // Constructor
    public EventRecord(int eventId, String eventName, String eventDescription, int eventManager) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventManager = eventManager;
    }

    // Getter and Setter methods
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

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getEventManager() {
        return eventManager;
    }

    public void setEventManager(int eventManager) {
        this.eventManager = eventManager;
    }
}

