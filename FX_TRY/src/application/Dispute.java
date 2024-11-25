package application;

public class Dispute {
    private int disputeId;
    private String firstPartyName;
    private String secondPartyName;
    private String description;
    private String filedDate;

    // Constructor
    public Dispute(int disputeId, String firstPartyName, String secondPartyName, String description, String filedDate) {
        this.disputeId = disputeId;
        this.firstPartyName = firstPartyName;
        this.secondPartyName = secondPartyName;
        this.description = description;
        this.filedDate = filedDate;
    }

    // Getters and setters
    public int getDisputeId() {
        return disputeId;
    }

    public void setDisputeId(int disputeId) {
        this.disputeId = disputeId;
    }

    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }

    public String getSecondPartyName() {
        return secondPartyName;
    }

    public void setSecondPartyName(String secondPartyName) {
        this.secondPartyName = secondPartyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFiledDate() {
        return filedDate;
    }

    public void setFiledDate(String filedDate) {
        this.filedDate = filedDate;
    }
}
