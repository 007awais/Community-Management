package application;

public class PaymentRecord {
    private final int paymentId;
    private final String status;
    private final double amount;
    private final int residentId;

    public PaymentRecord(int paymentId, String status, double amount, int residentId) {
        this.paymentId = paymentId;
        this.status = status;
        this.amount = amount;
        this.residentId = residentId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    public int getResidentId() {
        return residentId;
    }
}
