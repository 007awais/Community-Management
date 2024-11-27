package application;

// Encapsulation: Hiding internal data by making fields private and providing public getters and setters
public class Visitor {

    // Private fields (Encapsulation)
    private int visitorId;
    private String visitorName;
    private String visitDate;
    private String confirmationStatus;
    private int adminId;

    // Constructor Overloading (Polymorphism)
    public Visitor(int visitorId, String visitorName, String visitDate, String confirmationStatus, int adminId) {
        this.visitorId = visitorId;
        this.visitorName = visitorName;
        this.visitDate = visitDate;
        this.confirmationStatus = confirmationStatus;
        this.adminId = adminId;
    }

    // Overloaded constructor with fewer parameters
    public Visitor(int visitorId, String visitorName, String visitDate) {
        this(visitorId, visitorName, visitDate, "Pending", 0); // Default values for other fields
    }

    // Getter and Setter methods for each field (Encapsulation)
    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(String confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    // Method Overriding (Polymorphism)
    @Override
    public String toString() {
        return "Visitor ID: " + visitorId + 
               ", Name: " + visitorName + 
               ", Visit Date: " + visitDate + 
               ", Status: " + confirmationStatus + 
               ", Admin ID: " + adminId;
    }

    // Example of using the "this" keyword to refer to the current object
    public void printDetails() {
        System.out.println("Visitor Details: " + this);
    }
}

