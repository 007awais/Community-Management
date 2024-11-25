package application;

public class Visitor {
    private int visitorId;
    private String visitorName;
    private String visitDate;
    private String confirmationStatus;
    private int adminId;

    public Visitor(int visitorId, String visitorName, String visitDate, String confirmationStatus, int adminId) {
        this.visitorId = visitorId;
        this.visitorName = visitorName;
        this.visitDate = visitDate;
        this.confirmationStatus = confirmationStatus;
        this.adminId = adminId;
    }

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
}
