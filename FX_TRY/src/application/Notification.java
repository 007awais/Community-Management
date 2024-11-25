package application;

public class Notification {
    private int notificationId;
    private String title;
    private String timestamp;
    private String message;
    private int receiverId;

    public Notification(int notificationId, String title, String timestamp, String message, int receiverId) {
        this.notificationId = notificationId;
        this.title = title;
        this.timestamp = timestamp;
        this.message = message;
        this.receiverId = receiverId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
}
