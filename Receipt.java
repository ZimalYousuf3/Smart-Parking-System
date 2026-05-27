package parking;

public class Receipt {
    private String plateNumber;
    private String vehicleType;
    private long entryTime;
    private long exitTime;
    private double fee;
    private int slotID;

    // Constructor
    public Receipt(String plateNumber, String vehicleType,long entryTime, long exitTime, double fee, int slotID) {
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.fee = fee;
        this.slotID = slotID;
    }

    public String getPlateNumber() { 
    	return plateNumber;
    }
    
    public String getVehicleType() { 
    	return vehicleType; 
    }
    
    public double getFee() { 
    	return fee; 
    }
    
    public int getSlotID() { 
        return slotID; 
    }

    public String getDuration() {
        long ms = exitTime - entryTime;
        long mins = ms / (1000 * 60);
        if (mins < 1) mins = 1;
        return mins + " minutes";
    }
}