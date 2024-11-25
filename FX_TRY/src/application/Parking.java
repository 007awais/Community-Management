  
package application;


public class Parking {
    private int parkingSlotNumber;
    private String vehicleId;
    private int residentId;
    private String bookingStatus;
    private String bookingTime;

    public Parking(int parkingSlotNumber, String vehicleId, int residentId, String bookingStatus, String bookingTime) {
        this.parkingSlotNumber = parkingSlotNumber;
        this.vehicleId = vehicleId;
        this.residentId = residentId;
        this.bookingStatus = bookingStatus;
        this.bookingTime = bookingTime;
    }

    public int getParkingSlotNumber() {
        return parkingSlotNumber;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public int getResidentId() {
        return residentId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public String getBookingTime() {
        return bookingTime;
    }
}

